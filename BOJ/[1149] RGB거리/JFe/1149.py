import sys

N = int(sys.stdin.readline())
house = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
dp = [[0 for _ in range(3)]for _ in range(N)]

for i in range(3):
    dp[0][i] = house[0][i]

# dp[i번 집][j 색깔] = min((dp[i-1번 집][j 아닌 색 1] + 현재 집 비용), (dp[i-1번 집][j 아닌 색 2] + 현재 집 비용))
for i in range(1, N):
    for j in range(3):
        if j == 0: dp[i][j] = min(dp[i - 1][1] + house[i][j], dp[i - 1][2] + house[i][j])
        elif j == 1: dp[i][j] = min(dp[i - 1][0] + house[i][j], dp[i - 1][2] + house[i][j])
        elif j == 2: dp[i][j] = min(dp[i - 1][0] + house[i][j], dp[i - 1][1] + house[i][j])
# N - 1번 집의 세가지 색들 중 최솟값 출력
print(min(dp[N - 1][0], dp[N - 1][1], dp[N - 1][2]))