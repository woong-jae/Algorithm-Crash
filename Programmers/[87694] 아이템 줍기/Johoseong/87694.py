from collections import deque
def bfs(matrix, start, item):
    visited = [[False] * 101 for _ in range(101)]
    que = deque()
    que.append([start[0], start[1], 0])

    while que:
        c_r, c_c, c_cnt = que.popleft()
        visited[c_r][c_c] = True
        if c_r == item[0] and c_c == item[1]:
            return c_cnt

        for d in [[-1, 0], [0, 1], [1, 0], [0, -1]]:
            n_r = c_r + d[0]
            n_c = c_c + d[1]
            n_cnt = c_cnt + 1
            if not (0 <= n_r <= 100 and 0 <= n_c <= 100) or visited[n_r][n_c] or not matrix[n_r][n_c]: # 범위밖이거나 이미 방문하거나 길 아님
                continue
            que.append([n_r, n_c, n_cnt])

def make_line(matrix, rectangle):
    cnt = 1
    for x1, y1, x2, y2 in rectangle:
        for c in range(x1 * 2, x2 * 2 + 1):
            matrix[y1 * 2][c] = cnt
            matrix[y2 * 2][c] = cnt
        for r in range(y1 * 2, y2 * 2 + 1):
            matrix[r][x1 * 2] = cnt
            matrix[r][x2 * 2] = cnt

    for x1, y1, x2, y2 in rectangle: # 겹치는 부분 지우기 (안쪽만 지워야됨)
        for c in range(x1 * 2 + 1, x2 * 2):
            for r in range(y1 * 2 + 1, y2 * 2):
                matrix[r][c] = 0

def solution(rectangle, characterX, characterY, itemX, itemY):
    matrix = [[0] * 101 for _ in range(101)]

    make_line(matrix, rectangle) # 테두리 그리기

    cnt = bfs(matrix, [characterY * 2, characterX * 2], [itemY * 2, itemX * 2]) # item까지 거리 count
    all_cnt = 0
    for i in range(101):
        for j in range(101):
            if matrix[i][j]:
                all_cnt += 1
    
    cnt = min(cnt, all_cnt - cnt)
    return round(cnt // 2)