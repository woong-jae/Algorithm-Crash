import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    static class Power {

        private final int algoPower;
        private final int codingPower;
        private final int time;

        public Power(int algoPower, int codingPower, int time) {
            this.algoPower = algoPower;
            this.codingPower = codingPower;
            this.time = time;
        }

        public int algoPower() {
            return algoPower;
        }

        public int codingPower() {
            return codingPower;
        }

        public int time() {
            return time;
        }
    }

    private int[][] problems;

    public int solution(int alp, int cop, int[][] problems) {
        this.problems = problems;
        return dijkstra(alp, cop);
    }

    private int dijkstra(int algoPower, int codingPower) {
        int targetAlgoPower = Arrays.stream(problems)
                .mapToInt(problem -> problem[0])
                .max()
                .getAsInt();
        int targetCodingPower = Arrays.stream(problems)
                .mapToInt(problem -> problem[1])
                .max()
                .getAsInt();
        boolean[][] visited = new boolean[500][500];

        Queue<Power> powers = new PriorityQueue<>(Comparator.comparing(Power::time)
                .thenComparing(Power::algoPower, Comparator.reverseOrder())
                .thenComparing(Power::codingPower, Comparator.reverseOrder()));
        powers.add(new Power(algoPower, codingPower, 0));

        while (!powers.isEmpty()) {
            Power current = powers.poll();

            if (visited[current.algoPower()][current.codingPower()]) {
                continue;
            }
            visited[current.algoPower()][current.codingPower()] = true;

            if (current.algoPower() >= targetAlgoPower && current.codingPower() >= targetCodingPower) {
                return current.time();
            }

            if (current.algoPower() + 1 <= targetAlgoPower) {
                powers.add(new Power(current.algoPower() + 1, current.codingPower(), current.time() + 1));
            }
            if (current.codingPower() + 1 <= targetCodingPower) {
                powers.add(new Power(current.algoPower(), current.codingPower() + 1, current.time() + 1));
            }

            for (int[] problem : problems) {
                int requiredAlgoPower = problem[0];
                int requiredCodingPower = problem[1];
                int rewardAlgoPower = problem[2];
                int rewardCodingPower = problem[3];
                int time = problem[4];

                if (current.algoPower() < requiredAlgoPower || current.codingPower() < requiredCodingPower) {
                    continue;
                }

                powers.add(new Power(current.algoPower() + rewardAlgoPower,
                        current.codingPower() + rewardCodingPower,
                        current.time() + time));
            }
        }
        return -1;
    }
}