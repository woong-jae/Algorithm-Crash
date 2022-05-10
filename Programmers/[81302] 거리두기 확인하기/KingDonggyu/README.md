## :mag: Algorithm

DFS

## :round_pushpin: Logic

**각 'P'의 위치를 시작으로 DFS를 깊이(맨해튼 거리) 2까지 수행한다.**

```js
for (let x = 0; x < 5; x++) {
    for (let y = 0; y < 5; y++) {
        if (place[x][y] === 'P') {
            stack.push([x, y, 1])
        }
    }
}
```

- 각 P의 위치를 찾아 좌표를 스택에 push 한다.

  - 이때, 깊이를 알기 위해 1을 함께 push 한다.

<br />

```js
outer: while (stack.length > 0) {
  let [x, y, distance] = stack.pop();

  if (visited[x][y]) {
    continue;
  }

  visited[x][y] = true;

  for (let d = 0; d < 4; d++) {
    let nextX = x + dx[d];
    let nextY = y + dy[d];

    if (nextX < 0 || nextX > 4 || nextY < 0 || nextY > 4 ||
        visited[nextX][nextY] || place[nextX][nextY] === 'X') {
        continue;
    }

    if (place[nextX][nextY] === 'P') {
      isKeep = false;
      break outer;
    }

    if (distance === 2) {
      continue;
    }

    stack.push([nextX, nextY, distance + 1]);
  }
}
```

- DFS를 실시한다.

  - 탐색 위치가 이미 방문한 곳인지, 'X'인지, 'P'인지, 맨해튼 거리가 2인지 모두 고려한다.

### 시간 복잡도 : O(N)

## :memo: Review

문제에서 말하는 맨해튼 거리 2 이하의 범위를 그려보니, 깊이 2 DFS를 써야한다는 것을 바로 알 수 있었다.

처음에 맨해튼 거리 2인 곳을 방문하는 것까지 방문 여부를 `true`로 하는 실수를 해버려서 틀렸다.