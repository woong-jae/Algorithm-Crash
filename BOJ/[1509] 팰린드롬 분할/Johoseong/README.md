# [1509] 팰린드롬 분할
## Algorithm
- DP
## Logic
DP를 두번 사용했다.
1. 구간별로 팰린드롬인지 아닌지를 판단하는데 `dp[시작 인덱스][끝 인덱스]`로 dp 사용.
- 길이를 1~N까지 늘려가면서 구간별 팰린드롬을 판별한다. 알파벳 1개면 무조건 팰린드롬이므로 처음에 초기화 함.
- 길이가 2인 팰린드롬은 그 두 알파벳이 같으면 팰린드롬임. 즉, `arr[i] == arr[i + 1]`면 팰린드롬임.
- 길이 3 이상 부터는 양 끝 알파벳이 같으면서, 그 안의 문자열도 팰린드롬이어야함. 이건 이미 `dp`에 기록돼있음!
```python
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
```
<br>

2. 1번에서 구한 `dp`를 사용해서, 전체 구간에서 최소 분할 수를 구하기 위해 `result[문자열 길이]`로 dp 사용.
- dp[i][j]이 팰린드롬이면? -> `min(원래 값, 이 팰린드롬 채택(=1) + j 이후 최소 분할 수)`
- dp[i][j]이 팰린드롬 아니면? -> `min(원래 값, i 한개 팰린드롬(=1) + i 이후 최소 분할 수)`
```python
for i in range(N - 2, -1, -1):
    for j in range(i, N):
        if dp[i][j] == 1:
            result[i] = min(result[i], result[j + 1] + 1)
        else:
            result[i] = min(result[i], result[i + 1] + 1)
```

## Review
과정2 구하는게 너무 오래걸렸다. 사실 과정2가 더 일반적인 dp에 가까웠는데 너무 복잡하게 생각한듯..