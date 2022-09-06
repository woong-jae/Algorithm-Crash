# [12929] 올바른 괄호의 갯수

## Algorithm

- Recursion

## Logic

아래 함수를 통해 재귀를 수행한다.

```js
function _solution(open, close) {
  if (!open) {
    return 1;
  }

  if (close < open) {
    return 0;
  }

  return _solution(open - 1, close) + _solution(open, close - 1);
}
```

### 시간 복잡도 : O(2^logn)

## Review

쉬운 문제
