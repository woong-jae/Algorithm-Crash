import heapq, sys
from collections import defaultdict

def solution(n, start, end, roads, traps):
    INF = sys.maxsize
    answer = INF
    graph = defaultdict(list)
    # visited : 활성화 된 함정 노드에 따라 방문했던 노드 표시
    # visited[node][활성화 된 함정 상태] (활성화 된 함정 상태는 비트마스크로 표시)
    visited = [[INF for _ in range(2**len(traps))]for _ in range(n+1)]
    traps_dict = {n: i for i, n in enumerate(traps)}    # 함정 노드: traps 리스트 상 index
    # 그래프 정보 입력
    for a, b, v in roads:
        graph[a].append([b, v, False])  # False : 정방향
        graph[b].append([a, v, True])   # True : 역방향
    # 다익스트라 알고리즘
    h = []
    heapq.heappush(h, (0, start, 0))
    visited[start][0] = 0
    while h:
        cost, node, state = heapq.heappop(h)
        # end 노드 도착
        if node == end:
            answer = min(answer, cost)
            continue
        # 이미 방문한 값보다 크면 방문 X
        if cost > visited[node][state]:
            continue
        for next_node, next_cost, direction in graph[node]:
            # 함정 노드면 비트마스킹
            cur_trap, next_trap = False, False
            if node in traps_dict:
                cur_trap = bool(state & (1 << traps_dict[node]))
            if next_node in traps_dict:
                next_trap = bool(state & (1 << traps_dict[next_node]))
            if direction != (cur_trap ^ next_trap): # cur_trap, next_trap 상태가 같으면 정방향, 다르면 역방향
                continue
            # 다음 노드가 함정 노드인지에 따라 상태 변경
            next_state = state
            if next_node in traps_dict:
                next_state = state ^ (1 << traps_dict[next_node])
            # 다음 cost 계산하고, 다음 상태의 cost가 이미 방문했고 더 작으면 방문 X
            next_cost = cost + next_cost
            if next_cost >= visited[next_node][next_state]:
                continue
            visited[next_node][next_state] = next_cost
            heapq.heappush(h, (next_cost, next_node, next_state))
    return answer