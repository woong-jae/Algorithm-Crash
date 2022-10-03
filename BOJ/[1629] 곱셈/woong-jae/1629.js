const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const input = [];
rl.on('line', function (line) {
    input.push(line.split(" "));
}).on("close", function () {
    console.log(solution(...input[0].map(e => +e)));
    process.exit();
});

function solution(A, B, C) {
    A = BigInt(A);
    C = BigInt(C);
    const dnq = (exponent) => {
        if(exponent === 1) return A % C;

        const half = dnq(Math.floor(exponent / 2)) % C;
        if(exponent % 2 === 0) return half * half % C;
        return half * half * A % C;
    }

    return dnq(B).toString();
}