function solution(N, M, broken) {
  let answer = Math.abs(+N - 100);
  
  if (M === '0') {
    return Math.min(answer, N.length);
  }

  N = +N;
  broken = broken.split(' ');

  for (let i = 0; i < 1000000; i++) {
    const channel = i.toString();
    if (channel.split('').find((x) => broken.includes(x))) {
      continue;
    }
    answer = Math.min(answer, Math.abs(N - i) + channel.length);
  }

  return answer;
}

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const input = [];

rl.on('line', (line) => {
  input.push(line);
}).on('close', () => {
  console.log(solution(...input));
  process.exit();
});
