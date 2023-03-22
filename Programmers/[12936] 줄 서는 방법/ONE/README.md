# [12936] 줄 서는 방법
## Algorithm
- **Factorial**

## Logic
- 팩토리얼의 단계마다 넣어야 하는 숫자를 찾아가는 것
  - ex) n = 4,  k = 3 이라면
  - 첫 번째로 올 수 있는 수의 경우는 4 가지 이고 이는 전체 경우의 수 24 가지를 6(3 * 2 * 1)개로 나눈 것 과 같다
  - k의 값으로 4 가지로 분류된 부분을 찾아가고 이를 다음자리 수에 반복한다
- k를 한번 줄이는 것은 맨 처음 시작을 0이라고 생각해 분류하는 것을 / 연산하기 위해서이다

```java
k--;
int index = 0;
while (n > 0) {
    count /= n;
    int value = (int) (k / count);
    result[index++] = candidate.get(value);
    candidate.remove(value);
    k %= count;
    n--;
}
```

## Review
얼추 방법은 생각났는데 구현에 대해 어려워서 다른분의 코드를 참고했다  
쉬운 것 같지만 조금 어려웠던 문제
