import heapq, sys
from collections import defaultdict

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

def solution(N, road, K):
    answer = 0
    graph = defaultdict(list)
    # 그래프 생성
    for a, b, c in road:
        graph[a].append([b, c])
        graph[b].append([a, c])
    village = dijkstra(N, graph)
    # K 시간 이하 배달 가능한 마을 개수 체크
    for i in range(1, N + 1):
        if village[i] <= K: answer += 1
    return answer