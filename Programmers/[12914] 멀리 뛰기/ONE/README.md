# [12914] 멀리 뛰기
## Algorithm
- **DP**

## Logic
- 피보나치 수열의 점화식을 이

```java
public long solution(int n) {
    long[] answer = new long[n + 1];

    answer[0] = 1;
    answer[1] = 1;

    for (int i = 2; i <= n; i++)
        answer[i] = (answer[i - 2] + answer[i - 1]) % 1234567;

    return answer[n] % 1234567;
}
```

## Review
점화식을 이용하는 쉬운문제
