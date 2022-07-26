const readline = require('readline');
const input = [];

readline.createInterface({
    input: process.stdin,
    output: process.stdout
}).on('line', (line) => {
    input.push(line);
}).on('close', () => {
    solution(input);
    process.exit();
});

function solution([_, elements, S]) {
    elements = elements.split(" ").map(elem => +elem);
    S = +S;

    for(let left = 0; left < elements.length; left++) {
        let target = left;
        for(let right = left + 1; right <= left + S && right < elements.length; right++) {
            if(elements[target] < elements[right]) {
                target = right;
            }
        }
        if(target === left) continue;
        for(let i = target; i > left; i--) {
            [elements[i], elements[i - 1]] = [elements[i - 1], elements[i]];
        }
        S -= target - left;
        if(S === 0) break;
    }

    console.log(...elements);
}