function solution(n) {
    const dp = Array(n + 1).fill(0);
    dp[0] = 1;
    for(let i = 0; i < n; i++) {
        if(i + 1 <= n) dp[i + 1] = (dp[i + 1] + dp[i]) % 1234567;
        if(i + 2 <= n) dp[i + 2] = (dp[i + 2] + dp[i]) % 1234567;
    }
    return dp[n];
}