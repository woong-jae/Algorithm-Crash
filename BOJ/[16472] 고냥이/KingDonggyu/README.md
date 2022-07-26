## :mag: Algorithm

Two Pointer

## :round_pushpin: Logic

**left와 right를 정의하여 사이에 N개 종류의 알파벳이 있는지 확인한다.**

```js
while (right < str.length) {
  // right 증가
  if (map.has(str[right])) {
    map.set(str[right], map.get(str[right]) + 1);
  } else {
    map.set(str[right], 1);
  }
  right++;

  // answer 업데이트
  if (map.size <= N) {
    answer = Math.max(answer, right - left);
    continue;
  }

  // left 증가
  while (map.size > N) {
    map.set(str[left], map.get(str[left]) - 1);
    if (!map.get(str[left])) {
      map.delete(str[left]);
    }
    left++;
  }
}
```

- right를 증가하며 알파벳 `str[right]`를 map에 추가 또는 업데이트한다.

- 만약, map의 크기가 N보다 크면, left를 증가혐 알파벳 `str[left]`를 map에 제거한다.

  - 단, map에 있는 알파벳 키의 값을 -1 하며, 0이 될 경우 제거한다.

- map의 크기가 N보다 같거나 작으면, 범위의 길이(right - left)를 answer와 비교하여 더 큰 것을 answer로 둔다.

- right가 문자열 인덱스 범위를 벗어날 때까지 이를 반복한다.

### 시간 복잡도 : O(N)

## :memo: Review

인식 가능한 알파벳의 종류의 개수가 주어지고, 이에 따른 범위를 구해야하는 것을 보고 지난번 프로그래머스 '보석 쇼핑' 문제가 떠올랐다.

그래서 투 포인터를 적용하여 문제를 쉽게 해결할 수 있었다.