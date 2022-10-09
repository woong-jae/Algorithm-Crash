function solution(N, E, V1, V2, edges) {
  const minDist = Array.from(
    Array(N + 1), 
    () => Array(N + 1).fill(Infinity)
  );

  edges.forEach(([u, v, dist]) => {
    minDist[u][v] = dist;
    minDist[v][u] = dist;
  });

  for (let k = 1; k < N + 1; k++) {
    for (let x = 1; x < N + 1; x++) {
      for (let y = 1; y < N + 1; y++) {
        if (x === y) {
          minDist[x][y] = 0;
          continue;
        }
        const newDist = minDist[x][k] + minDist[k][y];
        minDist[x][y] = Math.min(minDist[x][y], newDist);
      }
    }
  }

  const answer = Math.min(
    minDist[1][V1] + minDist[V1][V2] + minDist[V2][N],
    minDist[1][V2] + minDist[V2][V1] + minDist[V1][N]
  );

  return isFinite(answer) ? answer : -1;
}

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const input = [];
let maxCount;

rl.on('line', (line) => {
  input.push(line);
  if (input.length === 1) {
    maxCount = +input[0].split(' ')[1] + 2;
  } else if (input.length === maxCount) {
    rl.close();
  }
}).on('close', () => {
  const [N, E] = input[0].split(' ').map((x) => +x);
  const [V1, V2] = input[E + 1].split(' ').map((x) => +x);
  const edges = input.slice(1, -1).map((s) => s.split(' ').map((x) => +x));
  console.log(solution(N, E, V1, V2, edges));
  process.exit();
});
