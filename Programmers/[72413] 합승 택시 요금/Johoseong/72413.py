import heapq
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

def solution(n, s, a, b, fares):
    answer = 10 ** 10
    graph = { i: {} for i in range(n + 1)}
    for i, j, cost in fares:
        graph[i][j] = cost
        graph[j][i] = cost

    taxi1 = dijkstra(graph, s, n) # s -> 다른 노드까지 가는 최단거리 (환승지점)
    for start in range(1, n + 1): # 환승지점 -> a, b까지 가는 최단거리 구함
        taxi2 = dijkstra(graph, start, n)
        answer = min(answer, taxi1[start] + (taxi2[a] + taxi2[b])) # 1,2 더한거중 제일 작은 값이 answer

    return answer