def solution(board, skill):
    answer = 0
    R = len(board)
    C = len(board[0])
    sum_attack = [[0] * (C + 1) for _ in range(R + 1)]

    # 누적합 연산 위해서 skill에 따라 변화량 세팅
    for t, r1, c1, r2, c2, degree in skill:
        amount = degree
        if t == 1: amount *= (-1) # 공격이면 음수
        
        sum_attack[r1][c1] += amount
        sum_attack[r2 + 1][c2 + 1] += amount
        sum_attack[r1][c2 + 1] += (amount * -1)
        sum_attack[r2 + 1][c1] += (amount * -1)

    for r in range(R): # 누적합 연산 (가로)
        for c in range(C):
            sum_attack[r][c + 1] += sum_attack[r][c]
    for r in range(R): # 누적합 연산 (세로)
        for c in range(C):
            sum_attack[r + 1][c] += sum_attack[r][c]
    for r in range(R):
        for c in range(C):
            if board[r][c] + sum_attack[r][c] > 0:
                answer += 1
    return answer