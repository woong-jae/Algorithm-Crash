import sys
input = sys.stdin.readline

N = int(input())
dp = [[[0 for _ in range(1024)] for _ in range(10)] for _ in range(N + 1)]

for i in range(1, 10): # 0으로 시작하는 수는 계단수 아니라서 제외
    dp[1][i][1 << i] = 1

for length in range(1, N):
    for last in range(10):
        for bit in range(1024):
            if last == 9:
                dp[length + 1][8][bit | (1 << 8)] += dp[length][last][bit]
                dp[length + 1][8][bit | (1 << 8)] %= 1000000000
            elif last == 0:
                dp[length + 1][1][bit | (1 << 1)] += dp[length][last][bit]
                dp[length + 1][1][bit | (1 << 1)] %= 1000000000
            else: # 마지막이 1~8이면 +-1 가능
                dp[length + 1][last + 1][bit | (1 << (last + 1))] += dp[length][last][bit]
                dp[length + 1][last - 1][bit | (1 << (last - 1))] += dp[length][last][bit]
                dp[length + 1][last + 1][bit | (1 << (last + 1))] %= 1000000000
                dp[length + 1][last + 1][bit | (1 << (last - 1))] %= 1000000000

answer = 0
for last in range(10):
    answer += dp[N][last][1023]
    answer %= 1000000000

print(answer)