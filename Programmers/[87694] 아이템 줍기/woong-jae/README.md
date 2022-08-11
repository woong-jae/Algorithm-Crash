# [87694] 아이템 줍기

## Algorithm

- BFS

## Algorithm

1. 선을 분리하기 위해 넓이를 두배로 바꿔준다.

```js
const drawRectangles = (matrix, rectangles) => {
  rectangles.forEach((rectangle) => {
    const [x1, y1, x2, y2] = rectangle.map((v) => v * 2);

    let cur = [y1, x1];
    for (let dir = 0; dir < 4; dir++) {
      while (1) {
        const [nr, nc] = cur.map((v, i) => v + d[i][dir]);
        if (y1 > nr || nr > y2 || x1 > nc || nc > x2) break;

        matrix[nr][nc] = 1;
        cur = [nr, nc];
      }
    }
  });
  return matrix;
};
```

2. BFS로 테두리를 탐색한다. 다음 움직임이 다른 사각형들 안에 있는지 확인하고, 없으면 탐색한다.

```js
const q = [[characterY * 2, characterX * 2]];
while (q.length > 0) {
  const cur = q.shift();
  const nextDist = distance[cur[0]][cur[1]] + 1;

  for (let dir = 0; dir < 4; dir++) {
    const [nr, nc] = cur.map((v, i) => v + d[i][dir]);
    if (ground[nr][nc] === 0 || distance[nr][nc] !== 0) continue;
    if (isInside(nr, nc)) continue;

    distance[nr][nc] = nextDist;
    q.push([nr, nc]);
  }
}
```

## Review
뭔가 새로운 문제였다. 이런 유형의 문제를 너무 오랜만에 만났는지 구현하는데 너무 오래걸렸다. 고통스러웠던 문제...