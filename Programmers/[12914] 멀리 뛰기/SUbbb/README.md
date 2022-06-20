# [12914] 멀리 뛰기

## Algorithm
- DP

## Logic

```java
for (int i = 2; i < n; i++)
    dp[i] = (dp[i - 2] + dp[i - 1]) % div;
```
- 점화식

## :black_nib: **Review**

- 쉬운 문제
- 처음에는 중복순열을 구해야 하나 고민했었다.