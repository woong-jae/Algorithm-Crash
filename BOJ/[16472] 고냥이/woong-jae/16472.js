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

function solution([N, string]) {
    const map = new Map();
    let maxLength = 0;
    let left = 0, right = 0;
    while(1) {
        if(right >= string.length) {
            maxLength = Math.max(maxLength, right - left);
            break;
        }
        const rightChar = string[right++];
        if(!map.has(rightChar)) map.set(rightChar, 0);
        map.set(rightChar, map.get(rightChar) + 1);
        if(map.size > N) {
            maxLength = Math.max(maxLength, right - left - 1);
            while(left < right && map.size > N) {
                const leftChar = string[left++];
                map.set(leftChar, map.get(leftChar) - 1);
                if(map.get(leftChar) === 0) map.delete(leftChar);
            }
        }
    }

    console.log(maxLength);
}