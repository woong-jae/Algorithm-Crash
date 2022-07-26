def solution(board, skill):
    answer, r, c = 0, len(board), len(board[0])
    sum = [[0 for _ in range(c + 1)] for _ in range(r + 1)]
    # 누적합 계산 위해 skill 정보 저장
    for type, r1, c1, r2, c2, degree in skill:
        if type == 1: degree = -degree
        sum[r1][c1] += degree
        sum[r1][c2 + 1] -= degree
        sum[r2 + 1][c1] -= degree
        sum[r2 + 1][c2 + 1] += degree
    # 가로 누적합 계산
    for i in range(r):
        for j in range(c):
            sum[i][j + 1] += sum[i][j]
    # 세로 누적합 계산
    for i in range(r):
        for j in range(c):
            sum[i + 1][j] += sum[i][j]
    for i in range(r):
        for j in range(c):
            if sum[i][j] + board[i][j] > 0:
                answer += 1
    return answer