## :mag: Algorithm

DFS

## :round_pushpin: Logic

```js
const graph = new Array(n).fill(null).map((_) => []);

path.forEach(([x, y]) => {
  graph[x].push(y);
  graph[y].push(x);
});
```

- 양방향 그래프 생성

<br />

```js
const beforeRoom = new Array(n).fill(null); // 먼저 방문해야 하는 방
const afterRoom = new Array(n).fill(null); // 다음 방문할 수 있는 방

order.forEach(([x, y]) => {
  beforeRoom[y] = x;
  afterRoom[x] = y;
});
```

- **먼저 방문해야 하는 방**을 저장한 배열과, **다음 방문할 수 있는 방**을 저장한 배열 생성

<br />

```js
const visited = new Array(n).fill(false);
const isWaiting = new Array(n).fill(false);
const stack = [0];
let roomCount = 1;
visited[0] = true;
```

- 방문 여부를 나타내는 배열 `visited`, 잠시 탐색을 멈춘 방을 나타내는 배열 `isWaiting`, DFS에 필요한 스택, 방문한 방의 개수를 저장하는 변수 선언

<br />

```js
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
    if (after !== null && isWaiting[after]) {
      // wait 중인 방을 활성화
      visitRoom(after);
      isWaiting[after] = false;
    }
  }
}
```

- DFS 수행

- 이미 방문한 방이면 `continue`

- 먼저 방문해야 하는 방이 있을 경우, 다른 방들을 먼저 탐색하도록 탐색을 멈추고 `isWaiting`를 `true`로 변경

- 현재 방문으로 인해서 다음 방문 가능한 방이 존재할 경우, 멈추었던 방 탐색을 다시 활성화

### 시간 복잡도: 방을 모두 한번씩 방문하므로 O(N)이다.

## :memo: Review

이전 문제들의 난이도가 꽤 있었기에, 이번 문제는 많이 어려울 줄 알았는데 생각보다 쉬웠다.

정확성 테스트 케이스 30번이 틀려서 혹시나 싶어 문제를 다시 읽어보자, 역시나 방문 순서에 0은 해당하지 않는다는 말이 없었다.

그래서 0보다 먼저 방문해야 하는 방이 있으면 바로 `false`를 리턴하도록 하니 맞았다.