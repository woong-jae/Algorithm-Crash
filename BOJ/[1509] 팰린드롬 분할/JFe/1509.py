import sys

s = sys.stdin.readline().rstrip()
n = len(s)
pal =[[False for _ in range(n)] for _ in range(n)]
dp = [sys.maxsize for _ in range(n + 1)]
dp[-1] = 0
# 길이가 1인 경우, 팰린드롬 확인
for i in range(n):
    pal[i][i] = True
# 길이가 2 이상인 경우, 팰린드롬 확인
for end in range(1, n):
    for start in range(end):
        # 앞, 뒤 문자열이 다르면 무조건 팰린드롬 X
        if s[start] != s[end]: continue
        # 문자열 길이가 2인 경우
        if start + 1 == end: pal[start][end] = True
        # 문자열 길이가 2보다 크고, 그 사이 문자열이 팰린드롬인 경우
        elif pal[start + 1][end - 1]: pal[start][end] = True
# 부분 문자열이 팰린드롬인 경우, 그 문자열의 바로 이전까지의 dp 값(dp[start - 1])에 1을 더한 값과 현재 dp[end] 값 중 최솟값으로 dp[end] 업데이트
for end in range(n):
    for start in range(end + 1):
        if pal[start][end]: dp[end] = min(dp[end], dp[start - 1] + 1)
print(dp[n-1])