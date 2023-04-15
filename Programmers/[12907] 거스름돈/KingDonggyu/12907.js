const DIVIDER = 1000000007;

function solution(n, money) {
  const dp = Array(n + 1).fill(0);

  dp[0] = 1;

  money.forEach((m) => {
    for (let change = 0; change <= n; change++) {
      if (change >= m) {
        dp[change] += dp[change - m] % DIVIDER;
      }
    }
  });

  return dp[n];
}
