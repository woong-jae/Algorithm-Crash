# [2026] 소풍

## Algorithm

- DFS
  
## Logic

- 먼저, 친구 관계에 대한 양방향 그래프를 인접 행렬로 표현한다.

  - 출력 조건을 맞추기 위해 각 원소 배열을 오름차순으로 정렬한다.

- 1부터 N까지 순회하며, 친구가 K - 1 이상인 경우 해당 숫자에 대한 정점을 시작으로 DFS를 수행한다.

  - 매 방문마다 `selected` 배열에 방문한 정점 숫자를 추가한다.

  - 단, `selected` 베열에 담긴 숫자들과 모두 친구관계여야 한다.

  ```js
  const dfs = (num, selected, visited) => {
    if (selected.length === K) {
      console.log(selected.join('\n'));
      process.exit();
    }

    for (const v of graph[num]) {
      if (visited[v]) {
        continue;
      }

      if (selected.every((s) => graph[v].includes(s))) {
        visited[v] = true;
        dfs(v, [...selected, v], visited);
      }
    }
  };
  ```

## Review

처음에 조합을 이용했더니 역시나 시간초과가 발생했다.

감이 안잡혀서 다른 사람의 코드를 참고했고, 백트래킹을 적용한 DFS를 구현했다.

막상 구현하니 생각보다 허무했다.
