function solution(n) {
  const dp = [null, 1, 2];

  if (n <= 2) {
    return dp[n];
  }

  for (let x = 3; x <= n; x++) {
    dp[x] = (dp[x - 1] + dp[x - 2]) % 1234567;
  }

  return dp[n];
}