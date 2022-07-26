# [72413] 합승 택시 요금
## Algorithm
- Dijkstra

## Logic
- 합승을 했다가 나눠지는 경우가 발생하므로, 시작지점 s -> 환승 노드(x), 환승 노드(x) -> a/b 지점으로 나눈 후 x값 바꿔가면서 최소 거리 구하면 된다.
1. s에서 다른노드(환승노드)까지의 최단 거리를 구함
```python
taxi1 = dijkstra(graph, s, n)
```
2. 1~n까지 노드 각각을 환승노드로 생각해보면서, 환승노드에서 a/b 까지의 최단 거리를 구함
3. 1의 값 + 2의 값 (= 즉, s에서 환승지점까지 최단거리 + 환승지점에서 a/b까지의 최단거리) 중 최솟값이 answer 
```python
for start in range(1, n + 1):
    taxi2 = dijkstra(graph, start, n)
    answer = min(answer, taxi1[start] + (taxi2[a] + taxi2[b]))
```
- 최단거리는 Dijkstra 알고리즘으로 구해야함
```python
def dijkstra(graph, s, n):
    costs = { key: 10**10 for key in range(n + 1) }
    costs[s] = 0
    que = []
    heapq.heappush(que, [costs[s], s])

    while que:
        cur_cost, cur_node = heapq.heappop(que)

        if cur_cost > costs[cur_node]: continue
        for next_node, next_cost in graph[cur_node].items():
            cost = cur_cost + next_cost
            if cost < costs[next_node]:
                costs[next_node] = cost
                heapq.heappush(que, [cost, next_node])
    return costs
```

## Review
환승 지점 기준으로 나눠생각하는 걸 빨리 생각해내서 무난하게 풀었다 !