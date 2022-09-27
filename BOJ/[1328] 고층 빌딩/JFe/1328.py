import sys

N, L, R = map(int, sys.stdin.readline().split())
dp = [[[0 for _ in range(N + 1)] for _ in range(N + 1)] for _ in range(N + 1)]
dp[1][1][1] = 1     # base case

for i in range(2, N + 1):
    for j in range(1, L + 1):
        for k in range(1, R + 1):
            # dp[건물 개수][left][right] = dp[건물 추가 전 개수][left 추가 전 개수][right] + dp[건물 추가 전 개수][left][right 추가 전 개수] + dp[건물 추가 전 개수][left][right] (중간인 경우) * (건물 개수 - 2)
            dp[i][j][k] = (dp[i-1][j-1][k] + dp[i-1][j][k-1] + dp[i-1][j][k] * (i - 2)) % 1000000007
print(dp[N][L][R])