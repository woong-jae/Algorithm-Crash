# [67257] 수식 최대화
## Algorithm
- **Stack**
## Logic
모든 우선순위 조합에 대해 연산 결과를 구한 후 절댓값 취했을 때 최대값을 구한다.

### 연산하기
연산은 `numStack`과 `operandStack`을 통해 진행한다.

1. `numStack`에 숫자 두개를 넣고 `operandStack`에 연산자 하나를 넣어서 초기화한다.

2. 마지막 넣은 숫자 이후 `{연산자}{숫자}`를 구한다.

3. 구한 연산자를 `operandStack`의 top에 있는 연산자와 우선순위를 비교한다.
    구한 연산자가 **우선순위가 작거나 같다면**, top에 있는 연산자가 우선순위가 더 커질 때까지 스택에서 꺼내 계산한다.

4. 연산자와 숫자를 스택에 각각 넣어준다.

5. 2부터 반복.

```js
priorities.forEach(priority => {
    let operandStack = [], numStack = [];
    let cur = 0, prev = 0;
    // Stack 초기화
    while(!isNaN(expression[cur])) cur++;
    numStack.push(+expression.slice(prev, cur).join(""));
    operandStack.push(expression[cur]);
    prev = ++cur;
    while(!isNaN(expression[cur])) cur++;
    numStack.push(+expression.slice(prev, cur).join(""));
    prev = cur++;
    // 우선순위에 따라 계산
    while(1) {
        if(cur >= expression.length) {
            while(operandStack.length) {
                const b = numStack.pop(), a = numStack.pop();
                numStack.push(calc(a, operandStack.pop(), b));
            }
            let result = numStack.pop();
            answer = Math.max(Math.abs(result), answer);
            break;
        };
        while(cur < expression.length && !isNaN(expression[cur])) cur++;
        const nextOperand = expression[prev], nextNum = +expression.slice(prev + 1, cur).join("");
        
        while(operandStack.length && priority[operand[nextOperand]] <= priority[operand[operandStack[operandStack.length - 1]]]) {
            const b = numStack.pop(), a = numStack.pop();
            numStack.push(calc(a, operandStack.pop(), b));
        }
        operandStack.push(nextOperand);
        numStack.push(nextNum);
        prev = cur++;
    }
});
```

## Review
아이디어는 빨리 잡았는데 스택 사용하는 부분에서 한참 해맸다. 예전에 비슷한 알고리즘으로 계산기를 만들었던 기억이 있는데 너무 오래돼서 감을 잃은 것 같다. 다시 풀어봐야지...