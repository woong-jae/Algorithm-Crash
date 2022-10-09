# [1504] 특정한 최단 경로 - Python

## 🔍 Algorithm
**Dijkstra**

## 💻 Logic

```Python
def dijkstra(node):
    q = []
    distance = [sys.maxsize for _ in range(N + 1)]
    heapq.heappush(q, (0, node))
    distance[node] = 0
    while q:
        v, node = heapq.heappop(q)
        if distance[node] < v: continue
        for next_node, next_v in graph[node]:
            cost = v + next_v
            if cost < distance[next_node]:
                distance[next_node] = cost
                heapq.heappush(q, (cost, next_node))
    return distance
```
- **다익스트라 탐색 함수**  

---

```Python
# 1번, v1번, v2번 노드를 시작점으로 다익스트라 탐색
d_1 = dijkstra(1)
d_v1 = dijkstra(v1)
d_v2 = dijkstra(v2)
# 1 -> v1 -> v2 -> N or 1 -> v2 -> v1 -> N 중에서 최솟값
result = min(d_1[v1] + d_v1[v2] + d_v2[N], d_1[v2] + d_v2[v1] + d_v1[N])
```
- `1번`, `v1번`, `v2번` 노드를 시작점으로 **다익스트라** 탐색  
- `1 -> v1 -> v2 -> N` or `1 -> v2 -> v1 -> N` 중에서 **최솟값**  


## 📝 Review

최단 경로 찾는 문제여서 바로 다익스트라 생각했고, 이전에도 비슷하게 풀어봤던 문제여서 다익스트라 함수를 중간 지점까지와 최종 지점까지 여러번 호출해서 최단 경로를 더하는 방식으로 구현했다.  