function checkIsOutOfBounds(x, y, n, m) {
  return x < 0 || y < 0;
}

function solution(board) {
  let answer = 0;
  const N = board.length;
  const M = board[0].length;

  board.forEach((row, x) => {
    row.forEach((_, y) => {
      if (
        !board[x][y] ||
        checkIsOutOfBounds(x - 1, y - 1, N, M) ||
        checkIsOutOfBounds(x - 1, y, N, M) ||
        checkIsOutOfBounds(x, y - 1, N, M)
      ) {
        return;
      }

      board[x][y] =
        Math.min(board[x - 1][y - 1], board[x - 1][y], board[x][y - 1]) + 1;

      answer = Math.max(answer, board[x][y]);
    });
  });

  return answer * answer;
}
