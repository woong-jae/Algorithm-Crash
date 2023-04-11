# [118670] 행렬과 연산
## Algorithm
- **Dequeue**

## Logic
- 그냥 배열을 회전하거나 이동하는 연산을 수행한다면 효율성을 통과할 수 없다
- 행렬을 크게 3부분의 덱(Dequeue)으로 나누면 쉽게 행렬연산이 가능해진다
  1. 첫 번째 열 덱
  2. 마지막 번째 열 덱
  3. 두 열을 제외한 가운데 행 덱으로 이루어진 덱

- 세 덱으로 나눈 부분에 대해 아래와 같은 연산을 수행한다

- rotate 연산
```java
public void rotate() {
    centerRows.peekFirst().addFirst(leftCol.pollFirst());
    rightCol.addFirst(centerRows.peekFirst().pollLast());
    centerRows.peekLast().addLast(rightCol.pollLast());
    leftCol.addLast(centerRows.peekLast().pollFirst());
}
```

- shift row 연산
```java
public void shiftRow() {
    rightCol.addFirst(rightCol.pollLast());
    centerRows.addFirst(centerRows.pollLast());
    leftCol.addFirst(leftCol.pollLast());
}
```

## Review
카카오 공식사이트의 풀이를 이해하고 자바의 Dequeue를 사용해서 풀이했다  
스스로 생각하기엔 좀 어려웠던 것 같지만 다음번에 효율성을 생각해야하는 비슷한 유형의 문제가 나온다면 적용할 수 있을 것 같다