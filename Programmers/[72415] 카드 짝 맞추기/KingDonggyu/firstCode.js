function solution(board, r, c) {
  const isFinish = (board) => {
    return board.split('').every((card) => card === '0');
  };

  const isOutOfBounds = (x, y) => {
    return x < 0 || x >= 4 || y < 0 || y >= 4;
  };

  const isEdge = (x, y, di) => {
    return (
      (di === 0 && x === 0) ||
      (di === 1 && x === 3) ||
      (di === 2 && y === 0) ||
      (di === 3 && y === 3)
    );
  };

  const boardIdx = (x, y) => {
    return x * 4 + y;
  };

  const cursorState = (x, y, fx, fy) => {
    return `${x}${y}${fx}${fy}`;
  };

  const deleteCard = (board, card) => {
    return board.replace(new RegExp(card, 'g'), '0');
  };

  board = board.reduce((a, b) => (a += b.join('')), '');

  const dx = [-1, 1, 0, 0];
  const dy = [0, 0, -1, 1];
  const visited = new Set();

  const queue = [[board, r, c, null, null, 0]];

  visited.add(board + cursorState(r, c, null, null));

  while (queue.length) {
    let [board, x, y, fx, fy, count] = queue.shift();

    for (let i = 0; i < 8; i++) {
      let [nextX, nextY] = [x + dx[i % 4], y + dy[i % 4]];

      if (isOutOfBounds(nextX, nextY)) {
        continue;
      }

      while (
        i < 4 &&
        board[boardIdx(nextX, nextY)] === '0' &&
        !isEdge(nextX, nextY, i)
      ) {
        nextX += dx[i];
        nextY += dy[i];
      }

      const state = board + cursorState(nextX, nextY, fx, fy);

      if (visited.has(state)) {
        continue;
      }

      visited.add(state);
      queue.push([board, nextX, nextY, fx, fy, count + 1]);
    }

    if (board[boardIdx(x, y)] === '0') {
      continue;
    }

    if (fx !== null && fy !== null) {
      if (
        board[boardIdx(x, y)] === board[boardIdx(fx, fy)] &&
        x !== fx &&
        y !== fy
      ) {
        board = deleteCard(board, board[boardIdx(x, y)]);

        if (isFinish(board)) {
          return count + 1;
        }

        visited.add(board + cursorState(x, y, null, null));
        queue.push([board, x, y, null, null, count + 1]);
      }
      continue;
    }

    const state = board + cursorState(x, y, x, y);

    if (visited.has(state)) {
      continue;
    }

    visited.add(state);
    queue.push([board, x, y, x, y, count + 1]);
  }
}
