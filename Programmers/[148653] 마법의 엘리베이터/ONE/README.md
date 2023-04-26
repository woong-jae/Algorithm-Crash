# [148653] 마법의 엘리베이터
## Algorithm
- **단순 구현**

## Logic
- 두 가지의 경우로 나누어 생각할 수 있다
  1. 현재 자리 숫자가 5보다 크거나 5인데 다음자리 숫자가 5보다 큰 경우
     - 이 때는 다음 자리의 수가 되기 위한 만큼 더해 주는 게 최소의 개수
  2. 그 외의 나머지 경우
     - 현재 자리 수만큼 빼는 것이 최소의 개수
```java
int numberOfCurrentUnit = storey % UNIT;
int upperNumber = storey / UNIT % UNIT;

if (numberOfCurrentUnit > 5 || numberOfCurrentUnit == 5 && upperNumber >= 5) {
    count += (10 - numberOfCurrentUnit);
    storey += (10 - numberOfCurrentUnit);
} else {
    count += numberOfCurrentUnit;
}
```


## Review
조금 생각이 필요했던 문제였다  
5에서 다른 조건이 필요하다는 것은 질문을 통해 참고했다!