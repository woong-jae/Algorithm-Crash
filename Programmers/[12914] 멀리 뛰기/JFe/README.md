# [12914] λ©λ¦¬ λ°κΈ° - Python

## π Algorithm
**DP**

## π» Logic

```Python
    dp = [0 for _ in range(n+1)]
    dp[0], dp[1] = 1, 1
    for i in range(2, n+1):
        dp[i] = dp[i-1] + dp[i-2]
```

## π Review

κ°λ¨ν DP λ¬Έμ 
