function solution(s) {
  const answer = [0, 0];

  while(s !== '1') {
    const c = s.replace(/0/g, '').length;
    answer[1] += s.length - c;
    s = c.toString(2);
    answer[0]++;
  }

  return answer;
}