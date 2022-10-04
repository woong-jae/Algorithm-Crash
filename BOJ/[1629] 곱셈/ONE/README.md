# [1629] 곱셈
## Algorithm
- **분할정복**, **수학**

## Logic
- 지수를 반으로 쪼개 분할하면서 수식을 수행
  - 짝수면 지수를 N / 2, N/ 2두개로 쪼개고
  - 홀수면 지수를 N/ 2, N / 2, N 3개로 분할한다
- 모듈러 연산에서 _(a * b) % c = (a % c * b % c) % c_ 의 공식을 이용

```java
private static long pow(long A, long exp, long C) {
    // 지수가 1인 경우 return
    if (exp == 1)
        return A % C;

    long temp = pow(A, exp / 2, C);

    // 현재 지수가 홀수 A^(exponent / 2) * A^(exponent / 2) * A
    if (exp % 2 == 1)
        return (temp * temp % C) * A % C;
    return temp * temp % C;
}
```

## Review
수학 모듈러 공식을 알아야 풀수 있는 문제 같았다... 나는 참고해서 풀었다 어렵네...