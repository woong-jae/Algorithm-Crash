function solution(n, path, order) {
  const graph = new Array(n).fill(null).map((_) => []);

  path.forEach(([x, y]) => {
    graph[x].push(y);
    graph[y].push(x);
  });

  const beforeRoom = new Array(n).fill(null); // 먼저 방문해야 하는 방
  const afterRoom = new Array(n).fill(null); // 다음 방문할 수 있는 방

  order.forEach(([x, y]) => {
    beforeRoom[y] = x;
    afterRoom[x] = y;
  });

  // 0보다 먼저 방문해야 하는 방이 있는지 확인
  if (beforeRoom[0]) {
    return false;
  }

  const visited = new Array(n).fill(false);
  const isWaiting = new Array(n).fill(false);
  const stack = [0];
  let roomCount = 1;
  visited[0] = true;

  const visitRoom = (x) => {
    stack.push(x);
    visited[x] = true;
    roomCount++;
  };

  // DFS
  while (stack.length > 0) {
    const x = stack.pop();

    for (const next of graph[x]) {
      if (visited[next]) {
        continue;
      }

      if (beforeRoom[next] && !visited[beforeRoom[next]]) {
        // 다른 방을 먼저 탐색하도록 wait
        isWaiting[next] = true;
        continue;
      }

      visitRoom(next);

      const after = afterRoom[next];
      if (after && isWaiting[after]) {
        // wait 중인 방을 활성화
        visitRoom(after);
        isWaiting[after] = false;
      }
    }
  }

  return roomCount === n;
}
