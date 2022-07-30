function solution(n) {
  const map124 = [4, 1, 2];
  const answer = [];

  while (n) {
    answer.push(map124[n % 3]);
    n = Math.floor((n - 1) / 3);
  }

  return answer.reverse().join('');
}
