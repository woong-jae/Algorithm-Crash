# [2026] 소풍

## Algorithm

- Backtracking
- DFS

## Logic

1. 인접 그래프를 만든다.
2. 각 정점에서 DFS를 시작한다. 다음 노드를 정할 때 친구인지 아닌지 확인하고 넣는다.

```js
const dfs = (start) => {
  let finished = false;
  let answer = -1;
  const visited = new Set([start]);

  const helper = (cur, picked) => {
    if (finished) return;
    if (picked.length === K) {
      answer = [...picked];
      finished = true;
      return;
    }

    for (let next = cur + 1; next <= N; next++) {
      if (visited.has(next)) continue;

      const isFriend = picked.reduce((acc, pick) => acc && adjGraph[pick][next], true);
      if (isFriend) {
        visited.add(next);
        picked.push(next);
        helper(next, picked);
        picked.pop();
      }
    }
  };

  helper(start, [start]);

  return answer;
};
```

## Review

너무 복잡하게 생각해서 못풀었다. 답을 보니까 쉬워서 허무했다. 백트래킹 문제를 많이 안풀어서 어떻게 접근할지 감이 잘 안오는 것 같다.

종만북부터 읽어봐야겠다...ㅋㅋ
