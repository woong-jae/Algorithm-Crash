function solution([xs, ys], [xe, ye], ...teleport) {
  const queue = [[xs, ys, 0]];
  const visited = teleport.reduce((map, [x1, y1, x2, y2]) => {
    map.set(`${x1} ${y1}`, Infinity);
    map.set(`${x2} ${y2}`, Infinity);
    return map;
  }, new Map());

  const jump = (x1, y1, x2, y2) => {
    return Math.abs(x1 - x2) + Math.abs(y1 - y2);
  };

  let answer = Infinity;

  while (queue.length) {
    const [x, y, time] = queue.shift();

    if (visited.get(`${x} ${y}`) < time) {
      continue;
    }

    visited.set(`${x} ${y}`, time);
    answer = Math.min(answer, time + jump(x, y, xe, ye));

    teleport.forEach(([x1, y1, x2, y2]) => {
      let t1 = time + jump(x, y, x1, y1) + 10;
      let t2 = time + jump(x, y, x2, y2) + 10;
      
      t1 < answer && queue.push([x2, y2, t1]);
      t2 < answer && queue.push([x1, y1, t2]);
    });
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
  input.push(line.split(' ').map((x) => +x));
  input.length === 5 && rl.close();
}).on('close', () => {
  console.log(solution(...input));
  process.exit();
});
