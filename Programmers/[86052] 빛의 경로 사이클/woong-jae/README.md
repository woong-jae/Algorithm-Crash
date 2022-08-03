# [86052] 빛의 경로 사이클
## Algorithm
- Graph
## Logic
각 노드의 상하좌우 네 방향에 대해, 방문한 적이 있는지 기록하는 `visited` 배열을 둔다.

각 노드의 네 방향중 아직 방문하지 않은 방향을 찾는다. 그 방향부터 시작해서 완성되는 사이클의 길이를 구한다.
사이클을 찾으면서 방문한 간선은 모두 `true`로 표시해서 다시 방문하지 않도록 한다.

```js
const calcPath = (r, c, dir) => {
    let result = 1;
    
    let next = getNext(r, c, dir);
    while(1) {
        const [nr, nc, ndir] = next;
        const nextVisited = grid[nr][nc][1];
        if(nextVisited[ndir]) break;
        
        nextVisited[ndir] = true;
        next = getNext(nr, nc, ndir);
        result++;
    }
    
    return result;
}
```

## Review
시키는 대로 하면 풀 수 있는 어렵지 않은 문제. 어렵지는 않지만 귀찮았다.