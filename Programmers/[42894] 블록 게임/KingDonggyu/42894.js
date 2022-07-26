const blockPattern = [
  [[1, 0], [1, 1], [1, 2]],
  [[1, 0], [2, 0], [2, -1]],
  [[1, 0], [2, 0], [2, 1]],
  [[1, 0], [1, -1], [1, -2]],
  [[1, 0], [1, -1], [1, 1]],
];

const blockEmpty = [
  [[0, 1], [0, 2]],
  [[0, -1], [1, -1]],
  [[0, 1], [1, 1]],
  [[0, -1], [0, -2]],
  [[0, -1], [0, 1]]
];

function checkBlockAbove(board, x, y, type) {
  for (let i = 0; i < blockEmpty[type].length; i++) {
    const [dx, dy] = blockEmpty[type][i];
    const col = y + dy;
    for (let row = x + dx; row >= 0; row--) {
      if (board[row][col]) {
        return false;
      }
    }
  }

  return true;
}

function removeBlocks(board, x, y) {
  const number = board[x][y];
  
  for (let i = 0; i < 5; i++) {
    const matched = [[x, y]];

    for (const [dx, dy] of blockPattern[i]) {
      const nextX = x + dx;
      const nextY = y + dy;

      if (
        nextX < 0 || nextX >= board.length || 
        nextY < 0 || nextY >= board.length ||
        board[nextX][nextY] !== number
      ) {
        break;
      }

      matched.push([nextX, nextY]);
    }

    // 패턴 찾음
    if (matched.length === 4) {
      // 패턴 블록의 위에 블록이 있는지 확인
      if (!checkBlockAbove(board, x, y, i)) {
        break;
      }

      matched.forEach(([x, y]) => {
        board[x][y] = 0;
      })

      return 1;
    }
  }

  return 0;
}

function solution(board) {
  let answer = 0;

  board.forEach((row, x) => {
    row.forEach((block, y) => {
      if (block) {
        answer += removeBlocks(board, x, y);
      }
    })
    row.forEach((block, y) => {
      if (block) {
        answer += removeBlocks(board, x, y);
      }
    })
  });

  return answer;
}