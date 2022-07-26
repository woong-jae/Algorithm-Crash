
class Solution {
    public long solution(int n) {
        long[] dp = new long[n];
        long div = 1234567;
        
        if (n == 1) return 1;
        
        dp[0] = 1 % div;
        dp[1] = 2 % div;
        
        for (int i = 2; i < n; i++)
            dp[i] = (dp[i - 2] + dp[i - 1]) % div;
        
        return dp[n - 1];
    }
}