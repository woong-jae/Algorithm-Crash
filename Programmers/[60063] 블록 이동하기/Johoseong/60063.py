from collections import deque
def move(cur1, cur2, board):
    moving = []
    
    for d in [(-1, 0), (1, 0), (0, 1), (0, -1)]: # 이동
        nxt1 = (cur1[0] + d[0], cur1[1] + d[1])
        nxt2 = (cur2[0] + d[0], cur2[1] + d[1])
        if board[nxt1[0]][nxt1[1]] == 0 and board[nxt2[0]][nxt2[1]] == 0:
            moving.append((nxt1, nxt2))
    
    if cur1[0] == cur2[0]: # 회전
        for d in [-1, 1]:
            if board[cur1[0] + d][cur1[1]] == 0 and board[cur2[0] + d][cur2[1]] == 0:
                moving.append((cur1, (cur1[0] + d, cur1[1])))
                moving.append((cur2, (cur2[0] + d, cur2[1])))
    else:
        for d in [-1, 1]:
            if board[cur1[0]][cur1[1] + d] == 0 and board[cur2[0]][cur2[1] + d] == 0:
                moving.append(((cur1[0], cur1[1] + d), cur1))
                moving.append(((cur2[0], cur2[1] + d), cur2))  
    return moving

def solution(b):
    N = len(b)
    board = [[1] * (N + 2) for _ in range(N + 2)]
    for i in range(N):
        for j in range(N):
            board[i + 1][j + 1] = b[i][j]
    queue = deque([((1, 1), (1, 2), 0)])
    visited = set([((1, 1), (1, 2))])

    while queue:
        cur1, cur2, count = queue.popleft()
        if cur1 == (N, N) or cur2 == (N, N):
            return count
        for next in move(cur1, cur2, board):
            if next not in visited:
                queue.append((*next, count+1))
                visited.add(next)