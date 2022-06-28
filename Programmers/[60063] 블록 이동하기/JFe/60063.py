from collections import deque

def search(location, board):
    next = []
    location = list(location)
    x1, y1, x2, y2 = location[0][0], location[0][1], location[1][0], location[1][1]
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    # 상하좌우 이동 확인
    for i in range(4):
        next_x1, next_y1, next_x2, next_y2 = x1 + dx[i], y1 + dy[i], x2 + dx[i], y2 + dy[i]
        if board[next_x1][next_y1] == 0 and board[next_x2][next_y2] == 0:
            next.append({(next_x1, next_y1), (next_x2, next_y2)})
    # 가로인 경우 회전 확인
    if x1 == x2:
        for i in [-1, 1]:
            if board[x1 + i][y1] == 0 and board[x2 + i][y2] == 0:
                next.append({(x1, y1), (x1 + i, y1)})
                next.append({(x2, y2), (x2 + i, y2)})
    # 세로인 경우 회전 확인
    elif y1 == y2:
        for i in [-1, 1]:
            if board[x1][y1 + i] == 0 and board[x2][y2 + i] == 0:
                next.append({(x1, y1), (x1, y1 + i)})
                next.append({(x2, y2), (x2, y2 + i)})
    return next

def solution(board):
    n = len(board)
    # 가장자리 필드 추가
    new_board = [[1 for _ in range(n+2)] for _ in range(n+2)]
    for i in range(n):
        for j in range(n):
            new_board[i+1][j+1] = board[i][j]
    # BFS 탐색
    q = deque()
    q.append(({(1, 1), (1, 2)}, 0))
    visited = []
    while q:
        location, count = q.popleft()
        print(location)
        # 도착하면 count 반환
        if (n, n) in location:
            return count
        # 다음 위치 확인
        for next in search(location, new_board):
            if next not in visited:
                visited.append(next)
                q.append((next, count + 1))