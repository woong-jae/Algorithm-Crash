function solution(a) {
  const n = a.length;
  let min = Infinity;

  for (let i = 0; i < n; i++) {
    min = Math.min(min, a[i]);
    a[i] = [a[i], min];
  }

  let answer = 0;
  min = Infinity;

  for (let i = n - 1; i > -1; i--) {
    min = Math.min(min, a[i][0]);
    if (a[i][0] <= a[i][1] || a[i][0] <= min) {
      answer++;
    }
  }

  return answer;
}

console.log(solution([-16, 27, 65, -2, 58, -92, -71, -68, -61, -33]));
