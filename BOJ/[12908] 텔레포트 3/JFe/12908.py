import sys, heapq
from collections import defaultdict

def dijkstra(graph, start, end):
    h = []
    INF = int(1e9)
    distance = [INF for _ in range(8)]
    heapq.heappush(h, (0, start))
    distance[start] = 0
    while h:
        cost, node = heapq.heappop(h)
        if cost > distance[node]: continue
        for next_node, value in graph[node]:
            next_cost = cost + value
            if next_cost < distance[next_node]:
                distance[next_node] = next_cost
                heapq.heappush(h, (next_cost, next_node))
    return distance[end]

xs, ys = map(int, sys.stdin.readline().split())
xe, ye = map(int, sys.stdin.readline().split())
teleport = [[int(x) for x in sys.stdin.readline().split()] for _ in range(3)]
# idx에 좌표 전부 저장
idx = []
idx.extend([[xs, ys], [xe, ye]])
for x1, y1, x2, y2 in teleport:
    idx.extend([[x1, y1], [x2, y2]])
# graph 생성
graph = defaultdict(list)
for i, (x1, y1) in enumerate(idx):
    for j in range(i + 1, len(idx)):
        x2, y2 = idx[j][0], idx[j][1]
        graph[i].append([j, abs(x2 - x1) + abs(y2 - y1)])
        graph[j].append([i, abs(x2 - x1) + abs(y2 - y1)])
        # 연결되어 있는 텔레포트 좌표인 경우
        if (i == 2 and j == 3) or (i == 4 and j == 5) or (i == 6 and j == 7):
            graph[i].append([j, 10])
            graph[j].append([i, 10])
print(dijkstra(graph, 0, 1))