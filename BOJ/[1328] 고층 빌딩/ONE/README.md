# [1328] 고층 빌딩
## Algorithm
- **DP**

## Logic
- dp[n][r][l]
  - n번째 빌딩을 세울 때, 왼쪽에서 보이는 빌딩의 수가 l, 오른쪽에서 보이는 빌딩의 수가 r
  - 가장 양 옆을 제외한 N-2 경우

```java
for (int i = 2; i <= N; i++)
    for (int j = 1; j <= L; j++)
        for (int k = 1; k <= R; k++) {
            DP[i][j][k] += DP[i - 1][j][k] * (i - 2);
            DP[i][j][k] += DP[i - 1][j - 1][k];
            DP[i][j][k] += DP[i - 1][j][k - 1];
            DP[i][j][k] %= MOD;
        }
```

## Review
의견 없음.