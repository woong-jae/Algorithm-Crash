function solution(board, aloc, bloc) {
  const isOutOfBounds = (x, y) => {
    return x < 0 || x >= N || y < 0 || y >= M;
  };

  const playTurn = (currPlayer, nextPlayer) => {
    const [x, y] = currPlayer;

    const nextPos = [[-1, 0], [1, 0], [0, -1], [0, 1]]
      .map(([dx, dy]) => [x + dx, y + dy])
      .filter(([nx, ny]) => !isOutOfBounds(nx, ny) && board[nx][ny]);

    // 현재 플레이어의 패배
    if (!board[x][y] || !nextPos.length) {
      return { isWin: false, cnt: 0 };
    }

    board[x][y] = 0;

    const results = nextPos.map((pos) => playTurn(nextPlayer, pos));

    board[x][y] = 1;

    let winCnt = 0;
    let max = 0;
    let min = Infinity;

    results.forEach(result => {
      // 다음 턴에서 상대가 패배
      // - 현재 플레이어는 승리했으므로 최소한의 이동을 선택
      if (!result.isWin) {
        min = Math.min(min, result.cnt);
        return;
      }
      // 다음 턴에서 상대가 승리
      // - 현재 플레이어는 패배했으므로 최대한의 이동을 선택
      max = Math.max(max, result.cnt);
      winCnt++;
    })

    // 상대 턴으로 넘어간 모든 결과가 승리
    // = 현재 플레이어는 반드시 패배
    if (winCnt === results.length) {
      return { isWin: false, cnt: max + 1 };
    }

    // 상대 턴으로 넘어간 모든 결과 중 패배하는 경우 존재
    // = 현재 플레이어가 승리하는 경우 존재
    return { isWin: true, cnt: min + 1 };
  };

  const [N, M] = [board.length, board[0].length];

  return playTurn(aloc, bloc).cnt;
}
