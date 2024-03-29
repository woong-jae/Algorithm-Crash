# [1328] 고층 빌딩
## Algorithm
- DP
## Logic
- 특정 높이의 빌딩이 어디에 위치할지 고려할 필요없이, N-1개 빌딩들에서 왼쪽에 빌딩 넣기 or 오른쪽에 빌딩 넣기 or 사이에 빌디 넣기 세개의 경우를 고려해주면 됨.
- 예를 들어 N, L, R은
1. N-1, L-1, R에서 맨 왼쪽에 빌딩 하나 추가한 경우
2. N-1, L, R-1에서 맨 오른쪽에 빌딩 하나 추가한 경우
3. N-1, L, R에서 사이에 빌딩 추가한 경우 <- 양끝 2지점을 제외하고 N-2개의 경우가 발생
이 세가지 경우를 합해주면 됨.
```python
for i in range(2, N + 1):
    for j in range(1, L + 1):
        for k in range(1, R + 1):
            dp[i][j][k] = dp[i - 1][j][k] * (i - 2) + dp[i - 1][j - 1][k] + dp[i - 1][j][k - 1]
            dp[i][j][k] %= 1000000007
```

## Review
처음에 빌딩 높이를 어떻게 배치하냐에 너무 연연했던 것 같다. 도저히 답이 안나와서 풀이 봤음.. 