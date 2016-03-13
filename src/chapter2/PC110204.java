package chapter2;

import java.io.*;
import java.util.*;

/**
 * @author Manoj Khanna
 */

class PC110204 {

	private static InputReader in;
	private static PrintWriter out = new PrintWriter(System.out);

	public static void main(String[] args) {
		if (System.getProperty("OFFLINE_JUDGE") != null) {
			try {
				in = new InputReader(new FileInputStream("in.txt"));
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}

			do {
				new Solution().solve();

				out.println("");
			} while (in.nextLine() != null);
		} else {
			in = new InputReader(System.in);

			new Solution().solve();
		}

		out.close();
	}

	private static class Solution {

		public static final char ASTERISK = '*';
		private HashMap<Integer, ArrayList<Word>> dictMap;
		private ArrayList<Word> lineList;

		private boolean matches(Word lineWord, Word dictWord, char[] map) {
			for (int i = 0; i < lineWord.string.length(); i++) {
				char lineC = lineWord.string.charAt(i),
						dictC = dictWord.string.charAt(i),
						mapC = map[lineC - 'a'];
				if (mapC == ASTERISK) {
					map[lineC - 'a'] = dictC;
				} else if (mapC != dictC) {
					return false;
				}
			}

			boolean[] booleans = new boolean[26];
			for (char c : map) {
				if (c == ASTERISK) {
					continue;
				}

				if (booleans[c - 'a']) {
					return false;
				}

				booleans[c - 'a'] = true;
			}

			return true;
		}

		private char[] decrypt(int i, char[] map) {
			if (i == lineList.size()) {
				return map;
			}

			Word lineWord = lineList.get(i);

			ArrayList<Word> dictList = dictMap.get(lineWord.code);
			if (dictList == null) {
				return null;
			}

			for (Word dictWord : dictList) {
				char[] tempMap = Arrays.copyOf(map, map.length);
				if (matches(lineWord, dictWord, tempMap)) {
					tempMap = decrypt(i + 1, tempMap);
					if (tempMap != null) {
						return tempMap;
					}
				}
			}

			return null;
		}

		public void solve() {
			int n = in.nextInt();

			dictMap = new HashMap<>(n);
			for (int i = 0; i < n; i++) {
				Word dictWord = new Word(in.nextLine());

				ArrayList<Word> dictList = dictMap.get(dictWord.code);
				if (dictList == null) {
					dictList = new ArrayList<>();
					dictMap.put(dictWord.code, dictList);
				}

				dictList.add(dictWord);
			}

			String line;
			while ((line = in.nextLine()) != null && !line.isEmpty()) {
				String[] lineStrings = line.split(" ");

				LinkedHashSet<Word> lineSet = new LinkedHashSet<>(lineStrings.length);
				for (String lineString : lineStrings) {
					lineSet.add(new Word(lineString));
				}

				lineList = new ArrayList<>(lineSet);

				char[] map = new char[26];
				for (int i = 0; i < 26; i++) {
					map[i] = ASTERISK;
				}

				char[] tempMap = decrypt(0, map);
				if (tempMap != null) {
					map = tempMap;
				}

				StringBuilder stringBuilder = new StringBuilder(line.length());
				for (int i = 0; i < line.length(); i++) {
					char lineC = line.charAt(i);
					stringBuilder.append(lineC == ' ' ? ' ' : map[lineC - 'a']);
				}

				out.println(stringBuilder);
			}
		}

		private class Word {

			private String string;
			private int code;

			public Word(String string) {
				this.string = string;

				int[] counts = new int[26];
				int j = 0;
				for (int i = 0; i < string.length(); i++) {
					int index = string.charAt(i) - 'a';
					if (counts[index] == 0) {
						counts[index] = ++j;
					}

					code = code * 10 + counts[index];
				}
			}

			@Override
			public boolean equals(Object obj) {
				return obj instanceof Word && ((Word) obj).string.equals(string);
			}

			@Override
			public int hashCode() {
				return string.hashCode();
			}

			@Override
			public String toString() {
				return string;
			}

		}

	}

	@SuppressWarnings("UnusedDeclaration")
	private static class InputReader {

		private BufferedReader bufferedReader;
		private StringTokenizer stringTokenizer;

		public InputReader(InputStream inputStream) {
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		}

		public String next() {
			while (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
				try {
					stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

			return stringTokenizer.nextToken();
		}

		public char nextChar() {
			return next().charAt(0);
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public float nextFloat() {
			return Float.parseFloat(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public String nextLine() {
			stringTokenizer = null;

			try {
				return bufferedReader.readLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}

}
