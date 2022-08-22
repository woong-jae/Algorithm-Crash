function solution(N, M, factory, src, dst) {
  [src, dst] = [src, dst].map(([x, y, dir]) => {
    const arr = [null, 1, 3, 2, 0];
    return [x - 1, y - 1, arr[dir]];
  });

  const queue = [[...src, 0]];
  const direction = [[-1, 0], [0, 1], [1, 0], [0, -1]];
  const visited = Array.from(Array(N), () =>
    Array.from(Array(M), () => Array(4).fill(Infinity))
  );

  const isOutOfBounds = (x, y) => {
    return x < 0 || x >= N || y < 0 || y >= M;
  };

  const turnDir = (dir, i) => {
    let ndir = dir + i;
    if (ndir > 3) ndir = 0;
    if (ndir < 0) ndir = 3;
    return ndir;
  };

  visited[src[0]][src[1]][src[2]] = 0;

  while (queue.length) {
    const [x, y, dir, cmd] = queue.shift();

    // Go k
    for (const k of [1, 2, 3]) {
      const nx = x + direction[dir][0] * k;
      const ny = y + direction[dir][1] * k;

      if (isOutOfBounds(nx, ny) || factory[nx][ny]) {
        break;
      }

      if (visited[nx][ny][dir] > cmd + 1) {
        visited[nx][ny][dir] = cmd + 1;
        queue.push([nx, ny, dir, cmd + 1]);
      }
    }

    // Turn dir
    [1, -1].forEach((i) => {
      const ndir = turnDir(dir, i);
      if (visited[x][y][ndir] > cmd + 1) {
        visited[x][y][ndir] = cmd + 1;
        queue.push([x, y, ndir, cmd + 1]);
      }
    });
  }

  return visited[dst[0]][dst[1]][dst[2]];
}

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const input = [];

rl.on('line', (line) => {
  input.push(line.split(' ').map((x) => +x));
  input.length === input[0][0] + 3 && rl.close();
}).on('close', () => {
  const [N, M] = input[0];
  const factory = input.slice(1, -1);
  const [src, dst] = input.slice(-2);
  console.log(solution(N, M, factory, src, dst));
  process.exit();
});
