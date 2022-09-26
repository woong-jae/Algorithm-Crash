const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const input = [];
rl.on('line', function (line) {
    input.push(line.split(' '));
}).on("close", function () {
    solution(...input[0].map(value => parseInt(value)));
    process.exit();
});

function solution(N, L, R) {
    const mod = 1000000007;

    const dp = Array.from(Array(N + 1), () => Array.from(Array(N + 1), () => Array(N + 1).fill(0)));
    dp[1][1][1] = 1;
    for(let length = 2; length <= N; length++) {
        for(let l = 1; l <= length; l++) {
            for(let r = 1; r <= length; r++) {
                dp[length][l][r] = (dp[length - 1][l - 1][r] + dp[length - 1][l][r - 1] + dp[length - 1][l][r] * (length - 2)) % mod;
            }
        }
    }

    console.log(dp[N][L][R]);
}