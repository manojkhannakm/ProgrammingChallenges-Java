package chapter2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class PC110202 {

    private static int hand(Card[] cards) {
        Arrays.sort(cards, new Comparator<Card>() {

            @Override
            public int compare(Card o1, Card o2) {
                return Integer.compare(o1.value, o2.value);
            }

        });

        boolean straight = true;
        for (int i = 1; i < 5; i++) {
            if (cards[i].value - cards[0].value != i) {
                straight = false;
                break;
            }
        }

        int[] valueCounts = new int[13],
                suitCounts = new int[4];
        for (Card card : cards) {
            valueCounts[card.value]++;
            suitCounts[card.suit]++;
        }

        Arrays.sort(valueCounts);
        int sameValueCount1 = valueCounts[12],
                sameValueCount2 = valueCounts[11];

        Arrays.sort(suitCounts);
        int sameSuitCount = suitCounts[3];

        if (straight && sameSuitCount == 5) {
            return 9;
        } else if (sameValueCount1 == 4) {
            return 8;
        } else if (sameValueCount1 == 3 && sameValueCount2 == 2) {
            return 7;
        } else if (sameSuitCount == 5) {
            return 6;
        } else if (straight) {
            return 5;
        } else if (sameValueCount1 == 3) {
            return 4;
        } else if (sameValueCount1 == 2 && sameValueCount2 == 2) {
            return 3;
        } else if (sameValueCount1 == 2) {
            return 2;
        } else {
            return 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            StringTokenizer tokenizer = new StringTokenizer(line);

            Card[] cards1 = new Card[5];
            for (int i = 0; i < 5; i++) {
                cards1[i] = new Card(tokenizer.nextToken());
            }

            Card[] cards2 = new Card[5];
            for (int i = 0; i < 5; i++) {
                cards2[i] = new Card(tokenizer.nextToken());
            }

            int hand1 = hand(cards1),
                    hand2 = hand(cards2);
            if (hand1 == hand2) {
                switch (hand1) {

                    case 9:

                    case 5:
                        hand1 = cards1[4].value;
                        hand2 = cards2[4].value;
                        break;

                    case 8:

                    case 7:

                    case 4:
                        hand1 = cards1[2].value;
                        hand2 = cards2[2].value;
                        break;

                    case 3:

                    case 2:
                        int[] valueCounts1 = new int[13],
                                valueCounts2 = new int[13];
                        for (int i = 0; i < 5; i++) {
                            valueCounts1[cards1[i].value]++;
                            valueCounts2[cards2[i].value]++;
                        }

                        int h1 = -1, l1 = -1, k1 = -1,
                                h2 = -1, l2 = -1, k2 = -1;
                        for (int i = 12; i >= 0; i--) {
                            int valueCount1 = valueCounts1[i];
                            if (valueCount1 == 2) {
                                if (h1 == -1) {
                                    h1 = i;
                                } else if (l1 == -1) {
                                    l1 = i;
                                }
                            } else if (valueCount1 == 1 && k1 == -1) {
                                k1 = i;
                            }

                            int valueCount2 = valueCounts2[i];
                            if (valueCount2 == 2) {
                                if (h2 == -1) {
                                    h2 = i;
                                } else if (l2 == -1) {
                                    l2 = i;
                                }
                            } else if (valueCount2 == 1 && k2 == -1) {
                                k2 = i;
                            }

                            if (k1 == k2) {
                                k1 = k2 = -1;
                            }
                        }

                        if (h1 != h2) {
                            hand1 = h1;
                            hand2 = h2;
                        } else if (l1 != l2) {
                            hand1 = l1;
                            hand2 = l2;
                        } else if (k1 != k2) {
                            hand1 = k1;
                            hand2 = k2;
                        }
                        break;

                    case 6:

                    case 1:
                        for (int i = 4; i >= 0; i--) {
                            hand1 = cards1[i].value;
                            hand2 = cards2[i].value;

                            if (hand1 != hand2) {
                                break;
                            }
                        }
                        break;

                }
            }

            if (hand1 > hand2) {
                System.out.println("Black wins.");
            } else if (hand1 == hand2) {
                System.out.println("Tie.");
            } else {
                System.out.println("White wins.");
            }
        }
    }

    private static class Card {

        private int value, suit;

        public Card(String s) {
            char c = s.charAt(0);
            switch (c) {

                case 'T':
                    value = 8;
                    break;

                case 'J':
                    value = 9;
                    break;

                case 'Q':
                    value = 10;
                    break;

                case 'K':
                    value = 11;
                    break;

                case 'A':
                    value = 12;
                    break;

                default:
                    value = c - '2';
                    break;

            }

            switch (s.charAt(1)) {

                case 'C':
                    suit = 0;
                    break;

                case 'D':
                    suit = 1;
                    break;

                case 'H':
                    suit = 2;
                    break;

                case 'S':
                    suit = 3;
                    break;

            }
        }

    }

}
