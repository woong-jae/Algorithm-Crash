# [76503] 모두 0으로 만들기
## Algorithm
- Postorder traverse
- Stack
## Logic
먼저 트리의 모든 가중치를 0으로 만드는 것이 불가능한지 확인한다.
`a`의 모든 값을 더했을 때, 0이 아니라면 불가능하다.

```js
if(a.reduce((prev, cur) => prev + cur, 0) !== 0) return -1;
```

트리의 가중치를 0으로 만드는 작업은 재귀적으로 할 수 있다.

현재 노드에서 모든 자식 노드들의 가중치를 0으로 만들기 위해서는 각 자식 노드들이 가진 가중치를 자신이 받으면 된다.
그러면 필요한 움직임은 각 '자식 노드의 가중치 + 자식 노드가 움직인 수'의 합이 된다.

이것을 임의의 루트부터 시작해서 후위 순회로 재귀적으로 진행하면 모든 가중치를 0으로 만들 수 있다.

```js
const visited = Array(a.length).fill(false);
const resolveChildWeight = cur => {
    visited[cur] = true;
    
    let weight = a[cur];
    if(adj_list[cur].length === 0) return [weight, 0];
    
    let moves = 0;
    adj_list[cur].forEach(next => {
        if(visited[next]) return;
        const [childWeight, childMoves] = resolveChildWeight(next);
        weight += childWeight;
        moves += childMoves + Math.abs(childWeight);
    });
    
    return [weight, moves];
}
```

하지만, 재귀적으로 구현할 경우 `a`의 길이가 최대 300000이기 때문에 stack overflow가 날 수 있다.

따라서 후위 순회를 stack으로 구현해야한다.

```js
const visited = Array(a.length).fill(false);
const weight_moves = a.map(w => [w, 0]);
const children = Array.from(Array(a.length), () => []);

const stack = [[0, 0]];
visited[0] = true;
while(stack.length) {
    const [cur, index] = stack.pop();
    if(adj_list[cur].length === 0) continue;
    
    let flag = false;
    for(let nextIndex = index; nextIndex < adj_list[cur].length; nextIndex++) {
        const next = adj_list[cur][nextIndex];
        if(visited[next]) continue;
        visited[next] = true;
        stack.push([cur, nextIndex]);
        stack.push([next, 0]);
        children[cur].push(next);
        
        flag = true;
        break;
    }
    if(flag) continue;
    
    children[cur].forEach(child => {
        weight_moves[cur][0] += weight_moves[child][0];
        weight_moves[cur][1] += weight_moves[child][1] + Math.abs(weight_moves[child][0]);
    });
}
```

## Review
처음에 재귀로 구현할 때 오버플로우가 날 줄 알았지만 그냥 구현했다. 재귀로 먼저 쉽게 구현하고 iterative하게 바꾸는 것이, 처음부터 iterative하게 바꾸는 것보다 쉽다고 생각했기 때문이다. 다행히도 내 생각이 맞았고, 비교적 쉽게 해결할 수 있었다.