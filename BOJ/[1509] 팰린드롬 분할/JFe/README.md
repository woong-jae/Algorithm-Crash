# [1509] 팰린드롬 분할 - Python

## 🔍 Algorithm
**DP**

## 💻 Logic

```Python
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
```
- **팰린드롬인지 확인**  
    - 길이가 1인 경우와 2 이상인 경우로 나눠서 팰린드롬인지 확인  
    - 길이가 2 이상인 경우  
        - 앞, 뒤 문자열이 다르면 무조건 팰린드롬 X  
        - 앞, 뒤 문자열이 같고, 문자열 길이가 2인 경우는 바로 팰린드롬  
        - 앞, 뒤 문자열이 같고, 문자열 길이가 2보다 크면 그 사이 문자열이 팰림드롬일 경우 해당 문자열도 팰린드롬  

---

```Python
# 부분 문자열이 팰린드롬인 경우, 그 문자열의 바로 이전까지의 dp 값(dp[start - 1])에 1을 더한 값과 현재 dp[end] 값 중 최솟값으로 dp[end] 업데이트
for end in range(n):
    for start in range(end + 1):
        if pal[start][end]: dp[end] = min(dp[end], dp[start - 1] + 1)
```
- **최소 분할 개수 체크**  
    - 반복문을 돌면서 해당 문자열이 팰린드롬인지 체크  
    - 문자열이 팰린드롬이면,  
        그 문자열의 바로 이전까지의 dp 값(`dp[start - 1]`)에 1을 더한 값과 현재 `dp[end]` 값 중 최솟값으로 `dp[end]` 업데이트  



## 📝 Review

이전에 풀었던 팰린드롬인지 확인하는 문제처럼 구현하니 너무 비효율적이었고, 팰린드롬 체크하는 것부터 다시 생각했다.  
전체 풀이에는 어려움을 느껴 다른 풀이를 참고했다,, DP,, 참고하자,,