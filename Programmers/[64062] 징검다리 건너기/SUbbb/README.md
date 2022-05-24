# [64062] 징검다리 건너기

## Algorithm
- 이분 탐색

## Logic

```java
public int crossBridge(int[] stones, int k) {
    int friends = 0;
    int min = Integer.MAX_VALUE;
    int max = 0;

    // min과 max 사이에 건널 수 있는 최대 인원 수가 있음
    for (int stone : stones) {
        if (stone < min) min = stone;
        if (stone > max) max = stone;
    }

    while (min <= max) {
        int mid = (min + max)/2;

        // 건널 수 있다면, min을 1 증가시키고 반복
        if (canCrossOver(stones, k, mid)) {
            min = mid + 1;
            friends = mid;
        } else {
            max = mid - 1;
        }
    }

    return friends;
}
```
- `stones` 에서 최솟값, 최댓값을 찾아 이분탐색
- `canCrossOver()` 로 `mid` 명이 건널 수 있는지 확인
    - 건널 수 있다면 `min` 을 update
    - 건널 수 없다면 `max` 를 update

## Review
- 0이 `K` 개 연속이라면, 더 이상 건널 수 없다는 큰 조건을 쉽게 알 수 있었으나, 효율성 테스트가 있는 것으로 보았을 때, 어떤 알고리즘을 사용해야 하는지 감이 오지 않았다.
- 게시판에서 이분 탐색을 사용한다는 것을 보았고, 코드를 대강 참고하였다. 보고 나니 왜 이분 탐색을 사용해야 하는지를 이해할 수 있었다.