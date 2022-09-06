function solution(n) {
  const fibo = [1, 1, 2, 3, 5, 8];
  for (let i = 6; i <= n; i++) {
    fibo[i] = (fibo[i - 1] + fibo[i - 2]) % 1000000007;
  }
  return fibo[n];
}
