# [92344] 파괴되지 않은 건물

## Algorithm
- 누적합

## Logic

```java
private void fill(int r1, int c1, int r2, int c2, int deg) {
    attackOrHeal[r1][c1] += deg;
    if (c2 + 1 < cSize) attackOrHeal[r1][c2 + 1] += -deg;
    if (r2 + 1 < rSize) attackOrHeal[r2 + 1][c1] += -deg;
    if (r2 + 1 < rSize && c2 + 1 < cSize) attackOrHeal[r2 + 1][c2 + 1] += deg;
}
```

- 주어진 범위의 누적합 계산을 위해 범위 내의 배열 값을 누적합하는 함수

## Review
- 처음에는 `skill` 값을 다 읽고 최종적으로 `board` 에 증감할 값을 만들어두고 0보다 큰 경우의 개수를 세는 방식으로 구현했는데 당연히도 시간 초과가 났다.
- 여기서 더 이상 시간을 줄일 방법을 모르겠어서 카카오 풀이를 참고했다. 뭔가 비슷한 느낌이긴 한데, 누적합 배열을 사용해 시간복잡도를 줄일 수 있었다..
  - 그치만 이런 풀이는 누적합 문제를 가지고 놀 정도가 되어야 떠오를 것 같다....