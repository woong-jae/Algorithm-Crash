# [1149] RGB거리

## Algorithm
- DP

## Logic

```java
dp[0][0] = color[0][0];
dp[0][1] = color[0][1];
dp[0][2] = color[0][2];
for (int index = 1; index < N; index++) {
    dp[index][0] = Math.min(dp[index - 1][1], dp[index - 1][2]) + color[index][0];
    dp[index][1] = Math.min(dp[index - 1][0], dp[index - 1][2]) + color[index][1];
    dp[index][2] = Math.min(dp[index - 1][0], dp[index - 1][1]) + color[index][2];
}
```

- 현재 인덱스의 집에 특정 색을 칠하는 경우, 이전 집에 해당 색을 제외한 다른 색을 칠하는 경우들 중 최소 비용을 구해 현재 비용으로 갱신한다.

## :black_nib: **Review**

- 알고리즘 시간에 배운 기억이 났던 DP 문제였다.