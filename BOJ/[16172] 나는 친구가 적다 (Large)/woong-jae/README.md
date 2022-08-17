# [16172] 나는 친구가 적다 (Large)

## Algorithm

- KMP

## Logic

1. S에 있는 모든 숫자를 빼준다.
2. K와 KMP 알고리즘을 통해 일치하는 문자열이 있는지 확인한다.

```js
const pi = getPartialMatch(K);
let begin = 0,
  matched = 0;
while (begin <= S.length - K.length) {
  if (matched < K.length && S[begin + matched] === K[matched]) {
    if (++matched === K.length) return 1;
  } else {
    if (matched === 0) begin++;
    else {
      begin += matched - pi[matched - 1];
      matched = pi[matched - 1];
    }
  }
}
```

## Review
처음에 무지성으로 풀었다가 시간초과가 나서 KMP 알고리즘을 썼다.
너무 오랜만에 KMP 알고리즘을 사용해봐서 예전에 정리해논걸 참고했다.
오랜만에 봐도 어려운 알고리즘인듯