# [62050] 지형 이동

## Algorithm

- BFS
- Union find

## Logic

1. 지형을 사다리 없이 갈 수 있는 지형들로 분리한다.

```js
const divide_land = () => {
  const divided_land = Array.from(Array(N), () => Array(N).fill(null));

  let count = 0;
  for (let r = 0; r < N; r++) {
    for (let c = 0; c < N; c++) {
      if (divided_land[r][c]) continue;
      divided_land[r][c] = ++count;

      const q = [[r, c]];
      while (q.length) {
        const cur = q.shift();
        for (let dir = 0; dir < 4; dir++) {
          const nr = cur[0] + dr[dir],
            nc = cur[1] + dc[dir];
          if (
            !isValid(nr, nc) ||
            divided_land[nr][nc] ||
            Math.abs(land[cur[0]][cur[1]] - land[nr][nc]) > height
          )
            continue;
          divided_land[nr][nc] = count;
          q.push([nr, nc]);
        }
      }
    }
  }

  return [divided_land, count];
};
```

2. 분리된 지형을 각각 하나의 노드로 한 간선들을 구한다.

```js
const get_edges = (divided_land) => {
  const edges = [];

  for (let r = 0; r < N; r++) {
    for (let c = 0; c < N; c++) {
      for (let dir = 0; dir < 4; dir++) {
        const nr = r + dr[dir],
          nc = c + dc[dir];
        if (!isValid(nr, nc) || divided_land[nr][nc] === divided_land[r][c]) continue;

        const diff = Math.abs(land[r][c] - land[nr][nc]);
        edges.push([divided_land[nr][nc], divided_land[r][c], diff]);
      }
    }
  }

  return edges;
};
```

3. 간선을 오름차순으로 정렬한 후, 아직 트리에 포함되지 않은 것들을 추가하며 비용의 최솟값을 구한다.

```js
edges.sort((a, b) => a[2] - b[2]);
const disjoint_set = new UnionFind(count + 1);
return edges.reduce((prev, cur) => {
  const [from, to, weight] = cur;
  if (disjoint_set.find(from) === disjoint_set.find(to)) return prev;
  disjoint_set.union(to, from);
  return prev + weight;
}, 0);
```

## Review
사다리 없이 갈 수 있는 곳들을 각 구간으로 나눈다는 아이디어를 잡으면 무난하게 풀 수 있는 문제라고 생각한다.
개인적으로 4번같은 문제가 더 어려운것 같다. 오랜만에 union find 자료구조를 사용해야하는 문제를 풀어서 복습도 되고 좋았다.