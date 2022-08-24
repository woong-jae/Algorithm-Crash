# [12929] 올바른 괄호의 갯수

## Algorithm

- Recursion

## Logic

아래 점화식을 통해 재귀 호출을 수행한다.

```js
if (open > 0) {
  result += _solution(v + 1, open - 1, close);
}

if (close > 0) {
  result += _solution(v - 1, open, close - 1);
}
```

- `v`는 잘못된 괄호를 식별하기 위한 수이다.

- 열린 괄호 삽입

  - `v`에 1을 더한다.
  - `open`(삽입해야 할 열린 괄호의 개수)에 1을 뺀다.
  
- 닫힌 괄호 삽입

  - `v`에 1을 뺀다.
  - `close`(삽입해야 할 닫힌 괄호의 개수)에 1을 뺀다.

<br />

아래 베이스 케이스를 통해 재귀 호출을 종료한다.

```js
if (!open && !close) {
  return 1;
}

if (v < 0) {
  return 0;
}
```

- `open`, `close` 모두 0일 경우 1을 리턴한다.
- `v`가 음수일 경우, 잘못된 괄호로 간주하여 0을 리턴한다.

### 시간 복잡도 : O(2^logn)

## Review

쉬운 문제
