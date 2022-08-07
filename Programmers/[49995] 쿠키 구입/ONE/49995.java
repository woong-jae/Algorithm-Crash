import java.util.Arrays;

class Solution {
    private int answer = Integer.MIN_VALUE;
    private int n;
    private int[][] dp;
    public int solution(int[] cookie) {
        n = cookie.length;

        if (n == 1)
            return 0;

        dp = init(n, cookie);

        compareSum();

        return answer == Integer.MIN_VALUE ? 0 : answer;
    }

    private int[][] init(int n, int[] cookie) {
        int[][] tmp = new int[n][n];

        tmp[0] = Arrays.copyOf(cookie, n);
        for (int i = 0; i < n - 1; i++)
            tmp[0][i + 1] += tmp[0][i];

        for (int m = 0; m < n - 1; m++) {
            tmp[m + 1][m] = cookie[m];
            // left
            for (int i = 0; i < m; i++)
                tmp[m + 1][i] = tmp[m][i] +  cookie[m];
            // right
            for (int i = m + 1; i < n; i++)
                tmp[m + 1][i] = tmp[m][i] - cookie[m];
        }
        return tmp;
    }

    private void compareSum() {
        for (int i = 1; i < n; i++) {
            int start = 0, end = n - 1, m = i - 1;

            while (start <= m && end > m) {
                if (dp[i][start] == dp[i][end]) {
                    answer = Math.max(answer, dp[i][start]);
                    break;
                } else if (dp[i][start] < dp[i][end]) {
                    end--;
                } else {
                    start++;
                }
            }
        }
    }
}