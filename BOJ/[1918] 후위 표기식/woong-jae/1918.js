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

    return changeToPostorder(inorder);
}
