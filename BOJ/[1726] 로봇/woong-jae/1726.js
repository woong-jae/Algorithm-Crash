const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

const input = [];
rl.on('line', function (line) {
  input.push(line.split(' '));
}).on("close", function () {
  console.log(solution());
  process.exit();
});

function solution() {
  const [M, N] = input[0].map(e => +e);
  const board = input.slice(1, M + 1).map(row => row.map(e => +e));
  const dr = [-1, 0, 1, 0], dc = [0, 1, 0, -1];
  const faceToDir = [1, 3, 2, 0];
  const [from, to] = input.slice(M + 1).map(([r, c, face]) => [+r - 1, +c - 1, faceToDir[+face - 1]]);

  const valid = (r, c) => {
    if (0 <= r && r < M && 0 <= c && c < N && board[r][c] === 0) return true;
    return false;
  }

  const spin = (from, to) => {
    if (from === to) return 0;
    if ((from + 2) % 4 === to) return 2;
    return 1;
  }

  const shortestCommand = Array.from(Array(M), () => Array.from(Array(N), () => Array(4).fill(Infinity)));
  const q = [[...from, 0]];
  shortestCommand[from[0]][from[1]][from[2]] = 0;
  while (q.length) {
    const [r, c, d, w] = q.shift();
    if (w > shortestCommand[r][c][d]) continue;

    // 직진
    for (let dx = 1; dx <= 3; dx++) {
      const nr = r + dr[d] * dx, nc = c + dc[d] * dx;
      if (!valid(nr, nc)) break;
      if (w + 1 >= shortestCommand[nr][nc][d]) continue;

      shortestCommand[nr][nc][d] = w + 1;
      q.push([nr, nc, d, w + 1]);
    }

    // 회전
    for (let dir = 0; dir < 4; dir++) {
      const nw = spin(d, dir) + w;
      if (nw >= shortestCommand[r][c][dir]) continue;

      shortestCommand[r][c][dir] = nw;
      q.push([r, c, dir, nw]);
    }
  }

  return shortestCommand[to[0]][to[1]][to[2]];
}