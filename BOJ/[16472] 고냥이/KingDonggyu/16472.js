function solution(N, str) {
  let answer = 0;

  const map = new Map();
  let left = 0;
  let right = 0;

  while (right < str.length) {
    // right 증가
    if (map.has(str[right])) {
      map.set(str[right], map.get(str[right]) + 1);
    } else {
      map.set(str[right], 1);
    }
    right++;

    // answer 업데이트
    if (map.size <= N) {
      answer = Math.max(answer, right - left);
      continue;
    }

    // left 증가
    while (map.size > N) {
      map.set(str[left], map.get(str[left]) - 1);
      if (!map.get(str[left])) {
        map.delete(str[left]);
      }
      left++;
    }
  }

  return answer;
}

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

const input = [];

rl.on('line', (line) => {
  input.push(line);
  if (input.length === 2) {
    rl.close();
  }
}).on('close', () => {
  console.log(solution(...input));
  process.exit();
})