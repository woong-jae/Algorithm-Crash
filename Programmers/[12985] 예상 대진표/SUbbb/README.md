# [12985] 예상 대진표

## Algorithm
- 구현

## Logic

```java
while (nA != nB) {
    nA = (nA + 1) >> 1;
    nB = (nB + 1) >> 1;
    answer++;
}
```
- 쉬프트 연산을 사용해 다음 대진 번호를 정한다.

## :black_nib: **Review**
- 쉬운 문제였다. 다음 대진 번호를 정하는 반복의 종료 조건을 찾는데 시간이 좀 걸린 게 문제였다.