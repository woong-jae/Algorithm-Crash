function solution(mathExp) {
  let answer = new Set();

  function solutionInner(mathExp) {
    if (answer.has(mathExp)) {
      return;
    }
    
    answer.add(mathExp);
    mathExp = Array.from(mathExp);
    
    const stack = [];

    mathExp.forEach((ch, i) => {
      if (ch === '(') {
        stack.push(i);
        return;
      }
      if (ch === ')') {
        temp = [...mathExp];
        temp[stack.pop()] = temp[i] = '';
        solutionInner(temp.join(''));
      }
    });
  }

  solutionInner(mathExp);
  answer = Array.from(answer).sort();
  return answer.slice(1).join('\n');
}

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

rl.on('line', (line) => {
  console.log(solution(line));
  rl.close();
}).on('close', () => {
  process.exit();
});
