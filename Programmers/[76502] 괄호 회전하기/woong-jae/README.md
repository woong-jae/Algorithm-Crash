# [76502] 괄호 회전하기

## Algorithm

- Stack

## Logic

각 회전시킨 문자열에 대해 올바른 괄호인지 확인한다.

문자열의 각 문자들을 stack에 넣어주면서, 맞는 짝이 나오면 stack에서 pop해준다.
Stack이 비면 올바른 괄호열이란 의미다.

```js
const isCorrect = (s) => {
  const stack = [];
  for (let char of s) {
    const top = stack.length - 1;
    if (top >= 0 && pair.get(stack[top]) === char) {
      stack.pop();
    } else {
      stack.push(char);
    }
  }
  return stack.length === 0;
};

let result = 0;
for (let i = 0; i < s.length; i++) {
  if (isCorrect(rotatedString(i))) result++;
}
```

## Review
Stack을 사용하는 정석적인 문제인 것 같다. 쉽게 풀 수 있었다.