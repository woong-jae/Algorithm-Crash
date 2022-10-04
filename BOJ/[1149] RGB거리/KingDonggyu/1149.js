function solution(N, costs) {
  const colors = [0, 1, 2, 0, 1];
  const dp = Array.from(Array(N), () => [Infinity, Infinity, Infinity]);

  costs = costs.map((cost) => cost.split(' ').map((x) => +x));
  dp[0] = costs[0];

  for (let house = 1; house < N; house++) {
    [0, 1, 2].forEach((color) => {
      dp[house][color] = Math.min(
        dp[house - 1][colors[color + 1]] + costs[house][color],
        dp[house - 1][colors[color + 2]] + costs[house][color]
      );
    });
  }

  return Math.min(...dp[N - 1]);
}

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const input = [];

rl.on('line', (line) => {
  input.push(line);
  input.length - 1 === +input[0] && rl.close();
}).on('close', () => {
  console.log(solution(+input[0], input.slice(1)));
  process.exit();
});
