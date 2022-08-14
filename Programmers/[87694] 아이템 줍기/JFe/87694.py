from collections import deque

def bfs(field, characterX, characterY, itemX, itemY):
    visited = [[False for _ in range(101)]for _ in range(101)]
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    q = deque()
    q.append([characterX, characterY, 0])
    while q:
        x, y, v = q.popleft()
        visited[y][x] = True
        # itemX, itemY에 도착하면 종료
        if x == itemX and y == itemY: return v
        for i in range(4):
            nx, ny, nv = x + dx[i], y + dy[i], v + 1
            # 범위 안, 테두리 위, 방문 X면 BFS 탐색
            if 0 <= nx <= 100 and 0 <= ny <= 100 and field[ny][nx] and not visited[ny][nx]:
                q.append([nx, ny, nv])

def solution(rectangle, characterX, characterY, itemX, itemY):
    answer = 0
    field = [[False for _ in range(101)]for _ in range(101)]    # 크기 2배로 늘리기 (ㄷ자인 경우 처리하기 위함)
    # 직사각형 field에 표시
    for x1, y1, x2, y2 in rectangle:
        for i in range(y1*2, y2*2+1):
            for j in range(x1*2, x2*2+1):
                field[i][j] = True
    # 직사각형 내부 지우기
    for x1, y1, x2, y2 in rectangle:
        for i in range(y1*2+1, y2*2):
            for j in range(x1*2+1, x2*2):
                field[i][j] = False
    answer = bfs(field, characterX*2, characterY*2, itemX*2, itemY*2) / 2   # 2배로 늘렸기 때문에 2로 나눠서 return
    return answer