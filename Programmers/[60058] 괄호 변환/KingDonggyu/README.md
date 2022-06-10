# [60058] 괄호 변환

## Algorithm

- recursion

## Logic

문제에서 제시한 로직 그대로 구현했다.

```js
function getCorrectString(w, joined) {
  // 올바른 괄호 문자열 확인
  if (isCorrectString(w)) {
    return w;
  }

  // 두 균형잡힌 괄호 문자열 분할
  let { u, v } = splitBalancedString(w);

  // u가 올바른 괄호 문자열인지 확인
  if (isCorrectString(u)) {
    return getCorrectString(v, joined + u);
  }

  // v에 대해 재귀 수행
  v = getCorrectString(v, '');
  
  // 문자열 생성
  let str = '(' + v + ')';

  for (const bracket of u.slice(1, -1)) {
    str += bracket === '(' ? ')' : '(';
  }
  
  // 그대로 둔 올바른 문자열(u)에 이어 붙이기
  return joined + str;
}
```

### 시간 복잡도 : O(NlogN)

## Review

처음에 while 반복문을 이용하여 구현하려 했다가, 평소 약했던 재귀를 연습하고 싶어서 재귀로 구현했다.

문제에서 제시한 로직 그대로 구현하면 되었기에 어렵지 않았다.