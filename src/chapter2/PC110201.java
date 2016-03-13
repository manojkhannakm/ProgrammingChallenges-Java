package chapter2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class PC110201 {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")));

        String line;
        while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
            StringTokenizer stringTokenizer = new StringTokenizer(line);
            int n = Integer.parseInt(stringTokenizer.nextToken());

            int[] ints = new int[n];
            for (int i = 0; i < n; i++) {
                ints[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            int i;
            int[] counts = new int[n];
            for (i = 0; i < n - 1; i++) {
                int j = Math.abs(ints[i] - ints[i + 1]);
                if (j < 1 || j > n - 1 || counts[j] == 1) {
                    System.out.println("Not jolly");
                    break;
                }

                counts[j] = 1;
            }

            if (i == n - 1) {
                System.out.println("Jolly");
            }
        }
    }

}
