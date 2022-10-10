function solution(notation) {
  const stack = [];
  let answer = notation.split('').reduce((answer, ch) => {
    switch(ch) {
      case '(':
        stack.push(ch);
        break;
      case ')':
        while (stack.length && stack[stack.length - 1] !== '(') {
          answer += stack.pop();
        }
        stack.pop();
        break;
      case '/':
      case '*':
        while (stack.length && ['/', '*'].includes(stack[stack.length - 1])) {
          answer += stack.pop();
        }
        stack.push(ch);
        break;
      case '+':
      case '-':
        while (stack.length && stack[stack.length - 1] !== '(') {
          answer += stack.pop();
        }
        stack.push(ch);
        break;
      default:
        answer += ch;
    }
    return answer;
  }, '');

  while (stack.length) {
    answer += stack.pop();
  }

  return answer;
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
