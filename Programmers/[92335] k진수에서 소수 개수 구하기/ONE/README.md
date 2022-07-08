# [92335] k진수에서 소수 개수 구하기
## Algorithm
- **Prime Number**

## Logic
- 숫자를 k진수로 변환한 문자열로 바꾼 후 0이 1개 이상인 것들을 모두 0 한개짜리로 바꾸고 0을 기준으로 토큰을 나눈다
- 바꾼 토큰을 long 으로 바꿔서 제곱근까지 나머지가 0이 되는것을 확인하여 소수를 판별

```java
public int solution(int n, int k) {
    int answer = 0;

    String[] tokens = Integer.toString(n, k).replaceAll("0+", "0").split("0");

    for (String token : tokens)
        if (isPrime(Long.parseLong(token)))
            answer++;

    return answer;
}

private boolean isPrime(long num) {
    if (num == 1)
        return false;
    for (long i = 2; i < Math.sqrt(num); i++)
        if (num % i == 0)
            return false;
    return true;
}
```

## Review
런타임에러에서 자료형변환때문에 조금 걸렸지만 금방풀수 있는 쉬운 문제
