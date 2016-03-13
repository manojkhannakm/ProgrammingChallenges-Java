package chapter1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Manoj Khanna
 */

public class PC110106 {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")));

        int t = Integer.parseInt(bufferedReader.readLine());
        bufferedReader.readLine();

        for (int i = 1; i <= t; i++) {
            int[] ram = new int[1000];
            String line;
            int j = 0;
            while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                ram[j++] = Integer.parseInt(line);
            }

            int[] reg = new int[10];

            int n = 0;
            for (int k = 0; k < ram.length; k++) {
                n++;

                int ins = ram[k];
                if (ins == 100) {
                    break;
                }

                int a = ins / 100,
                        b = ins / 10 % 10,
                        c = ins % 10;
                switch (a) {

                    case 2:
                        reg[b] = c;
                        break;

                    case 3:
                        reg[b] = (reg[b] + c) % 1000;
                        break;

                    case 4:
                        reg[b] = (reg[b] * c) % 1000;
                        break;

                    case 5:
                        reg[b] = reg[c];
                        break;

                    case 6:
                        reg[b] = (reg[b] + reg[c]) % 1000;
                        break;

                    case 7:
                        reg[b] = (reg[b] * reg[c]) % 1000;
                        break;

                    case 8:
                        reg[b] = ram[reg[c]];
                        break;

                    case 9:
                        ram[reg[c]] = reg[b];
                        break;

                    case 0:
                        if (reg[c] != 0) {
                            k = reg[b] - 1;
                        }
                        break;

                }
            }

            if (i > 1) {
                System.out.println("");
            }

            System.out.println(n);
        }
    }

}
