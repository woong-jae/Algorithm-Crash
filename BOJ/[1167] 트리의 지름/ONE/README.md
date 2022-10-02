# [1167] 트리의 지름
## Algorithm
- **DFS**

## Logic
- DFS를 아무 노드에서 1번 수행했을 떄 가장 먼 거리의 노드를 찾는다
- 해당 노드는 트리의 가장 긴 지름에 포함되는 노드이기 때문에 해당 노드부터 DFS를 1번 더 수행해서 지름을 찾는다

```java
private static void dfs(int v, int sum) {
    if (dist < sum) {
        dist = sum;
        next = v;
    }

    for (Node node : graph[v]) {
        if (visited[node.vertex]) continue;
        visited[node.vertex] = true;
        dfs(node.vertex, sum + node.weight);
    }
}
```

## Review
4%에서 자꾸 틀렸다.. 질문을 찾아보니 나와있는 테스트케이스가 특별했던 경우고  
해당부분을 수정하니 통과했다 억까네.