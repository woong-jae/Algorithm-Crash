# [12914] 멀리 뛰기

## Algorithm

- dynamic programming

## Logic

```js
for (let x = 3; x <= n; x++) {
  dp[x] = (dp[x - 1] + dp[x - 2]) % 1234567;
}
```

- x 칸의 도달하는 방법의 수는 x - 1 칸의 방법 수와 x - 2 칸의 방법 수의 합이다.

### 시간 복잡도: O(N)

## Review

문제를 보고 dp 문제임을 알았고, 1부터 7까지의 답을 도출하니 피보나치 수열이 보였다.
