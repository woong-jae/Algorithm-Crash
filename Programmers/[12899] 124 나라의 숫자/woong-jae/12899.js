function solution(n) {
  let answer = [];
  const num_map = [4, 1, 2];
  while(n > 0) {
      answer.push(num_map[n % 3]);
      n = Math.floor((n - 1) / 3);
  }
  
  return answer.reverse().join("");
}