from collections import deque

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

def check(grid, visited, x, y, d):
    r, c, cnt = len(grid), len(grid[0]), 0
    q = deque()
    q.append([x, y, d])
    # 빛 이동
    while q:
        cur_x, cur_y, cur_d = q.popleft()
        # 이미 방문했으면 break
        if visited[cur_y][cur_x][cur_d]: break
        visited[cur_y][cur_x][cur_d] = True
        # 다음 위치 계산 (격자 끝을 넘어가면 반대쪽 끝으로 가도록)
        next_x, next_y = cur_x + dx[cur_d], cur_y + dy[cur_d]
        if next_x == c: next_x = 0
        elif next_x == -1: next_x = c - 1
        if next_y == r: next_y = 0
        elif next_y == -1: next_y = r - 1
        # 다음 방향 계산
        if grid[next_y][next_x] == 'S': next_d = cur_d
        elif grid[next_y][next_x] == 'L': 
            if cur_d == 0: next_d = 3
            else: next_d = cur_d - 1
        elif grid[next_y][next_x] == 'R': 
            if cur_d == 3: next_d = 0
            else: next_d = cur_d + 1
        q.append([next_x, next_y, next_d])
        cnt += 1
    return cnt
        
def solution(grid):
    answer = []
    r, c = len(grid), len(grid[0])
    visited = [[[False for _ in range(4)]for _ in range(c)]for _ in range(r)]
    # 시작 지점, 방향 바꿔가면서 사이클 체크
    for i in range(r):
        for j in range(c):
            for k in range(4):
                result = check(grid, visited, j, i, k)
                if result != 0: answer.append(result)
    return sorted(answer)