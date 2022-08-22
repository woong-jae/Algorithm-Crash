function solution(S, K) {
  S = S.replace(/\d/g, '');
  return +S.includes(K);
}

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const input = [];

rl.on('line', (line) => {
  input.push(line);
  input.length === 2 && rl.close();
}).on('close', () => {
  console.log(solution(...input));
  process.exit();
});
