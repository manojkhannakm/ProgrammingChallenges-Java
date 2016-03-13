package chapter1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Manoj Khanna
 */

public class PC110107 {

    private static char[][] board;

    private static int check() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                char src = board[i][j];
                if (src == '.') {
                    continue;
                }

                int oppColor = Character.isLowerCase(src) ? 1 : 0;
                char oppKing = oppColor == 0 ? 'k' : 'K';
                src = Character.toLowerCase(src);
                if (src == 'p') {
                    int r = i + (oppColor == 0 ? -1 : 1);
                    if (r >= 0 && r <= 7
                            && ((j > 0 && board[r][j - 1] == oppKing) || (j < 7 && board[r][j + 1] == oppKing))) {
                        return oppColor;
                    }
                }

                if (src == 'n') {
                    int[] rOffs = new int[]{-1, -2, -2, -1, 1, 2, 2, 1},
                            cOffs = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};
                    for (int k = 0; k < rOffs.length; k++) {
                        int r = i + rOffs[k],
                                c = j + cOffs[k];
                        if (r >= 0 && r <= 7 && c >= 0 && c <= 7 && board[r][c] == oppKing) {
                            return oppColor;
                        }
                    }
                }

                if (src == 'b' || src == 'q') {
                    int r = i - 1,
                            c = j - 1;
                    while (r >= 0 && c >= 0) {
                        char dst = board[r][c];
                        if (dst == oppKing) {
                            return oppColor;
                        } else if (dst == '.') {
                            r--;
                            c--;
                        } else {
                            break;
                        }
                    }

                    r = i - 1;
                    c = j + 1;
                    while (r >= 0 && c <= 7) {
                        char dst = board[r][c];
                        if (dst == oppKing) {
                            return oppColor;
                        } else if (dst == '.') {
                            r--;
                            c++;
                        } else {
                            break;
                        }
                    }

                    r = i + 1;
                    c = j + 1;
                    while (r <= 7 && c <= 7) {
                        char dst = board[r][c];
                        if (dst == oppKing) {
                            return oppColor;
                        } else if (dst == '.') {
                            r++;
                            c++;
                        } else {
                            break;
                        }
                    }

                    r = i + 1;
                    c = j - 1;
                    while (r <= 7 && c >= 0) {
                        char dst = board[r][c];
                        if (dst == oppKing) {
                            return oppColor;
                        } else if (dst == '.') {
                            r++;
                            c--;
                        } else {
                            break;
                        }
                    }
                }

                if (src == 'r' || src == 'q') {
                    int r = i - 1;
                    while (r >= 0) {
                        char dst = board[r][j];
                        if (dst == oppKing) {
                            return oppColor;
                        } else if (dst == '.') {
                            r--;
                        } else {
                            break;
                        }
                    }

                    r = i + 1;
                    while (r <= 7) {
                        char dst = board[r][j];
                        if (dst == oppKing) {
                            return oppColor;
                        } else if (dst == '.') {
                            r++;
                        } else {
                            break;
                        }
                    }

                    int c = j - 1;
                    while (c >= 0) {
                        char dst = board[i][c];
                        if (dst == oppKing) {
                            return oppColor;
                        } else if (dst == '.') {
                            c--;
                        } else {
                            break;
                        }
                    }

                    c = j + 1;
                    while (c <= 7) {
                        char dst = board[i][c];
                        if (dst == oppKing) {
                            return oppColor;
                        } else if (dst == '.') {
                            c++;
                        } else {
                            break;
                        }
                    }
                }

                if (src == 'k') {
                    int[] rOffs = new int[]{-1, -1, -1, 0, 0, 0, 1, 1, 1},
                            cOffs = new int[]{-1, 0, 1, -1, 0, 1, -1, 0, 1};
                    for (int k = 0; k < rOffs.length; k++) {
                        int r = i + rOffs[k],
                                c = j + cOffs[k];
                        if (r >= 0 && r <= 7 && c >= 0 && c <= 7 && board[r][c] == oppKing) {
                            return oppColor;
                        }
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")));

        int t = 1;
        do {
            board = new char[8][8];
            for (int i = 0; i < 8; i++) {
                board[i] = bufferedReader.readLine().toCharArray();
            }

            boolean empty = true;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j] != '.') {
                        empty = false;
                        break;
                    }
                }
            }

            if (empty) {
                break;
            }

            String checkColor = null;
            switch (check()) {

                case -1:
                    checkColor = "no";
                    break;

                case 0:
                    checkColor = "black";
                    break;

                case 1:
                    checkColor = "white";
                    break;

            }
            System.out.println("Game #" + t++ + ": " + checkColor + " king is in check.");
        } while (bufferedReader.readLine() != null);
    }

}
