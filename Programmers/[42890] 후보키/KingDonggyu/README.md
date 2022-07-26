# [42890] 후보키

## Algorithm

- combination

- set

## Logic

- 인덱스 값에 대한 조합 배열을 구한다.

```js
function isMinimal(keys, arr) {
  for (const key of keys) {
    if (key.every((k) => arr.includes(k))) {
      return false;
    }
  }
  return true;
}
```

- keys 배열을 통해 모든 key를 순회하며 조합 배열에 포함되어 있는지 확인한다.

  - 포함되어 있다면 최소성을 만족하지 못하는 것이므로 continue한다.

- 해당 조합의 값들을 이용하여 각 행에 대해 속성을 문자열 형식으로 더하고, set에 추가한다.

- set의 size가 relation의 길이와 같다면 조합 배열을 keys 배열에 push 한다. 그리고 answer + 1 한다.

### 시간 복잡도: O(N^2)

## Review

column의 길이가 최대 8인 것을 보고 조합을 사용해도 되겠다 판단했다.

최소성을 판단할 때 후보키들이 포함되어 있는지 확인해야하는 것을 간과하여 조금 헤맸다.
