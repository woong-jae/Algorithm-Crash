# [67258] 보석 쇼핑

## Algorithm
- 두 포인터

## Logic

```java
while (true) {
    // 아직 종류가 다 안 채워진 경우
    if (gemTypes != gemMap.size()) {
        if (right == gems.length) break;
        else {
            // 보석을 구매하는 경우로, Map에 개수를 증가시키면서 보석을 저장
            gemMap.put(gems[right], gemMap.getOrDefault(gems[right], 0) + 1);
            right++;
        }
    }

    // 종류가 다 채워진 경우
    if (gemTypes == gemMap.size()) {
        // 이전까지 구한 구간의 길이와 비교하여 작다면 update
        if (right - left < distance) {
            distance = right - left;
            start = left;
            end = right;
        }

        // 구간 줄이기 위해 제일 앞 보석 1개 삭제
        gemMap.put(gems[left], gemMap.get(gems[left]) - 1);

        // 보석 개수가 0개이면 map에서 삭제
        if (gemMap.get(gems[left]) == 0) gemMap.remove(gems[left]);

        left++;
    }
}
```

- `left` , `right` 라는 포인터를 사용하여 보석 진열대를 탐색한다.
- 보석 진열대에 놓인 모든 종류의 보석을 하나 이상 구매한 경우와, 그렇지 않은 경우로 나눈다.
  - 다 채워지지 않은 경우는 구매한 보석의 개수 정보를 업데이트하면서 `Map` 에 저장하고,
  - 다 채워진 경우는 최소 구간인지 확인하고 시작점부터 보석 개수를 하나씩 줄인다.

## Review
- 두 포인터를 사용해야 하는 문제임은 바로 알 수 있었지만, 두 포인터를 사용하지 않고 각 보석들의 위치를 저장하고 이 위치 정보만을 사용해서 풀 방법은 없을까를 고민해보았다.
- 두 포인터 알고리즘을 사용하는 가장 흔한 예가 부분 배열 합을 구하는 문제였는데, 이를 여기에 어떻게 접목시키는지 의문이었다.
- 두 포인터 알고리즘이 고냥이 문제에도 사용되던데, 한 번 혼자 풀어봐야겠다...