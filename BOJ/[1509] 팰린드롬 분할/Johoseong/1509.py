import sys
input = sys.stdin.readline

arr = input().rstrip()
N = len(arr)
dp = [[0] * N for _ in range(N)]
result = [N] * (N + 1)
result[N - 1] = 1
result[N] = 0

for i in range(N): # 길이 1이면 무조건 팰린드롬임
    dp[i][i] = 1
for l in range(1, N):
    for i in range(N - l - 1, -1, -1):
        if arr[i] == arr[i + l]:
            if l == 1: # 길이 2인 팰린드롬
                dp[i][i + 1] = 1
                continue
            if dp[i + 1][i + l - 1] == 1: # 길이 3 이상인 팰린드롬
                dp[i][i + l] = 1

for i in range(N - 2, -1, -1):
    for j in range(i, N):
        if dp[i][j] == 1:
            result[i] = min(result[i], result[j + 1] + 1)
        else:
            result[i] = min(result[i], result[i + 1] + 1)

print(result[0])