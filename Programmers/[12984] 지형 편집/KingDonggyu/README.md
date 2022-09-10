# [12984] 지형 편집

## Algorithm

- Binary Search

## Logic

- 가장 낮은 층을 `left`, 가장 높은 `right`로 두어 이분 탐색을 수행한다.

  - `mid`, `mid + 1` 에 대해 각 비용 `cost1`, `cost2` 을 구한다.

  - `cost1`보다 `cost2`가 클 경우 right를 조정한다.

    - `answer = cost1`

  - `cost2`보다 `cost1`이 클 경우 left를 조정한다.

    - `answer = cost2`

  - `cost1`과 `cost2`가 같을 경우 break한다.

### 시간 복잡도 : O(NlogN)

## Review

이분 탐색 아이디어는 금방 떠올렸으나 효율성 4, 5에서 자꾸만 시간초과가 발생했다.

기존에 `cost1`과 `cost2`를 `getCost` 함수를 두번 호출하여 각각 구해줬었는데, 한번의 호출로 모두 구할 수 있도록 개선하니 통과했다.
