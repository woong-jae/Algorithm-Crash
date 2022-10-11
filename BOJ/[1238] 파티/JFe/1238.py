import sys, heapq
from collections import defaultdict

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

N, M, X = map(int, sys.stdin.readline().split())
road_info = [[int(x) for x in sys.stdin.readline().split()] for _ in range(M)]
graph = defaultdict(list)
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