# [17687] n진수 게임
## Algorithm
- **구현**

## Logic
- 최대 16진법까지 사용되기 때문에 `0 ~ 15(F)`를 저장할 Map을 생성
- 튜브가 말해야 하는 숫자 t개가 m명의 인원수 만큼 있기 때문에 만드는 문자열의 길이는 최대 m * t 만큼만 필요하다
- 숫자를 0부터 1씩 늘려가면서 해당 숫자에 대해 n진수를 위한 최대 지수를 구한다
  - 최대 지수를 구하면 몇자리를 문자열에 더하는 지 알 수 있다
  - 최대 지수를 이용해서 나누기 연산을 통해 문자열에 해당하는 숫자를 map에서 가져와 반복해서 더한다
  - 지수가 1일 때는 마지막 자리이기 때문에 나머지만 구해준다

```java
int number = 0;
while (sb.length() < m * t) {
    int k = maxExp(n, number);
    int tmp = number;

    for (int i = k; k > 0; k--) {
        if (i > 1)
            sb.append(map.get(tmp / (int) Math.pow(n, k - 1)));
        else
            sb.append(map.get(tmp % (int) Math.pow(n, k)));
        tmp = tmp % (int) Math.pow(n, k - 1);
    }
    number++;
}
```

## Review
아이디어 생각은 쉽게 났는데 능지 이슈 때문에 구현하는데 시간이 좀 걸렸다  
그래도 크게 어렵지는 않았던 문제
