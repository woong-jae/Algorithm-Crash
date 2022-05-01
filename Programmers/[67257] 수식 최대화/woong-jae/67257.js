function solution(expression) {
    let answer = 0;
    const operand = { "*": 0, "+": 1, "-": 2 };
    const priorities = [[0, 1, 2], [1, 0, 2], [1, 2, 0], 
                        [0, 2, 1], [2, 0, 1], [2, 1, 0]];

    expression = expression.split("");
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
    
    return answer;
}

function calc(a, operand, b) {
    if(operand === "*") return a * b;
    if(operand === "+") return a + b;
    return a - b;
}