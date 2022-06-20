# [12914] 멀리 뛰기
## Algorithm
- DP
## Logic
이전 값들을 누적해서 N일 때 몇가지 경우를 가질 수 있는지 구한다.
```js
function solution(n) {
    const dp = Array(n + 1).fill(0);
    dp[0] = 1;
    for(let i = 0; i < n; i++) {
        if(i + 1 <= n) dp[i + 1] = (dp[i + 1] + dp[i]) % 1234567;
        if(i + 2 <= n) dp[i + 2] = (dp[i + 2] + dp[i]) % 1234567;
    }
    return dp[n];
}
```
## Review
가장 기본적인 DP 문제.