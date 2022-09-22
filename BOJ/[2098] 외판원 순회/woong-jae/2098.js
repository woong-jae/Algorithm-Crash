const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const input = [];
rl.on('line', function (line) {
    input.push(line.split(' '));
}).on("close", function () {
    console.log(solution(parseInt(input[0][0]), input.slice(1)));
    process.exit();
});

function solution(n, costMatrix) {
    const adjMatrix = costMatrix.map(row => row.map(e => e === "0" ? Infinity : parseInt(e)));

    const cache = new Map();
    const dp = (start, cur, visited) => {
        if(visited === (2 ** n) - 1) {
            return adjMatrix[cur][start];
        }
        const key = `${start}, ${cur}, ${visited}`;
        if(cache.has(key)) return cache.get(key);

        let result = Infinity;
        adjMatrix[cur].forEach((cost, next) => {
            if(visited & (1 << next)) return;
            result = Math.min(result, cost + dp(start, next, visited | (1 << next)));
        });

        cache.set(key, result);
        return result;
    }

    return dp(0, 0, 1);
}