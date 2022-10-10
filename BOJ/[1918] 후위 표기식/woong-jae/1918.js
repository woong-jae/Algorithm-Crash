const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const input = [];
rl.on('line', function (line) {
    input.push(line.split(" "));
}).on("close", function () {
    console.log(solution());
    process.exit();
});

function solution() {
    const inorder = input.shift()[0];
    const alphabet_regex = /[A-Z]/;
    const priority = new Map([
        ["+", 1],
        ["-", 1],
        ["*", 2],
        ["/", 2],
        ["(", 0],
        [")", 0],
    ]);

    const changeToPostorder = inorder => {
        const stack = [];
        let last_priority = 0;
        for (let i = 0; i < inorder.length; i++) {
            const char = inorder[i];
            if (alphabet_regex.test(char)) {
                stack.push(char);
                continue;
            }

            if (last_priority < priority.get(char) || char === "(") {
                stack.push(char);
                last_priority = priority.get(char);
                continue;
            }

            while (stack.length > 1) {
                const operand2 = stack.pop();
                const operator = stack.pop();
                if (operator === "(") {
                    stack.push(operand2);
                    break;
                };
                if (priority.get(operator) < priority.get(char)) {
                    stack.push(operator, operand2);
                    break;
                }
                const operand1 = stack.pop();
                stack.push(`${operand1}${operand2}${operator}`);
            }
            if (char !== ")") stack.push(char);
            last_priority = priority.get(char);
        }

        while (stack.length > 1) {
            const operand2 = stack.pop();
            const operator = stack.pop();
            const operand1 = stack.pop();
            stack.push(`${operand1}${operand2}${operator}`);
        }

        return stack[0];
    }

    return changeToPostorder(inorder);
}