const makeMatrix = (x, y) => {
  const matrix = Array(x)
    .fill(1)
    .map(() => Array(y).fill(1));
  let cnt = 0;
  for (let i = 0; i < x; i++) {
    for (let j = 0; j < y; j++) {
      matrix[i][j] += i * y + j;
    }
  }
  // console.log(matrix);
  return matrix;
};

function solution(rows, columns, queries) {
  let matrix = makeMatrix(rows, columns);
  let answer = [];

  queries.forEach(query => {
    const [x1, y1, x2, y2] = query.map(pos => pos - 1);
    let queue = [];

    for (let i = 0; i < y2 - y1; i++) queue.push(matrix[x1][y1 + i]);
    for (i = 0; i < x2 - x1; i++) queue.push(matrix[x1 + i][y2]);
    for (i = 0; i < y2 - y1; i++) queue.push(matrix[x2][y2 - i]);
    for (i = 0; i < x2 - x1; i++) queue.push(matrix[x2 - i][y1]);
    // console.log(queue);
    queue.unshift(queue.pop());
    answer.push(Math.min(...queue));
    console.log(queue);

    for (i = 0; i < y2 - y1; i++) matrix[x1][y1 + i] = queue.shift();
    for (i = 0; i < x2 - x1; i++) matrix[x1 + i][y2] = queue.shift();
    for (i = 0; i < y2 - y1; i++) matrix[x2][y2 - i] = queue.shift();
    for (i = 0; i < x2 - x1; i++) matrix[x2 - i][y1] = queue.shift();
  });

  return answer;
}

console.log(solution(4, 3, [[[1, 1, 4, 3]]]));
// console.log(
//   solution(6, 6, [
//     [2, 2, 5, 4],
//     [3, 3, 6, 6],
//     [5, 1, 6, 3],
//   ])
// );
