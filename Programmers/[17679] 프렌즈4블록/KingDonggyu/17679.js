function clearBlock(m, n, board) {
  const clearSet = new Set();

  for (let x = 0; x < m - 1; x++) {
    for (let y = 0; y < n - 1; y++) {
      const shape = board[x][y];
      if (!shape) continue;
      if (
        shape === board[x][y + 1] &&
        shape === board[x + 1][y] &&
        shape === board[x + 1][y + 1]
      ) {
        clearSet
          .add(`${x},${y}`)
          .add(`${x},${y + 1}`)
          .add(`${x + 1},${y}`)
          .add(`${x + 1},${y + 1}`);
      }
    }
  }

  for (const str of clearSet) {
    const [x, y] = str.split(',');
    board[x][y] = 0;
  }

  return clearSet.size;
}

function shiftBlock(m, n, board) {
  for (let y = 0; y < n; y++) {
    let bottom = m;
    for (let x = m - 1; x >= 0; x--) {
      if (!board[x][y] && bottom === m) {
        bottom = x;
        continue;
      }

      if (board[x][y] && bottom < m) {
        board[bottom][y] = board[x][y];
        board[x][y] = 0;
        bottom--;
      }
    }
  }
}

function solution(m, n, board) {
  let answer = 0;
  board = board.map((str) => str.split(''));

  while (1) {
    const clearCount = clearBlock(m, n, board);
    if (!clearCount) break;
    answer += clearCount;
    shiftBlock(m, n, board);
  }

  return answer;
}
