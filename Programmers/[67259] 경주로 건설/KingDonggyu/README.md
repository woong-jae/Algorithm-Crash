## :mag: Algorithm

BFS, Dynamic Programming

## :round_pushpin: Logic

**BFS를 수행**하며 해당 위치에 오기까지의 비용을 저장하여 **메모제이션**을 한다.

```js
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

    ...
}
```

- `visited` : BFS를 수행하며 현재 위치에 오기까지의 최소 비용을 저장한다.

- `queue` 에 좌표, 현재 방향, 누적 비용, 기회 횟수를 선입선출 한다.

- 불필요한 연산을 조금이라도 줄이기 위해, 현재방향을 우선순위로 하여 탐색한다.

```js
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
```

- 현재 방향과 다음 방향이 같으면 +100, 그렇지 않으면 코너를 돌 것이므로 +600 한다.

- 메모해둔 비용보다 적은 비용으로 해당 위치에 도착할 경우, 메모된 비용을 교체하고 BFS를 계속 진행한다.

- 만약 그렇지 않을 경우, 한번의 기회를 더 준다.

  - 다음 이동 위치에서의 최소값을 기대할 수 있기 때문이다.

    만약 현재 위치의 최소값이 다음 위치에서 코너를 돌게 될 경우, 더 큰 비용을 초래할 수도 있다.

  - 기회를 다 쓰면 탐색을 멈춘다. 즉, 큐에 넣지 않는다.

### 시간 복잡도

**O(NlogN)**

## :memo: Review

BFS와 DP를 활용하는 방법을 금방 알았지만, 마지막 테스트 케이스에서 계속 틀렸다..

이 하나의 케이스 때문에 계속 반례를 구상하다, 메모되어 있는 값보다 크더라도 다음 이동에서 최소값을 기대할 수도 있다는 것을 깨달았다.

그래서 기회를 한 번씩 주는 것으로 구현했고, 해결할 수 있었다.