# [1328] 고층 빌딩 - Python

## 🔍 Algorithm
**DP**

## 💻 Logic

```Python
for i in range(2, N + 1):
    for j in range(1, L + 1):
        for k in range(1, R + 1):
            # dp[건물 개수][left][right] = dp[건물 추가 전 개수][left 추가 전 개수][right] + dp[건물 추가 전 개수][left][right 추가 전 개수] + dp[건물 추가 전 개수][left][right] (중간인 경우) * (건물 개수 - 2)
            dp[i][j][k] = (dp[i-1][j-1][k] + dp[i-1][j][k-1] + dp[i-1][j][k] * (i - 2)) % 1000000007
```
- **점화식**  
    : `dp[건물 개수][left][right]` = `dp[건물 추가 전 개수][left 추가 전 개수][right]` + `dp[건물 추가 전 개수][left][right 추가 전 개수]` + `dp[건물 추가 전 개수][left][right] (중간인 경우) * (건물 개수 - 2)`  
- **base case** : `dp[1][1][1] = 1`  


## 📝 Review

어떻게 처리할지 모르겠어서 풀이 참고했는데 허무했다,,  
DP는 항상 몇차원 리스트를 생성하고 거기에 원소 값으로 어떤 걸 넣을지가 중요하다고 생각하는데 이게 너무 어렵다ㅜ 다들 어떻게 생각하는거지