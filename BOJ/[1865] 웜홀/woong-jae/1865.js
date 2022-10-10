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
    const result = [];
    const TC = input.shift()[0];
    for (let i = 0; i < TC; i++) {
        const [N, M, W] = input.shift().map(e => +e);
        const edges = input.splice(0, M).map(([S, E, T]) => [+S - 1, +E - 1, +T]);
        const negative_edges = input.splice(0, W).map(([S, E, T]) => [+S - 1, +E - 1, +T]);

        const dist = Array(N).fill(10001);
        dist[0] = 0;
        for (let iter = 0; iter < N - 1; iter++) {
            edges.forEach(([S, E, T]) => {
                if (dist[S] > dist[E] + T) {
                    dist[S] = dist[E] + T;
                }
                if (dist[E] > dist[S] + T) {
                    dist[E] = dist[S] + T;
                }
            });
            negative_edges.forEach(([S, E, T]) => {
                if (dist[S] > dist[E] - T) {
                    dist[S] = dist[E] - T;
                }
            });
        }

        let hasNegativeCycle = false;
        for (let [S, E, T] of edges) {
            if (dist[S] > dist[E] + T || dist[E] > dist[S] + T) hasNegativeCycle = true;
        }
        for (let [S, E, T] of negative_edges) {
            if (dist[S] > dist[E] - T) hasNegativeCycle = true;
        }

        result.push(hasNegativeCycle ? "YES" : "NO");
    }

    return result.join("\n");
}