function solution(n, t, m, p) {
  const ruleNumbers = [];
  let number = 0;

  while (t * m > ruleNumbers.length) {
    ruleNumbers.push(...number.toString(n).split(''));
    number++;
  }

  let answer = '';
  
  while(t--) {
    answer += ruleNumbers[p - 1];
    p += m;
  }

  return answer.toUpperCase();
}

console.log(solution(16, 16, 2, 2));