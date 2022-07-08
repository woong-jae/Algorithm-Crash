function solution(board, r, c) {
  const bfs = (removed, src, dst) => {
    const isOutOfBounds = (x, y) => {
      return x < 0 || x > 3 || y < 0 || y > 3;
    };

    const di = [[-1, 0], [1, 0], [0, -1], [0, 1]];
    const visited = Array.from(Array(4), () => Array(4).fill(false));
    const queue = [[...src, 0]];

    while (queue.length) {
      const [x, y, cnt] = queue.shift();
      if (x === dst[0] && y === dst[1]) {
        return cnt;
      }
      // 상, 하, 좌, 우 이동
      for (const [dx, dy] of di) {
        let [nx, ny] = [x + dx, y + dy];

        if (isOutOfBounds(nx, ny)) {
          continue;
        }

        if (!visited[nx][ny]) {
          visited[nx][ny] = true;
          queue.push([nx, ny, cnt + 1]);
        }

        // [Ctrl] 키 누르기
        // - 이미 한칸 이동했으므로 최대 2칸 이동 가능
        for (let i = 0; i < 2; i++) {
          // 카드가 존재
          if (!(removed & (1 << board[nx][ny]))) {
            break;
          }
          // 커서가 더이상 이동 불가
          if (isOutOfBounds(nx + dx, ny + dy)) {
            break;
          }
          [nx, ny] = [nx + dx, ny + dy];
        }

        if (!visited[nx][ny]) {
          visited[nx][ny] = true;
          queue.push([nx, ny, cnt + 1]);
        }
      }
    }

    return Infinity;
  };

  const permutate = (cnt, removed, src) => {
    // 모든 카드 제거
    if (removed === allRemoved) {
      answer = Math.min(answer, cnt);
      return;
    }

    for (const [card, pos] of cardPos.entries()) {
      // 이미 제거한 카드
      if (removed & (1 << card)) {
        continue;
      }

      const one = bfs(removed, src, pos[0]) + bfs(removed, pos[0], pos[1]) + 2;
      const two = bfs(removed, src, pos[1]) + bfs(removed, pos[1], pos[0]) + 2;

      permutate(cnt + one, removed | (1 << card), pos[1]);
      permutate(cnt + two, removed | (1 << card), pos[0]);
    }
  };

  const cardPos = new Map();
  let allRemoved = 1;
  let answer = Infinity;

  board.forEach((row, x) => {
    row.forEach((card, y) => {
      if (!card) {
        return;
      }
      if (cardPos.has(card)) {
        cardPos.get(card).push([x, y]);
        return;
      }
      cardPos.set(card, [[x, y]]);
      // 카드 값에 대한 비트 마스킹
      // - 비트 1이면 제거됨을 의미
      allRemoved |= 1 << card;
    });
  });

  permutate(0, 1, [r, c]);

  return answer;
}
