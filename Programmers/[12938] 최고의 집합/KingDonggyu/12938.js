function solution(n, s) {
  if (n > s) {
    return [-1];
  }

  const answer = [];

  while (n) {
    const value = Math.floor(s / n);
    answer.push(value);
    s -= value;
    n--;
  }

  return answer;
}
