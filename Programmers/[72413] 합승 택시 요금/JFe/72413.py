import heapq, sys
from collections import defaultdict

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
                
def solution(n, s, a, b, fares):
    answer = 0
    graph = defaultdict(list)
    distance = [sys.maxsize for _ in range(n+1)]
    # 그래프 입력
    for i, j, v in fares:
        graph[i].append((j, v))
        graph[j].append((i, v))
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
    return answer