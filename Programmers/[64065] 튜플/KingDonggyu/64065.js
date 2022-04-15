function solution(s) {
  const answer = [];
  const sumList = [];

  let sum = 0;
  let num = '';

  for (const ch of s.slice(1, s.length - 1)) {
    switch (ch) {
      case '}':
        sumList.push(sum + +num);
        break;
      case '{':
        sum = 0;
        break;
      case ',':
        sum += +num;
        num = '';
        break;
      default:
        num += ch;
    }
  }
  
  sumList.sort((a, b) => a - b);

  for (let i = 0; i < sumList.length; i++) {
    let element = i ? sumList[i] - sumList[i - 1] : sumList[i];
    answer.push(element);
  }

  return answer;
}