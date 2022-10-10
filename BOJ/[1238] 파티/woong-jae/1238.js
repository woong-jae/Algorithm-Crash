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
    const [N, _, X] = input.shift().map(e => +e);
    const dist = Array.from(Array(N), () => Array(N).fill(Infinity));
    input.forEach(edge => {
        let [from, to, weight] = edge.map(e => +e);
        from--;
        to--;
        dist[from][to] = weight;
    });

    for (let i = 0; i < N; i++) dist[i][i] = 0;

    for (let k = 0; k < N; k++) {
        for (let i = 0; i < N; i++) {
            for (let j = 0; j < N; j++) {
                dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }

    let max_dist = -1;
    for (let i = 0; i < N; i++) {
        max_dist = Math.max(max_dist, dist[i][X - 1] + dist[X - 1][i]);
    }
    return max_dist;
}