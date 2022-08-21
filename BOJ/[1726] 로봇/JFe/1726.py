import sys
from collections import deque

def bfs(x, y, d):
    visited[y][x][d] = True
    q = deque([(x, y, d, 0)])
    while q:
        x, y, d, v = q.popleft()
        if x == end_x-1 and y == end_y-1 and d == end_d-1: return v
        for i in range(1, 4):
            next_x, next_y = x + dx[d] * i, y + dy[d] * i
            if 0 <= next_x < M and 0 <= next_y < N and not visited[next_y][next_x][d]:
                if field[next_y][next_x]: break
                visited[next_y][next_x][d] = True
                q.append([next_x, next_y, d, v + 1])
        for next_d in rotate[d]:
            if not visited[y][x][next_d]:
                visited[y][x][next_d] = True
                q.append([x, y, next_d, v + 1])
    return -1

N, M = map(int, sys.stdin.readline().split())
field = [[int(x) for x in sys.stdin.readline().split()]for _ in range(N)]
visited = [[[False for _ in range(4)]for _ in range(M)]for _ in range(N)]
start_x, start_y, start_d = map(int, sys.stdin.readline().split())
end_x, end_y, end_d = map(int, sys.stdin.readline().split())
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]
rotate = {0: [1, 3], 1: [1, 3], 2: [0, 2], 3: [0, 2]}
print(bfs(start_x-1, start_y-1, start_d-1))
