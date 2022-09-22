# [1509] 팰린드롬 분할
## Algorithm
- **DP**

## Logic
- 2차원 boolean 배열을 사용해 palindrome[i][j]에서 i 부터 j 까지의 팰린드롬 여부를 저장한다
- 점화식 : dp[i] = Math.min(dp[i], dp[j - 1] + 1)
  - j 부터 i가 팰린드롬 일 때, 이전에 저장된값에 + 1한 것과 i 위치에서의 개수의 값을 비교하여 최솟값을 i 에 저장
  - dp[i] : i까지 팰린드롬의 최소 개수

```java
checkPalindrome(str, len);

for (int i = 1; i <= len; i++) {
    for (int j = 1; j <= i; j++) {
        if (palindrome[j][i]) {
            dp[i] = Math.min(dp[i], dp[j - 1] + 1);
        }
    }
}
```

## Review
이번 문제 또한 아이디어를 생각해내지 못해 다른 사람의 코드를 참고하였다  
나는 DP에 너무 약한 것 같다... 더 많이 풀어보자
