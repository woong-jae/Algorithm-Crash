function solution(N, A, S) {
  for (let x = 0; x < N; x++) {
    const max = { index: x, value: A[x] };

    for (let y = x + 1; y <= x + S; y++) {
      if (y >= N) break;

      if (max.value < A[y]) {
        max.index = y;
        max.value = A[y];
      }
    }

    for (let i = max.index; i > x; i--) {
      [A[i], A[i - 1]] = [A[i - 1], A[i]];
      S--;
    }

    if (!S) break;
  }

  return A.join(' ');
}

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const input = [];

rl.on('line', (line) => {
  input.push(line);
  if (input.length === 3) {
    rl.close();
  }
}).on('close', () => {
  console.log(
    solution(
      +input[0],
      input[1].split(' ').map((ch) => +ch),
      +input[2]
    )
  );
  process.exit();
});
