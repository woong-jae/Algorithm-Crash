# [1328] 고층 빌딩

## Algorithm

- DP

## Logic

1. N = 1, L = 1, R = 1일 때, 1개만 가능하다.
2. 모든 건물의 높이를 1 높여준 다음, 높이가 1인 건물을 사이사이에 끼워넣는다.

건물을 끼워넣을 수 있는 경우의 수는 세가지다. 가장 왼쪽에 배치하면 L이 1 증가하고, 가장 오른쪽에 배치하면 R이 1 증가한다. 그리고 나머지에 끼워넣으면 L과 R의 변화가 일어나지 않는다.

즉, `length`, `l`, `r`일 때 가능한 경우의 수는 `dp[length - 1][l - 1][r] + dp[length - 1][l][r - 1] + dp[length - 1][l][r] * (length - 2)`다.

```js
const dp = Array.from(Array(N + 1), () => Array.from(Array(N + 1), () => Array(N + 1).fill(0)));
dp[1][1][1] = 1;
for (let length = 2; length <= N; length++) {
  for (let l = 1; l <= length; l++) {
    for (let r = 1; r <= length; r++) {
      dp[length][l][r] =
        (dp[length - 1][l - 1][r] +
          dp[length - 1][l][r - 1] +
          dp[length - 1][l][r] * (length - 2)) %
        mod;
    }
  }
}
```

## Review
조금 생각해보다가 풀 수 있는 문제가 아니라 판단하고 빠르게 답을 봤다. 역시나 풀이를 보니 절대 혼자 못풀었을 것 같다.

일반적인 dp 문제와 좀 다른 문제인 것 같다. 어렵다!