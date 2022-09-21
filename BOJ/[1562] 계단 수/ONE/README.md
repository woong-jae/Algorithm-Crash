# [1562] 계단 수
## Algorithm
- **DP**, **Bit Mask**

## Logic
- 비트마스크를 이용해 0~9의 숫자가 모두 포함된 수를 찾는다 (1111111111₂)
- k는 가지고 있는 수인 비트마스크를 의미하며 0은 아무것도 포함하지 않은 숫자 1023은 0~9까지 모두 포함한 숫자이다

```java
for (int i = 2; i <= N; i++) {
    for (int end = 0; end <= 9; end++) {
        for (int k = 0; k < 1024; k++) {
            int n = k | (1 << end);

            if (end == 0) {
                dp[i][end][n] = (dp[i][end][n] + dp[i - 1][end + 1][k]) % MOD;
            } else if (end == 9) {
                dp[i][end][n] = (dp[i][end][n] + dp[i - 1][end - 1][k]) % MOD;
            } else {
                dp[i][end][n] = (dp[i][end][n] + dp[i - 1][end + 1][k] + dp[i - 1][end - 1][k]) % MOD;
            }
        }
    }
}
```

## Review
내가 생각한 방식대로는 답이 나오지 않아 '코딩하는 자바맨'님의 코드를 참고 했다..
DP 문제도 어려운데 비트 마스크는 정말 모르겠다...  
좀 많이 풀어야 알것같다...