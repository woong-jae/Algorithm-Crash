# [72413] 합승 택시 요금 - Python

## 🔍 Algorithm
**Dijkstra**

## 💻 Logic

```Python
# 다익스트라 함수
def dijkstra(start, distance, graph):
    q = []
    heapq.heappush(q, (0, start))
    distance[start] = 0
    while q:
        value, node = heapq.heappop(q)
        if distance[node] < value:
            continue
        for next_node, v in graph[node]:
            next_v = value + v
            if next_v < distance[next_node]:
                distance[next_node] = next_v
                heapq.heappush(q, (next_v, next_node))
```
- **Dijkstra 탐색하는 함수**  


```Python
    # 다익스트라 탐색
    dijkstra(s, distance, graph)
    answer = distance[a] + distance[b]
    # 환승 지점 경우마다 다익스트라 탐색 후, 최저 비용 찾기
    for i in range(1, n+1):
        if i == s: continue
        cost = distance[i]
        new_d = [sys.maxsize for _ in range(n+1)]
        dijkstra(i, new_d, graph)
        cost += new_d[a] + new_d[b]
        answer = min(answer, cost)
```
- 우선 출발 지점에서 각 지점까지 걸리는 최단 거리를 구하기 위해 `dijkstra()` 함수를 이용하여 탐색  
- 환승 지점을 바꿔가면서 최단 거리를 구해야 하기 때문에, 환승 지점 경우마다 `dijkstra()` 함수 이용하여 탐색  
- 최솟값 `answer`에 저장  


## 📝 Review

최단 거리를 구해야 하는 다익스트라 문제인 것은 바로 알았는데 합승 후 내리는 지점을 어떻게 처리할지 고민했었다.  
문제의 입력 값이 별로 크지 않은 걸 확인하고, 각 환승 지점을 다 고려해서 다익스트라 탐색을 여러 번 하도록 구현하면 되겠다고 생각했고 그렇게 구현했다. 만족
