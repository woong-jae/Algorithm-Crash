import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    static class Node {
        private final int x;
        private final int y;
        private final int weight;

        public Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        public int x() {
            return x;
        }

        public int y() {
            return y;
        }

        public int weight() {
            return weight;
        }
    }

    private final int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    private final int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    private final int[][] keys = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {-1, 0, -1}};
    private final int[][] weights = new int[10][10];
    private int[][][] dp;

    public int solution(String numbers) {

        for (int[] w : weights) {
            Arrays.fill(w, -1);
        }

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3; col++) {
                if (keys[row][col] == -1) {
                    continue;
                }
                setWeight(row, col);
            }
        }
        for (int index = 0; index < 10; index++) {
            weights[index][index] = 1;
        }

        int length = numbers.length();
        dp = new int[length + 1][10][10];
        for (int number = 0; number < length + 1; number++) {
            for (int left = 0; left < 10; left++) {
                Arrays.fill(dp[number][left], -1);
            }
        }
        return simulate(0, length, 4, 6, numbers);
    }

다

    private boolean isInbound(int x, int y) {
        return x >= 0 && y >= 0 && x < 4 && y < 3;
    }

    private int simulate(int index, int length, int left, int right, String numbers) {
        if (index == length) {
            return 0;
        }
        if (dp[index][left][right] != -1) {
            return dp[index][left][right];
        }

        int number = Character.getNumericValue(numbers.charAt(index));
        int result = Integer.MAX_VALUE;

        if (number != right) {
            result = Math.min(result,
                    simulate(index + 1, length, number, right, numbers) + weights[left][number]);
        }
        if (number != left) {
            result = Math.min(result,
                    simulate(index + 1, length, left, number, numbers) + weights[right][number]);
        }
        return dp[index][left][right] = result;
    }
}