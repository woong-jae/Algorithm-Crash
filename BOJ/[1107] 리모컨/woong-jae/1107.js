const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const input = [];
rl.on('line', function (line) {
    input.push(...line.split(" "));
}).on("close", function () {
    solution(input[0], input[1], input.slice(2));
    process.exit();
});

function solution(N, M, broken_btns) {
    const isBrokenBtn = (() => {
        const mask = broken_btns.reduce((acc, btn) => acc | (1 << +btn), 0);
        return (btn) => (mask & (1 << btn)) > 0
    })();

    const calcPress = channel => {
        return String(channel).length + Math.abs(+N - channel);
    }

    const getMinPress = channel => {
        if (channel > 1000000) return Infinity;

        let result = calcPress(channel);
        for (let btn = channel === 0 ? 1 : 0; btn < 10; btn++) {
            if (isBrokenBtn(btn)) continue;
            result = Math.min(result, getMinPress(channel * 10 + btn));
        }
        return result;
    }

    let result = Math.abs(+N - 100);
    for (let i = 0; i < 10; i++) {
        if (isBrokenBtn(i)) continue;
        result = Math.min(result, getMinPress(i));
    }

    console.log(result);
}