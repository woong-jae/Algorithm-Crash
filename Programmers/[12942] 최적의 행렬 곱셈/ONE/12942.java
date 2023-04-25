import java.util.Arrays;

class Solution {
    public int solution(int[][] matrix_sizes) {
        int length = matrix_sizes.length;
        int[][] dp = new int[length][length];

        for (int index = 0; index < length; index++) {
            Arrays.fill(dp[index], Integer.MAX_VALUE);
            dp[index][index] = 0;
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i; j++) {
                for (int k = j; k < j + i; k++) {
                    dp[j][j + i] = Math.min(dp[j][j + i],
                            dp[j][k] + dp[k + 1][j + i]
                                    + matrix_sizes[j][0] * matrix_sizes[k][1] * matrix_sizes[j + i][1]);
                }
            }
        }

        return dp[0][length - 1];
    }
}