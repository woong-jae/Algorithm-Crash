# [92343] 양과 늑대

## Algorithm

- BFS
- Bitmask

## Logic

1. Root부터 시작해서 BFS를 시작한다.
2. 다음으로 갈 수 있는 경우는 다음과 같다.
  - 다음으로 갔을 때 양의 수가 늑대의 수보다 같거나 작다면 갈 수 없다.
  - 그리고 방문한 노드라면 다음 노드에 기록된 양의 수보다 많거나, 양의 수가 같다면 늑대의 수가 작아야한다.

```js
const canGo = (next, counts, visited) => {
  if (counts[0] <= counts[1]) return false;

  if (visited & (1 << next)) {
    if (counts[0] < max_counts[next][0]) return false;
    if (counts[0] === max_counts[next][0] && counts[1] >= max_counts[next][1]) return false;
  }

  return true;
};
```

3. 다음으로 갈 수 있다면 기록하고 큐에 넣는다.

```js
const q = [[0, 1, 0, 1]];
while(q.length) {
    const [cur, sheep, wolf, visited] = q.shift();
    if(sheep < max_counts[cur][0]) continue;
    
    adj_list[cur].forEach(next => {
        const next_counts = getNextCounts(next, sheep, wolf, visited);
        if(!canGo(next, next_counts, visited)) return;
        
        q.push([next, ...next_counts, visited | (1 << next)]);
        max_counts[next] = next_counts;
    });
}
```

## Review
BFS를 할 때 각 BFS 가지가 뻗을 때마다 각자 `visited`를 관리하게 해야하는 것을 안해서 틀렸다.
이걸 어떻게 괸라할까 생각하다 보니, `info`의 길이가 최대 17이라는 점에서 비트마스크를 사용할 수 있을 것이라 생각했다.
어렵구만 어려워.
