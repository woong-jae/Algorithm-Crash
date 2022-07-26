# [92335] k진수에서 소수 개수 구하기

## Algorithm
- 소수 구하기

## Logic

```java
private static boolean isPrime(long n) {
    // 소수는 1과 자기 자신만으로 나누어지는 수
    // 따라서 2부터 루트 n까지의 수로 나누어보고, 나눠진다면 소수가 아니다.
    // (int) Math.sqrt(n) : 소수점 버림
    for (int i = 2; i <= (int) Math.sqrt(n); i++)
        if (n % i == 0)
            return false;

    return true;
}
```

- 소수인지 아닌지 판별하는 함수

## Review
- 소수를 구하는 알고리즘이 중요했다.
- 뭔가 0에 대해서 막 조건 설명을 늘어놓길래 .. 아 0으로 `split` 하면 되겠구나 싶었다.