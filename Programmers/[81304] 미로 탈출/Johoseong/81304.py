import heapq

def dijkstra(n, start, end, graph, traps):
    # dist = [float('inf')] * (n + 1)
    # dist[start] = 0
    dist = {}
    heap = []
    heapq.heappush(heap, [0, start, 0]) # 힙에 [비용, 노드, state(트랩 여부)] 넣음
    dp[0][start] = 0

    while heap:
        cur_time, cur_node, cur_state = heapq.heappop(heap)
                
        if end == cur_node:
            return cur_time

        for next_node, next_time, state in graph[cur_node]:
            if cur_node in traps:
                if next_node in traps: # 현재 노드랑 다음 노드 다 함정임 -> 0
                    cur_flag  = ((cur_state&(1<<trap_index[cur_node]))//(1<<trap_index[cur_node]) + (cur_state&(1<<trap_index[next_node]))//(1<<trap_index[next_node]))%2
                else: # 현재 노드만 함정임 -> 1
                    cur_flag = (cur_state&(1<<trap_index[cur_node]))//(1<<trap_index[cur_node])
            else:
                if next_node in traps: # 다음 노드만 함정임 -> 1
                    cur_flag = (cur_state&(1<<trap_index[next_node]))//(1<<trap_index[next_node])
                else: # 현재 노드랑 다음 노드 다 함정 아님 -> 0
                    cur_flag = 0
        
            if cur_flag == state:
                if dp[cur_state][next_node] > cur_time + next_time:
                    dp[cur_state][next_node] = cur_time + next_time
                    if next_node in traps:
                        heapq.heappush(heap,(dp[cur_state][next_node], next_node, cur_state^(1<<trap_index[next_node])))
                    else:
                        heapq.heappush(heap,(dp[cur_state][next_node],next_node,cur_state))

def solution(n, start, end, roads, traps):
    global trap_index, dp
    graph = [[] for _ in range(n + 1)]
    trap_index = {t : n for n, t in enumerate(traps)}
    dp = [[float('inf') for _ in range(n+1)] for _ in range(1<<len(traps))]

    for road in roads:
        p, q, s = road
        graph[p].append([q, s, 0])
        graph[q].append([p, s, 1])

    return dijkstra(n, start, end, graph, traps)

print(solution(4, 1, 4, [[1, 2, 1], [2, 3, 1], [3, 2, 1], [3, 4, 1], [1, 4, 10]], [2, 3]))