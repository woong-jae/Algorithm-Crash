# [1562] 계단 수 - Python

## 🔍 Algorithm
**DP**, **Bit Mask**

## 💻 Logic

```Python
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
```
- **비트 마스킹을 활용한 DP**  
    - `dp[i][j][k]`  
        `i` : 자릿수 / `j` : 해당 숫자(0 ~ 9) / `k` : 지금까지 포함된 숫자 (**bit mask**)  
    - 첫 자릿수로 1 ~ 9가 오는 경우, 1로 초기화 (첫 자릿수로 0은 불가능)  
    - 0이거나 9인 경우는 앞, 뒤 하나만 생각하면 되기 때문에 예외 처리  
    - 나머지 경우는 앞, 뒤 둘 다 생각해서 더한 다음 처리  


## 📝 Review

1년 전에 풀었었던 문제.  
그 때도 쉽지 않았지만 1년이 지나도 여전히 낯설다,, DP에 비트 마스킹은 불닭에 캡사이신 넣은 맛 아닐까,,