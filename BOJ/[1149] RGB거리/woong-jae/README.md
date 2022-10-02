# [1149] RGB 거리

## Algorithm

- DP

## Logic

RGB를 012로 바꿔 표현한다.

i번째 집에 c색을 칠하면 드는 총 비용은, i-1번째 집에 (c + 1) % 3 색과 (c + 2) % 3 색을 칠했을 때 든 총 비용중 작은 것에 색을 칠하는 비용을 더하면 된다.

```js
for (let i = 1; i < costs.length; i++) {
  for (let c = 0; c < 3; c++) {
    dp[i][c] = Math.min(dp[i - 1][(c + 1) % 3], dp[i - 1][(c + 2) % 3]) + costs[i][c];
  }
}
```
## Review
쉬운 DP 문제. 4번 풀다가 도망쳐왔다.