# [76503] 모두 0으로 만들기

## Algorithm
- DFS

## Logic

```java
private long dfs(int node) {
    visited[node] = true;
    
    // 방문하지 않은 자식 노드가 있다면 탐색
    for (int i = 0; i < adjList[node].size(); i++) {
        int cur = adjList[node].get(i);
        if (!visited[cur])
            values[node] += dfs(cur);
    }
    
    // 런타임 에러 원인
    // for (int n : adjList[node])
    //     if (!visited[n]) 
    //         values[node] += dfs(n);

    long value = values[node];
    answer += Math.abs(value);

    return value;
}
```

- 리프 노드부터 0으로 만든다.

## Review
- 트리를 그려두고 리프 노드부터 보다보니 아이디어는 쉽게 잡을 수 있었다.
- 계속 6, 7, 8에서 런타임 에러가 발생했다 ....
  - Enhanced for문 사용으로 인한 런타임 에러였다는 걸 알게 됐고.. 정리해야겠다.