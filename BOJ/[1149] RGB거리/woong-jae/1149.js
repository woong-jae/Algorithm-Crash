const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

const input = [];
rl.on('line', function (line) {
  input.push(line.split(" "));
}).on("close", function () {
  console.log(solution(+input[0][0], input.slice(1).map(row => row.map(e => +e))));
  process.exit();
});

function solution(N, costs) {
  const dp = Array.from(Array(N), () => Array(3).fill(Infinity));
  for (let i = 0; i < 3; i++) {
    dp[0][i] = costs[0][i];
  }

  for (let i = 1; i < costs.length; i++) {
    for (let c = 0; c < 3; c++) {
      dp[i][c] = Math.min(dp[i - 1][(c + 1) % 3], dp[i - 1][(c + 2) % 3]) + costs[i][c];
    }
  }

  return Math.min(...dp[N - 1]);
}