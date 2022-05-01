function getPermutaion(arr, count) {
  if (count === 1) return [arr];

  const result = [];
  arr.forEach((elem, i) => {
    const rest = [...arr.slice(0, i), ...arr.slice(i + 1)];
    const permutations = getPermutaion(rest, count - 1);
    const attached = permutations.map((p) => [elem, ...p]);
    result.push(...attached);
  });

  return result;
}

function calc(operator, a, b) {
  if (operator === '*') {
    return a * b;
  } else if (operator === '+') {
    return a + b;
  }
  return a - b;
}

function solution(expression) {
  const expArr = [];
  const isOperator = { '*': false, '+': false, '-': false };

  // 문자열을 배열로 변환
  let num = '';

  expression.split('').forEach((op) => {
    if (['*', '+', '-'].includes(op)) {
      expArr.push(+num);
      expArr.push(op);
      isOperator[op] = true;
      num = '';
    } else {
      num += op;
    }
  });
  expArr.push(+num);

  // 주어진 연산자 확인
  const operators = [];

  if (isOperator['*']) operators.push('*');
  if (isOperator['+']) operators.push('+');
  if (isOperator['-']) operators.push('-');

  // 연산자 우선순위 순열을 통해 모든 경우 확인
  const permutations = getPermutaion(operators, operators.length);
  let answer = 0;

  permutations.forEach((permutation) => {
    let copyExpArr = [...expArr];

    permutation.forEach((p) => {
      let stack = [];
      let operator = null;

      copyExpArr.forEach((element) => {
        if (element === p) {
          operator = p;
        } else if (operator) {
          stack.push(calc(operator, stack.pop(), element));
          operator = null;
        } else {
          stack.push(element);
        }
      });

      copyExpArr = stack;
    });

    answer = Math.max(Math.abs(copyExpArr[0]), answer);
  });

  return answer;
}