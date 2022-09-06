# [12900] 2 x n 타일링
## Algorithm
- **DP**

## Logic
- 타일을 세우는 경우의 수는 피보나치 수열과 같다

```java
public int solution(int n) {
    // 1과 2의 부분합 -> 피보나치 배열의 규칙
    int[] fibonacci = new int[n + 1];
    fibonacci[0] = 1;
    fibonacci[1] = 1;

    for (int i = 2; i <= n; i++)
        fibonacci[i] = (fibonacci[i - 2] + fibonacci[i - 1]) % 1000000007;

    return fibonacci[n];
}
```

## Review
쉬운문제
