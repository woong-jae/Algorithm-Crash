class Solution {
    public int solution(int n, int[] money) {

        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int m : money) {
            for (int change = 1; change <= n; change++) {
                if (change >= m) {
                    dp[change] += dp[change - m];
                }
            }
        }
        return dp[n];
    }
}
