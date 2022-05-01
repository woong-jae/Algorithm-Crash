## :mag: Algorithm

String

## :round_pushpin: Logic

```js
lexicon
  .set('zero', 0)
  .set('one', 1)
  .set('two', 2)
  .set('three', 3)
  .set('four', 4)
  .set('five', 5)
  .set('six', 6)
  .set('seven', 7)
  .set('eight', 8)
  .set('nine', 9);
```

주어진 숫자-영단어 매칭 표를 통해, key가 영단어이고 value가 숫자인 Map 자료구조로 생성한다.

```js
let answer = '';
let string = '';

for (const ch of s) {
  if (+ch || ch === '0') {
    answer += ch;
    continue;
  }

  string += ch;

  if (lexicon.has(string)) {
    answer += lexicon.get(string);
    string = '';
  }
}
```

주어진 문자열을 한 문자씩 순서대로 탐색한다.

- 문자를 숫자로 변환 가능할 경우, `answer`에 그대로 이어 붙인다.

- 그렇지 않을 경우, `string` 에 이어붙인다.

  - 만약 `string`이 `lexicon` Map의 key로 존재한다면, 해당 key의 value를 `answer`에 push 하고, `string`을 다시 빈 문자열로 초기화한다.

### 시간 복잡도: O(N)

## :memo: Review

첫번째 문제라 그런지 너무 쉬웠다.