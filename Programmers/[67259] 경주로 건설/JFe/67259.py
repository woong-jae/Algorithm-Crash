from collections import deque
import sys

def solution(board):
    answer, N = 0, len(board)
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    cost_list = [[[sys.maxsize for _ in range(4)]for _ in range(N)]for _ in range(N)]   # y좌표, x좌표, 방향에 해당하는 누적 최소 비용 저장
    x, y, d, cost = 0, 0, -1, 0
    # BFS 탐색
    q = deque()
    q.append((x, y, d, cost))
    while q:
        x, y, d, cost = q.popleft()
        for i in range(4):
            next_x, next_y = x + dx[i], y + dy[i]
            # boundary 안이고 벽이 아닌 경우
            if 0 <= next_x < N and 0 <= next_y < N and board[next_y][next_x] == 0:
                # 직선 도로 비용 추가
                next_cost = cost + 100
                # 방향이 달라지면 코너 비용 추가
                if d != i and d != -1:
                    next_cost += 500
                # 해당 cost_list 값보다 작으면 업데이트 하고 append
                if next_cost < cost_list[next_y][next_x][i]:
                    cost_list[next_y][next_x][i] = next_cost
                    q.append((next_x, next_y, i, next_cost))
    answer = min(cost_list[N-1][N-1])
    return answer