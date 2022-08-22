from collections import deque
import sys
input = sys.stdin.readline

def bfs(start, end):
    global M, N
    que = deque()
    que.append(start + [0]) # 현재 행, 열, 방향, 이전 이동의 직전 유무, 명령 횟수

    while que:
        c_r, c_c, c_d, c_cnt = que.popleft()
        visited[c_r][c_c][c_d] = 1

        if c_r == end[0] and c_c == end[1] and c_d == end[2]:
            print(c_cnt)
            exit()

        for s in range(1, 4):
            n_r = c_r + D[c_d][0] * s
            n_c = c_c + D[c_d][1] * s
            n_d = c_d
            if not (0 <= n_r < M and 0 <= n_c < N) or matrix[n_r][n_c] == 1: break
            if visited[n_r][n_c][n_d] == 1: continue
            que.append([n_r, n_c, n_d, c_cnt + 1])
            visited[n_r][n_c][n_d] = 1

        for n_d in range(4):
            if visited[c_r][c_c][n_d] == 1: continue
            n_cnt = c_cnt
            if (c_d == 0 and n_d == 1) or (c_d == 1 and n_d == 0) or (c_d == 2 and n_d == 3) or (c_d == 3 and n_d == 2):
                n_cnt += 2 # 방향 이동 + 칸 이동
            else:
                n_cnt += 1
            que.append([c_r, c_c, n_d, n_cnt])

global M, N
M, N = map(int, input().split())
matrix = []
for _ in range(M):
    matrix.append(list(map(int, input().split())))
sr, sc, sd = map(int, input().split())
er, ec, ed = map(int, input().split())
visited = [[[0 for _ in range(4)] for _ in range(N)] for _ in range(M)]
D = [[0, 1], [0, -1], [1, 0], [-1, 0]] # 동 서 남 북

bfs([sr - 1, sc - 1, sd - 1], [er - 1, ec - 1, ed - 1])
