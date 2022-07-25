import copy
def dfs(board, a, b, turn, cnt):
    global R, C
    if turn == 0: # 현재 A 차례
        cur_r, cur_c = a
    else: # 현재 B 차례
        cur_r, cur_c = b

    if board[cur_r][cur_c] == 0: # 이미 딛고있는 발판이 사라짐 -> 졌음
        return [False, cnt]

    can_move = False # 현재 위치에서 내가 이동 가능한지 판단
    winner = []
    loser = []
    for d in [[-1, 0], [1, 0], [0, -1], [0, 1]]:
        next_r = cur_r + d[0]
        next_c = cur_c + d[1]
        if not (0 <= next_r < R and 0 <= next_c < C) or (board[next_r][next_c] == 0):
            continue

        can_move = True
        next_board = copy.deepcopy(board)
        next_board[cur_r][cur_c] = 0 # 현재 딛고있는 발판 없앰
        if turn == 0: # 현재 A 차례면 B 차례로 넘어감
            win, new_cnt = dfs(next_board, [next_r, next_c], b, 1, cnt + 1)
        else: # 현재 B 차례면 A 차례로 넘어감
            win, new_cnt = dfs(next_board, a, [next_r, next_c], 0, cnt + 1)

        if win == False: # 상대가 졌음 == 내가 이김
            winner.append(new_cnt)
        else: # 상대가 이김
            loser.append(new_cnt)
    
    if can_move: # 내가 이동을 할 수 있는 상태면?
        if winner: # 내가 이김 -> 이긴 경우 중 이동경로 최소가 최선
            return [True, min(winner)]
        else: # 내가 짐 -> 진 경우 중 이동경로 최대가 최선
            return [False, max(loser)]
    else: # 내가 이동 불가능한 상태면? -> 내가 졌음
        return [False, cnt]

def solution(board, aloc, bloc):
    global R, C
    R = len(board)
    C = len(board[0])
    turn = 0 # 0이면 A차례, 1이면 B차례
    return dfs(board, aloc, bloc, turn, 0)[1]