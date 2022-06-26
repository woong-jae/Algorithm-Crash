# [12914] 멀리 뛰기 - Python

## 🔍 Algorithm
**DP**

## 💻 Logic

```Python
    dp = [0 for _ in range(n+1)]
    dp[0], dp[1] = 1, 1
    for i in range(2, n+1):
        dp[i] = dp[i-1] + dp[i-2]
```

## 📝 Review

간단한 DP 문제
