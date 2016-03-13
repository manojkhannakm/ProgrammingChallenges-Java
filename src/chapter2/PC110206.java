package chapter2;

import java.io.*;
import java.util.*;

/**
 * @author Manoj Khanna
 */

public class PC110206 {

	private static InputReader in;
	private static PrintWriter out = new PrintWriter(System.out);

	public static void main(String[] args) {
		if (Arrays.asList(args).contains("-local")) {
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
			int s = in.nextInt();

			for (int i = 1; i <= s; i++) {
				int p = in.nextInt(),
						n = in.nextInt();

				HashMap<String, Author> authorMap = new HashMap<>();
				authorMap.put("Erdos, P.", new Author(0));

				for (int j = 0; j < p; j++) {
					ArrayList<String> nameList = new ArrayList<>();
					String string = "";
					while (!string.endsWith(":")) {
						string = in.next() + " " + in.next();
						nameList.add(string.substring(0, string.length() - 1));
					}

					in.nextLine();

					Author parentAuthor = null;
					for (String name : nameList) {
						Author author = authorMap.get(name);
						if (author == null) {
							continue;
						}

						if (parentAuthor == null || author.erdos < parentAuthor.erdos) {
							parentAuthor = author;
						}
					}

					if (parentAuthor == null) {
						for (String name : nameList) {
							authorMap.put(name, new Author(Integer.MAX_VALUE));
						}
					} else {
						for (String name : nameList) {
							Author author = authorMap.get(name);
							if (author != null) {
								if (author == parentAuthor) {
									continue;
								}

								if (author.parentAuthor != null) {
									author.parentAuthor.childAuthorList.remove(author);
								}

								author.erdos = parentAuthor.erdos + 1;
								author.parentAuthor = parentAuthor;
								author.updateChildErdos();
							} else {
								author = new Author(parentAuthor.erdos + 1);
								author.parentAuthor = parentAuthor;

								authorMap.put(name, author);
							}

							parentAuthor.childAuthorList.add(author);
						}
					}
				}

				out.println("Scenario " + i);

				for (int j = 0; j < n; j++) {
					String name = in.next() + " " + in.next();
					Author author = authorMap.get(name);
					if (author == null || author.erdos == Integer.MAX_VALUE) {
						out.println(name + " infinity");
					} else {
						out.println(name + " " + author.erdos);
					}
				}
			}
		}

		private class Author {

			private int erdos;
			private Author parentAuthor;
			private LinkedList<Author> childAuthorList = new LinkedList<>();

			public Author(int erdos) {
				this.erdos = erdos;
			}

			private void updateChildErdos() {
				for (Author childAuthor : childAuthorList) {
					childAuthor.erdos = erdos + 1;
					childAuthor.updateChildErdos();
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
			if (stringTokenizer != null && stringTokenizer.hasMoreTokens()) {
				return stringTokenizer.nextToken("");
			}

			try {
				return bufferedReader.readLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}

}
