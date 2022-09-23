const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

const input = [];
rl.on('line', function (line) {
  input.push(line.split(' '));
}).on("close", function () {
  solution(parseInt(input[0][0]), parseInt(input[1][0]), input.slice(2).map(row => row.map(e => parseInt(e))))
  process.exit();
});

function solution(N, W, cases) {
  const calcDistance = (from, to) => {
    return from.reduce((acc, _, i) => acc + Math.abs(from[i] - to[i]), 0);
  }

  const cache = new Map();
  const dp = (car1, car2, caseIndex) => {
    if (caseIndex === cases.length) {
      return 0;
    }
    const key = `${car1}, ${car2}, ${caseIndex}`;
    if (cache.has(key)) return cache.get(key)[0];

    const currentCase = cases[caseIndex];

    const car1Position = car1 === -1 ? [1, 1] : cases[car1];
    const car2Position = car2 === -1 ? [N, N] : cases[car2];

    const car1MovingDist = calcDistance(car1Position, currentCase) + dp(caseIndex, car2, caseIndex + 1);
    const car2MovingDist = calcDistance(car2Position, currentCase) + dp(car1, caseIndex, caseIndex + 1);

    const result = Math.min(car1MovingDist, car2MovingDist);
    cache.set(key, [result, car1MovingDist < car2MovingDist ? 1 : 2]);
    return result;
  }

  const result = dp(-1, -1, 0);

  let car1 = -1, car2 = -1;
  const path = [];
  for (let i = 0; i < W; i++) {
    const key = `${car1}, ${car2}, ${i}`;
    const picked = cache.get(key)[1];
    path.push(picked);
    picked === 1 ? car1 = i : car2 = i;
  }

  console.log(result + "\n" + path.join("\n"));
}