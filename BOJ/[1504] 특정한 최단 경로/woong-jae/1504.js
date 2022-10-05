const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

const input = [];
rl.on('line', function (line) {
  input.push(line.split(" "));
}).on("close", function () {
  const [N, E] = input[0].map(e => +e);
  const edges = input.slice(1, 1 + E).map(row => row.map(e => +e));
  const [v1, v2] = input[1 + E].map(e => +e - 1);
  console.log(solution(N, edges, v1, v2));
  process.exit();
});

function solution(N, edges, v1, v2) {
  const dist = Array.from(Array(N), () => Array(N).fill(Infinity));
  edges.forEach(([from, to, cost]) => {
    dist[from - 1][to - 1] = cost;
    dist[to - 1][from - 1] = cost;
  });
  for (let i = 0; i < N; i++) dist[i][i] = 0;

  for (let k = 0; k < N; k++) {
    for (let i = 0; i < N; i++) {
      for (let j = 0; j < N; j++) {
        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
      }
    }
  }

  const result = Math.min(dist[0][v1] + dist[v1][v2] + dist[v2][N - 1], dist[0][v2] + dist[v2][v1] + dist[v1][N - 1]);
  return isFinite(result) ? result : -1;
}