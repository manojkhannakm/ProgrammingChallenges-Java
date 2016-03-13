package chapter1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Manoj Khanna
 */

public class PC110108 {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")));

        int t = Integer.parseInt(bufferedReader.readLine());
        bufferedReader.readLine();

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(bufferedReader.readLine());
            String[] names = new String[n];
            for (int j = 0; j < n; j++) {
                names[j] = bufferedReader.readLine();
            }

            String line;
            ArrayList<int[]> ballotList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                String[] ballotString = line.split(" ");
                int[] ballot = new int[ballotString.length];
                for (int j = 0; j < ballotString.length; j++) {
                    ballot[j] = Integer.parseInt(ballotString[j]);
                }

                ballotList.add(ballot);
            }

            HashMap<Integer, Candidate> candidateMap = new HashMap<>();
            for (int j = 0; j < n; j++) {
                int id = j + 1;
                candidateMap.put(id, new Candidate(id));
            }

            ArrayList<Candidate> candidateList = new ArrayList<>(candidateMap.values());
            int[] ballotIndexes = new int[ballotList.size()];
            int requiredVotes = (int) Math.floor(ballotList.size() / 2.0f) + 1;
            while (true) {
                for (Candidate candidate : candidateList) {
                    candidate.votes = 0;
                }

                for (int j = 0; j < ballotList.size(); j++) {
                    candidateMap.get(ballotList.get(j)[ballotIndexes[j]]).votes++;
                }

                Collections.sort(candidateList, new Comparator<Candidate>() {

                    @Override
                    public int compare(Candidate o1, Candidate o2) {
                        return Integer.compare(o1.votes, o2.votes);
                    }

                });

                if (candidateList.get(candidateList.size() - 1).votes >= requiredVotes
                        || candidateList.get(0).votes == candidateList.get(candidateList.size() - 1).votes) {
                    break;
                }

                int leastVotes = candidateList.get(0).votes;
                Iterator<Candidate> iterator = candidateList.iterator();
                while (iterator.hasNext()) {
                    Candidate candidate = iterator.next();
                    if (candidate.votes == leastVotes) {
                        candidate.eliminated = true;
                        iterator.remove();
                    } else {
                        break;
                    }
                }

                for (int j = 0; j < ballotList.size(); j++) {
                    int[] ballot = ballotList.get(j);
                    while (candidateMap.get(ballot[ballotIndexes[j]]).eliminated) {
                        ballotIndexes[j]++;
                    }
                }
            }

            if (i > 0) {
                System.out.println("");
            }

            boolean tied = candidateList.get(0).votes == candidateList.get(candidateList.size() - 1).votes;
            if (tied) {
                for (Candidate candidate : candidateList) {
                    System.out.println(names[candidate.id - 1]);
                }
            } else {
                System.out.println(names[candidateList.get(candidateList.size() - 1).id - 1]);
            }
        }
    }

    private static class Candidate {

        private int id, votes;
        private boolean eliminated;

        public Candidate(int id) {
            this.id = id;
        }

    }

}
