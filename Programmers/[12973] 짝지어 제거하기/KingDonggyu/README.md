# [12973] 짝지어 제거하기

## Algorithm

- Stack

## Logic

```js
for (const ch of s) {
  const top = stack.length - 1;
  if (top > -1 && stack[top] === ch) {
    stack.pop();
    continue;
  }
  stack.push(ch);
}
```

코드로 말한다.

## Review

감사합니다.