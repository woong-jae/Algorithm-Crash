# [12978] 배달 - Python

## 🔍 Algorithm
**Dijkstra**

## 💻 Logic

```Python
def dijkstra(N, graph):
    q = []
    distance = [sys.maxsize for _ in range(N+1)]
    heapq.heappush(q, (0, 1))
    distance[1] = 0
    while q:
        value, node = heapq.heappop(q)
        if distance[node] < value: continue
        for next_node, next_value in graph[node]:
            cost = value + next_value
            if cost < distance[next_node]:
                distance[next_node] = cost
                heapq.heappush(q, (cost, next_node))
    return distance
```
- **Dijkstra 탐색**  


```Python
    graph = defaultdict(list)
    # 그래프 생성
    for a, b, c in road:
        graph[a].append([b, c])
        graph[b].append([a, c])
    village = dijkstra(N, graph)
    # K 시간 이하 배달 가능한 마을 개수 체크
    for i in range(1, N + 1):
        if village[i] <= K: answer += 1
```
- **딕셔너리로 graph 생성**  
- **다익스트라 탐색 후, K 시간 이하 배달 가능한 마을 개수 체크**  


## 📝 Review

전형적인 다익스트라 문제!!