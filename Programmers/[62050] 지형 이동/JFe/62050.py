import heapq
def solution(land, height):
    answer, n, h = 0, len(land), []
    visited = [[False for _ in range(n)] for _ in range(n)]
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    # 우선순위 큐를 이용한 탐색
    # heapq 정보 : [사다리 비용, x좌표, y좌표]
    heapq.heappush(h, [0, 0, 0])
    while h:
        w, x, y = heapq.heappop(h)
        if visited[y][x]: continue  # 방문했으면 통과(우선순위가 밀린 경우)
        visited[y][x] = True
        answer += w
        # 상하좌우 탐색
        for i in range(4):
            next_x, next_y = x + dx[i], y + dy[i]
            if 0 <= next_x < n and 0 <= next_y < n and not visited[next_y][next_x]:
                # 사다리 비용(높이 차) 계산
                next_w = abs(land[next_y][next_x] - land[y][x])
                # 높이 차이가 height보다 크면: 사다리 비용 그대로 넣고 heappush
                if next_w > height:
                    heapq.heappush(h, [next_w, next_x, next_y])
                # 높이 차이가 height 이하면: 사다리 비용으로 0 넣고 heappush
                else:
                    heapq.heappush(h, [0, next_x, next_y])
    return answer