const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const input = [];
rl.on('line', function (line) {
    input.push(line.split(' '));
}).on("close", function () {
    console.log(solution(parseInt(input[0])));
    process.exit();
});

function solution(N) {
    const cache = new Map();
    const dp = (cur, length, used) => {
        if (length <= 0) {
            return used === (2 ** 10) - 1 ? 1 : 0;
        }
        const key = `${cur}, ${length}, ${used}`;
        if(cache.has(key)) {
            return cache.get(key);
        }

        let result = 0;
        if (cur - 1 >= 0) {
            result = result + dp(cur - 1, length - 1, used | (1 << (cur - 1)));
        }
        if (cur + 1 < 10) {
            result = result + dp(cur + 1, length - 1, used | (1 << (cur + 1)));
        }

        result %= 1000000000;
        cache.set(key, result);
        return result;
    }

    let result = 0;
    for(let i = 1; i < 10; i++) {
        result = (result + dp(i, N - 1, 1 << i)) % 1000000000;
    }
    return result;
}