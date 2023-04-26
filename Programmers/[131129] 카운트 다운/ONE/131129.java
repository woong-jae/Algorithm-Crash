import java.util.Arrays;

class Solution {
    private final int COUNT = 0;
    private final int SINGLE_BULL_SUM = 1;
    private final int MAX_SINGLE_SCORE = 20;
    private final int MAX_SCORE = 60;
    private int target;
    private int[][] dp;

    public int[] solution(int target) {
        this.target = target;
        dp = new int[target + 1][2];

        initDpArray();

        for (int number = MAX_SCORE + 1; number <= target; number++) {
            findBestCase(number);
        }
        return dp[target];
    }

    private void initDpArray() {
        for (int number = 1; number < dp.length; number++) {
            dp[number][COUNT] = Integer.MAX_VALUE;
            dp[number][SINGLE_BULL_SUM] = Integer.MIN_VALUE;
        }

        for (int number = 1; number <= Math.min(target, MAX_SINGLE_SCORE); number++) {
            dp[number][COUNT] = 1;
            dp[number][SINGLE_BULL_SUM] = 1;
        }

        for (int number = MAX_SINGLE_SCORE + 1; number <= Math.min(target, MAX_SCORE); number++) {
            if (number == 50) {
                dp[number][COUNT] = 1;
                dp[number][SINGLE_BULL_SUM] = 1;
                continue;
            }
            if (isDoubleOrTriple(number)) {
                dp[number][COUNT] = 1;
                dp[number][SINGLE_BULL_SUM] = 0;
                continue;
            }
            findBestCase(number);
        }
    }

    private boolean isDoubleOrTriple(int number) {
        for (int n = 1; n <= MAX_SINGLE_SCORE; n++) {
            if (n * 2 == number || n * 3 == number) {
                return true;
            }
        }
        return false;
    }

    private void findBestCase(int number) {
        for (int i = 1; i < number; i++) {
            if (dp[i][COUNT] == Integer.MAX_VALUE || dp[number - i][COUNT] == Integer.MAX_VALUE) {
                continue;
            }
            int countSum = dp[i][COUNT] + dp[number - i][COUNT];
            int singleAndBullSum = dp[i][SINGLE_BULL_SUM] + dp[number - i][SINGLE_BULL_SUM];

            if (dp[number][COUNT] == countSum) {
                dp[number][SINGLE_BULL_SUM] = Math.max(dp[number][SINGLE_BULL_SUM], singleAndBullSum);
                continue;
            }
            if (dp[number][COUNT] > countSum) {
                dp[number][COUNT] = countSum;
                dp[number][SINGLE_BULL_SUM] = singleAndBullSum;
            }
        }
    }
}