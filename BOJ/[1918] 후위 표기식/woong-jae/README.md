# [1918] 후위 표기식
## Algorithm
- Stack
## Logic
- 현재 문자가 알파벳이거나 `(`면 스택에 넣는다.
- 아니라면 스택에서 우선순위가 낮은 연산자를 만나기 전까지 `pop`을 한 것을 `result`에 저장한다.
  - 이때, `(`를 만나면 `result`에 추가하지 않고 끝낸다.
  
```js
  const changeToPostorder = inorder => {
        const result = [];

        const stack = [];
        for (const char of inorder) {
            if (alphabet_regex.test(char) || char === "(") {
                stack.push(char);
                continue;
            }

            while (stack.length) {
                const top = stack.length - 1;
                if (priority.get(stack[top]) < priority.get(char)) break;

                const temp = stack.pop();
                if (temp === "(") break;

                result.push(temp);
            }
            if (char !== ")") stack.push(char);
        }

        while (stack.length) {
            result.push(stack.pop());
        }

        return result.join("");
    }
```

## Review
맞왜틀? 오랜만에 풀어서 고생좀 했다...
