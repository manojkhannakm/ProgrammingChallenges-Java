package chapter2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Manoj Khanna
 */

public class PC110203 {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")));

        int t = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(bufferedReader.readLine());
            int[] hartals = new int[n + 1];

            int p = Integer.parseInt(bufferedReader.readLine());
            for (int j = 0; j < p; j++) {
                int h = Integer.parseInt(bufferedReader.readLine());
                for (int k = h; k <= n; k += h) {
                    hartals[k]++;
                }
            }

            int res = 0;
            for (int j = 1; j <= n; j++) {
                int day = j % 7;
                if (day != 6 && day != 0 && hartals[j] > 0) {
                    res++;
                }
            }

            System.out.println(res);
        }
    }

}
