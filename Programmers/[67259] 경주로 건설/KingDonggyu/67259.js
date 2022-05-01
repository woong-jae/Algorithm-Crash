function solution(board) {
  const size = board.length; 
  // 상, 우, 하, 좌
  const dx = [-1, 0, 1, 0];
  const dy = [0, 1, 0, -1];

  const visited = new Array(size).fill(null)
    .map((_) => new Array(size).fill(0));

  const queue = [[0, 0, -1, 0, 1]];

  while (queue.length > 0) {
    // (x, y) 좌표, 현재 방향, 누적 비용, 기회 횟수
    let [x, y, nowDi, cost, chance] = queue.shift();

    // 불필요한 연산을 줄이기 위해, 현재 방향을 우선순위로 한다
    const di = [nowDi < 0 ? 1 : nowDi];
    for (let i = 0; i < 4; i++) {
      if (i === di[0]) continue;
      di.push(i);
    }

    for (const nextDi of di) {
      const nextX = x + dx[nextDi];
      const nextY = y + dy[nextDi];

      if (nextX < 0 || nextX >= size || nextY < 0 || nextY >= size) {
        continue;
      }

      if (!board[nextX][nextY]) {
        const nextCost =
          nowDi < 0 || nowDi === nextDi ? cost + 100 : cost + 600;
          
        if (visited[nextX][nextY] && nextCost > visited[nextX][nextY]) {
          // 다음 이동에서 최소값을 기대할 수 있기 때문에, 기회를 준다
          if (chance === 1) {
            queue.push([nextX, nextY, nextDi, nextCost, chance - 1]);
          }
          continue;
        }
        visited[nextX][nextY] = nextCost;
        queue.push([nextX, nextY, nextDi, nextCost, chance]);
      }
    }
  }

  return visited[size - 1][size - 1];
}
