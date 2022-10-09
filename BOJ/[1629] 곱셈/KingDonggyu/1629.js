function solution(A, B, C) {
  A = BigInt(A);
  C = BigInt(C);

  const power = (exp) => {
    if (exp === 0) {
      return 1;
    }
    if (exp === 1) {
      return A % C;
    }
    const x = power(Math.floor(exp / 2)) % C;
    return exp % 2 ? (x * x * A) % C : (x * x) % C;
  }

  return Number(power(B));
}

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

rl.on('line', (line) => {
  const answer = solution(...line.split(' ').map((x) => +x));
  console.log(answer);
  rl.close();
}).on('close', () => {
  process.exit();
});
