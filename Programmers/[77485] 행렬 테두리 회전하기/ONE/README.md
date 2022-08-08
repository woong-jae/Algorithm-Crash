# [77485] 행렬 테두리 회전하기
## Algorithm
- **구현**

## Logic
- 배열을 테두리만 시계방향으로 돌리는데 **반시계 방향**으로 현재값을 이전의 값으로 채워 나간다
  - 처음 시작값(x1,y1)은 마지막에 다음값(x1,y1 + 1)에 넣어줘야함으로 임시로 저장했다가 마지막에 넣어준다
- 채워나가면서 테두리의 값들을 비교해 최솟값을 갱신한다

```java
private int rotation(int x1, int y1, int x2, int y2) {

    int min = map[x1][y1];
    int temp = map[x1][y1];

    // 좌변
    for (int x = x1; x < x2; x++) {
        min = Math.min(min, map[x + 1][y1]);
        map[x][y1] = map[x + 1][y1];
    }

    // 하변
    for (int y = y1; y < y2; y++) {
        min = Math.min(min, map[x2][y + 1]);
        map[x2][y] = map[x2][y + 1];
    }

    // 우변
    for (int x = x2; x > x1; x--) {
        min = Math.min(min, map[x - 1][y2]);
        map[x][y2] = map[x - 1][y2];
    }

    // 상변
    for (int y = y2; y > y1 + 1; y--) {
        min = Math.min(min, map[x1][y - 1]);
        map[x1][y] = map[x1][y - 1];
    }
    map[x1][y1 + 1] = temp;

    return min;
}
```

## Review
백준 마법사 상어와 토네이도 날리던 기억이 솔솔나던 문제
