# [12900] 2 x n 타일링 - Python

## 🔍 Algorithm
**DP**

## 💻 Logic

```Python
def solution(n):
    dp = [0 for _ in range(n+1)]
    dp[1], dp[2] = 1, 2
    for i in range(3, n+1):
        dp[i] = (dp[i-1] + dp[i-2]) % 1000000007
    return dp[n]
```
- **피보나치 수열과 같은 방식**  


## 📝 Review

예전에 백준에서 같은 문제 풀었어서 빠르게 통과!