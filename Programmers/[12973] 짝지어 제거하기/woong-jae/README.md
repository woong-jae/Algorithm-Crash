# [12973] 짝지어 제거하기

## Algorithm

- Stack

## Logic

문자열의 각 문자를 stack에 집어 넣는데, 집어넣기 전에 stack의 top과 비교한다.

넣을 문자가 stack의 top과 같다면 넣지않고 stack을 pop한다.
같지 않다면 push 해준다.

```js
for (const char of s) {
  const top = stack.length - 1;
  if (top >= 0 && stack[top] === char) {
    stack.pop();
    continue;
  }
  stack.push(char);
}
```

## Review
쉬운 문제. 힐링된다.