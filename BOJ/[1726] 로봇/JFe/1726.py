import sys
from collections import deque

def bfs(x, y, d):
    visited[y][x][d] = True
    q = deque([(x, y, d, 0)])
    while q:
        x, y, d, v = q.popleft()
        # 마지막 위치, 방향과 맞으면 return
        if x == end_x - 1 and y == end_y - 1 and d == end_d - 1: return v
        # 1, 2 또는 3만큼 이동
        for i in range(1, 4):
            next_x, next_y = x + dx[d] * i, y + dy[d] * i
            # boundary 체크
            if 0 > next_x or next_x >= N or 0 > next_y or next_y >= M or field[next_y][next_x]: break
            if not visited[next_y][next_x][d]:
                visited[next_y][next_x][d] = True
                q.append((next_x, next_y, d, v + 1))
        # 방향 변경
        for next_d in rotate[d]:
            if not visited[y][x][next_d]:
                visited[y][x][next_d] = True
                q.append((x, y, next_d, v + 1))
    return -1

M, N = map(int,sys.stdin.readline().split())
field = [[int(x) for x in sys.stdin.readline().split()] for _ in range(M)]
visited = [[[False for _ in range(4)] for _ in range(N)] for _ in range(M)]
start_y, start_x, start_d = map(int, sys.stdin.readline().split())
end_y, end_x, end_d = map(int, sys.stdin.readline().split())
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
rotate = {0: [2, 3], 1: [2, 3], 2: [0, 1], 3: [0, 1]}

print(bfs(start_x - 1, start_y - 1, start_d - 1))