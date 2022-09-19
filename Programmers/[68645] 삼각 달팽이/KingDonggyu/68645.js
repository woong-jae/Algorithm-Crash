function solution(n) {
  const triangle = Array.from(Array(n), (_, i) => Array(i + 1));

  let number = 0;
  let [row, col] = [-1, 0];
  let count = n;

  while (count > 0) {
    for (let i = 0; i < count; i++) {
      triangle[++row][col] = ++number;
    }
    for (let i = 0; i < count - 1; i++) {
      triangle[row][++col] = ++number;
    }
    for (let i = 0; i < count - 2; i++) {
      triangle[--row][--col] = ++number;
    }
    count -= 3;
  }

  return triangle.flat();
}
