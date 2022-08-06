# [77886] 110 옮기기

## Algorithm

- Stack

## Logic

1. 주어진 문자열에서 "110"을 전부 뺀다.

```js
const delete110 = (x) => {
  const stack = [];
  let count = 0;

  for (const char of x) {
    stack.push(char);
    if (stack.length < 3) continue;

    const top = stack.length - 1;
    if (stack[top] === "0" && stack[top - 1] === "1" && stack[top - 2] === "1") {
      for (let i = 0; i < 3; i++) stack.pop();
      count++;
    }
  }
  return [stack.join(""), count];
};
```

2. 모든 "110"을 문자열에 넣어준다. 이때, 제일 마지막 "0" 뒤에 모두 넣어준다. "0"이 없다면 제일 앞에 넣어준다.

```js
const [filtered_x, count] = delete110(x);

const insertIndex = filtered_x.lastIndexOf("0") + 1;

return filtered_x.slice(0, insertIndex) + "110".repeat(count) + filtered_x.slice(insertIndex);
```

## Review

접근 방법이 완전히 틀렸어서 결국 답을 봤다. 처음에는 110을 찾고, 111과 치환하면 되는 줄 알았다...
바보다.
