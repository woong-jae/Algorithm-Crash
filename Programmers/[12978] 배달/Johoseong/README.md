# [12978] 배달
## Algorithm
- Dijkstra
## Logic
1. 1번 마을에서 K 시간 이하로 이동 가능한 마을의 개수를 구해야함
2. 다익스트라 알고리즘으로 1번 마을에서 각 마을까지 가는 최소 시간을 구함
3. K 이하인 개수를 셈
```python
def dijkstra(N, graph, distance):
    heap = []
    distance[1] = 0
    heapq.heappush(heap, [0, 1])

    while heap:
        cur_dist, cur_node = heapq.heappop(heap)

        if cur_dist > distance[cur_node]: continue

        for next_node in range(1, N + 1):
            next_dist = graph[cur_node][next_node]
            sum_dist = cur_dist + next_dist
            if sum_dist < distance[next_node]:
                distance[next_node] = sum_dist
                heapq.heappush(heap, [sum_dist, next_node])
```

## Review
다익스트라 기본 문제였다.