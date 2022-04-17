function solution(board, moves) {
  let answer = 0;

  const basket = []; // stack
  const top = new Array(board.length + 1).fill(null);

  // 맨 위 인형의 위치 구하기
  for (let y = 0; y < board.length; y++) {
    for (let x = 0; x < board.length; x++) {
      if (board[x][y] === 0) continue;
      top[y + 1] = [x, y];
      break;
    }
  }

  // 인형 뽑기 시작
  moves.forEach((move) => {
    if (!top[move]) return;

    const [x, y] = top[move];
    const doll = board[x][y];

    // 맨 위 인형 위치 업데이트
    top[move] = x + 1 < board.length ? [x + 1, y] : null;

    // 인형 터뜨리기
    if (basket[basket.length - 1] === doll) {
      basket.pop();
      answer += 2;
    } else {
      basket.push(doll);
    }
  });

  return answer;
}
