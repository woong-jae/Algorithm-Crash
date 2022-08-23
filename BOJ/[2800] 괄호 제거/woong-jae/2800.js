const readline = require('readline');
const input = [];

readline.createInterface({
    input: process.stdin,
    output: process.stdout
}).on('line', (line) => {
    input.push(line);
}).on('close', () => {
    console.log(solution(...input));
    process.exit();
});

function solution(s) {
    const braces = [];

    const stack = [];
    for (let i = 0; i < s.length; i++) {
        const char = s[i];
        if (char === "(") {
            stack.push(i);
        }
        else if (char === ")") {
            const front = stack.pop();
            braces.push([front, i]);
        }
    }

    const combinations = [];
    const dfs = (i, picked) => {
        if (i >= braces.length) {
            combinations.push([...picked]);
            return;
        };

        picked.push(i);
        dfs(i + 1, picked);
        picked.pop();

        dfs(i + 1, picked);
    }
    dfs(0, []);

    combinations.pop();

    const removeBrace = (s, braceIndexes) => {
        s = s.split("");
        braceIndexes.forEach(index => {
            const [front, back] = braces[index];
            s[front] = null;
            s[back] = null;
        });
        return s.filter(char => char).join("");
    }

    return Array.from(
        new Set(combinations.map(braceIndexes => removeBrace(s, braceIndexes)))
    ).sort().join("\n");
}