# [1726] 로봇

## Algorithm

- BFS

## Logic

평범한 BFS에 방향만 추가하면 된다.

```js
const shortestCommand = Array.from(Array(M), () =>
  Array.from(Array(N), () => Array(4).fill(Infinity))
);
const q = [[...from, 0]];
shortestCommand[from[0]][from[1]][from[2]] = 0;
while (q.length) {
  const [r, c, d, w] = q.shift();
  if (w > shortestCommand[r][c][d]) continue;

  // 직진
  for (let dx = 1; dx <= 3; dx++) {
    const nr = r + dr[d] * dx,
      nc = c + dc[d] * dx;
    if (!valid(nr, nc)) break;
    if (w + 1 >= shortestCommand[nr][nc][d]) continue;

    shortestCommand[nr][nc][d] = w + 1;
    q.push([nr, nc, d, w + 1]);
  }

  // 회전
  for (let dir = 0; dir < 4; dir++) {
    const nw = spin(d, dir) + w;
    if (nw >= shortestCommand[r][c][dir]) continue;

    shortestCommand[r][c][dir] = nw;
    q.push([r, c, dir, nw]);
  }
}
```

## Review
쉽게 풀 수도 있었는데 자잘한 실수 때문에 좀 걸린 문제. 그래프 문제에 감이 떨어진 것 같다. 열심히 하자.