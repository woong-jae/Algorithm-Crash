def solution(board, aloc, bloc):
    def dfs(ax, ay, bx, by):
        result = 0
        # 플레이어가 밟고 있는 발판이 사라졌다면
        if visited[ax][ay]: return 0
        # 상하좌우 탐색
        for d in range(4):
            next_x = ax + dx[d]
            next_y = ay + dy[d]
            if 0 <= next_x < r and 0 <= next_y < c:
                if visited[next_x][next_y] or board[next_x][next_y] == 0: continue
                visited[ax][ay] = True
                # 이동시켰을 때 턴의 수
                count = dfs(bx, by, next_x, next_y) + 1
                visited[ax][ay] = False
                # 현재 턴은 패배, 새로 계산된 턴은 승리인 경우
                if result % 2 == 0 and count % 2 == 1:
                    result = count
                # 현재 턴과 새로운 턴 모두 패배인 경우
                elif result % 2 == 0 and count % 2 == 0:
                    result = max(result, count)
                # 현재 턴과 새로운 턴 모두 승리인 경우
                elif result % 2 == 1 and count % 2 == 1:
                    result = min(result, count)
        return result
    r, c = len(board), len(board[0])
    visited = [[False for _ in range(5)] for _ in range(5)]
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    
    answer = dfs(aloc[0], aloc[1], bloc[0], bloc[1])
    return answer