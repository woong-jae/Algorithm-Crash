# [1149] RGB거리 - Python

## 🔍 Algorithm
**DP**

## 💻 Logic

```Python
# dp[i번 집][j 색깔] = min((dp[i-1번 집][j 아닌 색 1] + 현재 집 비용), (dp[i-1번 집][j 아닌 색 2] + 현재 집 비용))
for i in range(1, N):
    for j in range(3):
        if j == 0: dp[i][j] = min(dp[i - 1][1] + house[i][j], dp[i - 1][2] + house[i][j])
        elif j == 1: dp[i][j] = min(dp[i - 1][0] + house[i][j], dp[i - 1][2] + house[i][j])
        elif j == 2: dp[i][j] = min(dp[i - 1][0] + house[i][j], dp[i - 1][1] + house[i][j])
# N - 1번 집의 세가지 색들 중 최솟값 출력
print(min(dp[N - 1][0], dp[N - 1][1], dp[N - 1][2]))
```
- **점화식** : `dp[i번 집][j 색깔]` = min((`dp[i-1번 집][j 아닌 색 1] + 현재 집 비용`), (`dp[i-1번 집][j 아닌 색 2] + 현재 집 비용`))  
- N - 1번 집의 세가지 색들 중 최솟값 출력  


## 📝 Review

예전에 한번 풀어본 적 있는 문제고, 저번 주에 카카오 코테 준비하면서 비슷한 RGB 거리 2 문제도 풀어봤어서 바로 이 문제부터 다시 풀었다.  
고급문제해결 수업 때도 했었던 대표적인 DP 문제!  