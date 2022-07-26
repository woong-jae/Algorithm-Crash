def check(n, board):
    for x, y, a in board:
        if a == 0: # 기둥
            if y == 0: continue # 바닥 위
            elif [x, y - 1, 0] in board: continue # 다른 기둥 위
            elif [x - 1, y, 1] in board or [x, y, 1] in board: continue # 보 한쪽 위
            else:
                return False
        else: # 보
            if [x, y - 1, 0] in board or [x + 1, y - 1, 0] in board:
                continue
            if [x - 1, y, 1] in board and [x + 1, y, 1] in board:
                continue
            return False
    return True

def solution(n, build_frame):
    board = [] # 0: 기둥, 1: 보

    for x, y, a, b in build_frame:
        if b == 0: # 삭제
            board.remove([x, y, a])
            flag = check(n, board)
            if flag == False:
                board.append([x, y, a])
        else: # 설치
            board.append([x, y, a])
            flag = check(n, board)
            if flag == False:
                board.remove([x, y, a])
    board.sort()
    return board

print(solution(5, [[0,0,0,1],[2,0,0,1],[4,0,0,1],[0,1,1,1],[1,1,1,1],[2,1,1,1],[3,1,1,1],[2,0,0,0],[1,1,1,0],[2,2,0,1]]))