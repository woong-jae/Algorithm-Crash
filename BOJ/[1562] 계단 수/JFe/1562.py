import sys

N = int(sys.stdin.readline())
dp = [[[0 for _ in range(1024)] for _ in range(10)] for _ in range(N)]
result = 0
# 첫 자릿수로 1 ~ 9가 오는 경우, 1로 초기화 (첫 자릿수로 0은 불가능)
for i in range(1, 10):
    dp[0][i][1 << i] = 1
# dp[i][j][k] / i : 자릿수 / j : 해당 숫자(0 ~ 9) / k : 지금까지 포함된 숫자 (bit mask)
for i in range(1, N):
    for j in range(10):
        for k in range(1024):
            # 0이거나 9인 경우는 앞 뒤 하나만 생각하면 되기 때문에 예외 처리
            if j == 0: dp[i][j][k | (1 << j)] += dp[i - 1][j + 1][k] % 1000000000
            elif j == 9: dp[i][j][k | (1 << j)] += dp[i - 1][j - 1][k] % 1000000000
            else: dp[i][j][k | (1 << j)] += (dp[i - 1][j - 1][k] + dp[i - 1][j + 1][k]) % 1000000000
# 길이가 N이면서 0 ~ 9 값을 전부 다 사용한 경우 전부 더해서 출력
for i in range(10):
    result += dp[N - 1][i][1023]
print(result % 1000000000)