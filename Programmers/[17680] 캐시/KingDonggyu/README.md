# [17680] 캐시

## Algorithm

- Hash

## Logic

- Set에 city를 넣는다.

  - 만약, Set의 크기가 주어진 cacheSize보다 크면 Set의 맨 앞 원소를 제거한다.

- city가 이미 Set에 있는 경우, 해당 city를 Set에서 제거한 뒤, 다시 city를 넣는다.

### 시간복잡도: O(N)

## Review

Set을 사용했지만 중복된 원소가 없도록 하는 Set의 특성을 이용한 것은 아니다.

그저 Hash로 사용한 것이므로, Set 대신 Map을 사용해도 된다.

(둘 다 해봤는데, Set이 조금 더 빠르게 나왔다)

이번주 카카오 문제들은 전반적으로 쉬운 것 같다.