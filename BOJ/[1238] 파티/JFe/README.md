# [1238] 파티 - Python

## 🔍 Algorithm
**Dijkstra**

## 💻 Logic

```Python
def dijkstra(node):
    dist = [int(1e9) for _ in range(N + 1)]
    dist[node] = 0
    h = []
    heapq.heappush(h, (0, node))
    while h:
        value, node = heapq.heappop(h)
        if value < dist[node]: continue
        for next_node, cost in graph[node]:
            next_value = value + cost
            if next_value < dist[next_node]:
                dist[next_node] = next_value
                heapq.heappush(h, (next_value, next_node))
    return dist
```
- **다익스트라 탐색 함수**  

---

```Python
# graph 입력
for a, b, t in road_info:
    graph[a].append([b, t])
# 목적지 X에서 각 마을로 갈 수 있는 최단 거리 계산
x_dist = dijkstra(X)
result = 0
# 각 마을에서 목적지 X로 갈 수 있는 최단 거리 구하고 + 목적지 X -> i 마을까지 최단 거리 더해서
# 가장 오래 걸린 시간 출력
for i in range(1, N + 1):
    time = dijkstra(i)[X] + x_dist[i]
    result = max(result, time)
print(result)
```
- 단방향 그래프 입력  
- 우선, `목적지 X -> 각 마을로 갈 수 있는 최단 거리` => `x_dist`에 저장  
- `i 마을 -> X로 갈 수 있는 최단 거리` + `X -> i 마을까지 최단 거리` => `time`에 저장  
- `time` 중 **max** 값 출력  


## 📝 Review

일단 그래프 최단거리? 음수 아니다? => 다익스트라 사용하면 되겠다고 생각했다.  
단방향 그래프여서 X까지 갈 때와 돌아올 때 값이 다를 수 있겠다고 생각해서 따로 계산해서 더한 다음 최댓값을 구하도록 했다.  