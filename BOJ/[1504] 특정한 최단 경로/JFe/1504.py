import sys, heapq
from collections import defaultdict

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
    
N, E = map(int, sys.stdin.readline().split())
graph_input = [[int(x) for x in sys.stdin.readline().split()] for _ in range(E)]
v1, v2 = map(int, sys.stdin.readline().split())
graph = defaultdict(list)
temp1, temp2 = 0, 0

for a, b, c in graph_input:
    graph[a].append([b, c])
    graph[b].append([a, c])
# 1번, v1번, v2번 노드를 시작점으로 다익스트라 탐색
d_1 = dijkstra(1)
d_v1 = dijkstra(v1)
d_v2 = dijkstra(v2)
# 1 -> v1 -> v2 -> N or 1 -> v2 -> v1 -> N 중에서 최솟값
result = min(d_1[v1] + d_v1[v2] + d_v2[N], d_1[v2] + d_v2[v1] + d_v1[N])
if result >= sys.maxsize: print(-1)
else: print(result)