# [12900] 2 \* n 타일링

## Algorithm

- DP

## Logic

길이가 `n`인 바닥을 가득 채우는 방법은 길이가 `n - 1`인 바닥과 `n - 2`인 바닥을 채우는 방법의 합이다.

```js
for (let i = 3; i <= n; i++) {
  dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
}
```

## Review
제일 기본적인 DP 문제다. 처음에 탑다운으로 작성했다가 시간초과가 났고, 배열을 초기화해서 시간초과가 났었다.