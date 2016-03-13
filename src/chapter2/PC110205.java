package chapter2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

class PC110205 {

	private static InputReader in;
	private static PrintWriter out = new PrintWriter(System.out);

	public static void main(String[] args) {
		if (System.getProperty("OFFLINE_JUDGE") != null) {
			try {
				in = new InputReader(new FileInputStream("in.txt"));
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		} else {
			in = new InputReader(System.in);
		}

		new Solution().solve();

		out.close();
	}

	private static class Solution {

		public void solve() {
			int t = in.nextInt();

			in.nextLine();

			for (int i = 0; i < t; i++) {
				int n = in.nextInt();

				ArrayList<HashMap<Integer, Integer>> shuffleList = new ArrayList<>(n);
				for (int j = 0; j < n; j++) {
					HashMap<Integer, Integer> shuffleMap = new HashMap<>();
					for (int k = 1; k <= 52; k++) {
						int key = in.nextInt();
						if (key == k) {
							continue;
						}

						shuffleMap.put(key, k);
					}

					shuffleList.add(shuffleMap);
				}

				int[] cards = new int[53];
				for (int j = 1; j <= 52; j++) {
					cards[j] = j;
				}

				String line;
				while ((line = in.nextLine()) != null && !line.isEmpty()) {
					int k = Integer.parseInt(line);

					HashMap<Integer, Integer> shuffleMap = shuffleList.get(k - 1);
					boolean[] moved = new boolean[53];
					for (Map.Entry<Integer, Integer> entry : shuffleMap.entrySet()) {
						int key = entry.getKey(), value = entry.getValue();
						if (moved[key]) {
							continue;
						}

						int nextCard = cards[value];
						cards[value] = cards[key];
						moved[key] = true;

						int nextKey = value;
						while (!moved[nextKey]) {
							int nextValue = shuffleMap.get(nextKey),
									tempCard = cards[nextValue];
							cards[nextValue] = nextCard;
							moved[nextKey] = true;

							nextCard = tempCard;
							nextKey = nextValue;
						}
					}
				}

				String[] suits = new String[] {"Clubs", "Diamonds", "Hearts", "Spades"},
						values = new String[13];
				for (int j = 0; j < 9; j++) {
					values[j] = String.valueOf(j + 2);
				}
				values[9] = "Jack";
				values[10] = "Queen";
				values[11] = "King";
				values[12] = "Ace";

				if (i > 0) {
					out.println();
				}

				for (int j = 1; j < cards.length; j++) {
					int card = cards[j];
					out.println(values[(card - 1) % 13] + " of " + suits[(card - 1) / 13]);
				}
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
