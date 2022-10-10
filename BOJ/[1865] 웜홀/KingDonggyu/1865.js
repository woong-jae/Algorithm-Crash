function hasNagative(N, edges, start) {
  const minTime = Array(N + 1).fill(10000);
  minTime[start] = 0;
  for (let i = 0; i < N; i++) {
    for (const [src, dst, time] of edges) {
      if (minTime[src] + time < minTime[dst]) {
        minTime[dst] = minTime[src] + time;
        if (i === N - 1) {
          return true;
        }
      }
    }
  }
  return false;
}

function solution(N, roadInfo, wormholeInfo) {
  const edges = [];
  roadInfo = roadInfo.forEach((str) => {
    const [src, dst, time] = str.split(' ').map((c) => +c);
    edges.push([src, dst, time]);
    edges.push([dst, src, time]);
  });
  wormholeInfo = wormholeInfo.forEach((str) => {
    const [src, dst, time] = str.split(' ').map((c) => +c);
    edges.push([src, dst, -time]);
  });
  return hasNagative(N, edges, 1) ? 'YES' : 'NO';
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
  let i = 1;
  for (let TC = +input[0]; TC > 0; TC--) {
    const [N, M, W] = input[i++].split(' ').map((c) => +c);
    const roadInfo = input.slice(i, (i += M));
    const wormholeInfo = input.slice(i, (i += W));
    console.log(solution(N, roadInfo, wormholeInfo));
  }
  process.exit();
});
