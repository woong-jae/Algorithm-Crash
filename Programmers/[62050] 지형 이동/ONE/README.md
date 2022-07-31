# [62050] 지형 이동
## Algorithm
- **MST**
- **BFS**

## Logic
- BFS를 이용해 height 이하로 갈 수 있는 곳들의 범주로 check를 그린다
- BFS를 수행할 때, 높이 차이가 나서 사다리를 놓을 가능성이 있는 곳들의 높이 차와 해당 노드의 번호들을 우선순위 큐에 넣는다 -> Edge class
- BFS 후에 생성된 우선순위 큐에서 하나씩 가져와서 부모가 다르고 두 Node 의 번호가 0이 아니면 Union 하고 높이차를 answer에 더한다

```java
for (int i = 0; i < n; i++)
    for (int j = 0; j < n; j++)
        if (check[i][j] == 0)
            bfs(i, j, checkNum++);

parent = new int[checkNum + 1];
for (int i = 0; i < checkNum; i++)
    parent[i] = i;


while (!pq.isEmpty()) {
    Edge edge = pq.poll();

    if ((find(edge.u) != find(edge.v)) && (edge.u != 0 && edge.v != 0)) {
        union(edge.u, edge.v);
        answer += edge.w;
    }
}
```

## Review
아이디어는 빨리 생각나서 구현하는데 까지는 문제가 없었는데 자꾸 24,25번에서 시간 초과가 나서 해결하지못하고 결국 성욱이 코드를 참고 했는데,  
찾아보니 내가 생각한 곳과 다른 곳에서 시간 초과가 발생했었다  
나는 BFS를 할 때 Queue에서 가져온 후 visited에 표시했지만 그렇게 하면 중복이 매우 많이 발생해서 시간초과가 난 것이었고  
앞으로는 Queue에 삽입할 때 visited를 표시해야겠다고 생각했다... 똥멍청이였네...
