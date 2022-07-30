import heapq
def heap_bfs(N, land, height):
    answer = 0
    visited = [[0] * N for _ in range(N)]
    heap = []
    heapq.heappush(heap, [0, 0, 0]) # 이전 블록과의 높이 차 (이동 가능 경우면 0 - 같은 지역 간 이동이 우선돼야함)

    while heap:
        cur_h, cur_r, cur_c = heapq.heappop(heap)

        if visited[cur_r][cur_c] == 1: # 이미 사다리로 내려가서 탐색한 지역이면 패스
            continue
        visited[cur_r][cur_c] = 1
        answer += cur_h

        for D in [[-1, 0], [1, 0], [0, -1], [0, 1]]:
            next_r = cur_r + D[0]
            next_c = cur_c + D[1]
            if not (0 <= next_r < N and 0 <= next_c < N) or visited[next_r][next_c] == 1:
                continue
            sub = abs(land[next_r][next_c] - land[cur_r][cur_c])
            if sub > height: # 못가는 블록
                heapq.heappush(heap, [sub, next_r, next_c])
            else:
                heapq.heappush(heap, [0, next_r, next_c])
    return answer

def solution(land, height):
    N = len(land)
    return heap_bfs(N, land, height)