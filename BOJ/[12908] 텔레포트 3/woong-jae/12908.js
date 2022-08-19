const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const input = [];
rl.on('line', function (line) {
    input.push(line.split(' '));
}).on("close", function () {
    console.log(solution());
    process.exit();
});

function solution() {
    const [xs, ys] = input[0].map(e => +e);
    const [xe, ye] = input[1].map(e => +e);
    const teleports = input.slice(2);

    const distance = (from, to) => {
        const xDistance = Math.abs(from[0] - to[0]);
        const yDistance = Math.abs(from[1] - to[1]);
        return xDistance + yDistance;
    }

    const minDistance = (from, to, used) => {
        let result = distance(from, to);

        teleports.forEach((teleport, i) => {
            if (used & (1 << i)) return;

            let [x1, y1, x2, y2] = teleport.map(e => +e);
            if (distance(from, [x1, y1]) > distance(from, [x2, y2])) {
                [x1, y1, x2, y2] = [x2, y2, x1, y1];
            }
            result = Math.min(result, distance(from, [x1, y1]) + 10 + minDistance([x2, y2], to, used | (1 << i)));
        });

        return result;
    }

    return minDistance([xs, ys], [xe, ye], 0);
}