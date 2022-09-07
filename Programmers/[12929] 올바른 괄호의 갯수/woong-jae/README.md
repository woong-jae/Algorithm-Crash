# [12929] 올바른 괄호의 갯수

## Algorithm

- Stack

## Logic

괄호를 추가해가면서 모든 괄호를 사용했을 때의 개수를 센다.

```js
const stack = [];
const helper = (used) => {
  if (used === n) {
    result++;
    return;
  }

  if (used < n) {
    stack.push("(");
    helper(used + 1);
    stack.pop();
  }
  const top = stack.length - 1;
  if (top >= 0 && stack[top] === "(") {
    stack.pop();
    helper(used);
    stack.push("(");
  }
};
helper(0);
```

## Review
쉬운 문제... 이번주는 무난한 것 같다.