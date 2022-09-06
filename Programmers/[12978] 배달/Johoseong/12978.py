import heapq
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

def solution(N, road, K):
    answer = 0
    graph = [[10 ** 10] * (N + 1) for _ in range(N + 1)]
    distance = [10 ** 10 for _ in range(N + 1)]

    for a, b, w in road:
        graph[a][b] = min(w, graph[a][b])
        graph[b][a] = min(w, graph[b][a])

    dijkstra(N, graph, distance)

    for i in range(1, N + 1):
        if distance[i] <= K:
            answer += 1
    return answer