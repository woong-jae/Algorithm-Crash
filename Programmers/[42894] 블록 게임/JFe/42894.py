def solution(board):
    # 빈 공간 채워넣을 수 있는지 확인(x좌표 고정)
    def find_fill(y, x):
        for i in range(y):
            if board[i][x]: return False
        return True
    # 블록 없앨 수 있는지 확인
    def find_delete(y, x, height, width):
        cnt, last = 0, -1
        if y + height > n or x + width > n: return False    # boundary 체크
        for i in range(y, y + height):
            for j in range(x, x + width):
                # 빈 공간인 경우
                if board[i][j] == 0:
                    if not find_fill(i, j): return False    # 채울 수 없으면 False
                    cnt += 1
                    if cnt > 2: return False    # 빈 공간이 3개 이상이면 False
                # 빈 공간 아닌 경우
                else:
                    if last == -1: last = board[i][j]   # 색깔 저장
                    elif last != board[i][j]: return False  # 색깔이 다르면 False
        # 블록 없애기
        for i in range(y, y + height):
            for j in range(x, x + width):
                board[i][j] = 0
        return True
    
    answer = 0
    n = len(board)
    # 없앨 수 있는 블록이 없을 때까지 반복
    while True:
        count = 0
        for i in range(n):
            for j in range(n):
                # 2X3 형태, 3X2 형태 각각 검사
                if find_delete(i, j, 2, 3) or find_delete(i, j, 3, 2): count += 1
        if count == 0: break
        answer += count
    return answer