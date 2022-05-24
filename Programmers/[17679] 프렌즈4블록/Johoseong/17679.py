def solution(m, n, board):
    answer = 0
    field = [list(i) for i in board]

    while True:
        flag = 0
        remove = []

        for i in range(m - 1): # 2*2 같은 블록 찾음 (나중에 지워야해서 위치 기억해둠)
            for j in range(n - 1):
                if field[i][j] == 0:
                    continue

                if field[i][j] == field[i][j + 1] == field[i + 1][j] == field[i + 1][j + 1]:
                    flag = 1
                    remove.append([i, j])
                    remove.append([i, j + 1])
                    remove.append([i + 1, j])
                    remove.append([i + 1, j + 1]) 

        if flag == 0: # 지워지는 거 없음 -> 게임 종료
            break

        while remove: # 지움
            r, c = remove.pop()
            field[r][c] = 0

        for i in range(m - 1): # 위에 있는거 내림
            for j in range(n):
                if field[i + 1][j] == 0 and field[i][j] != 0:
                    field[i + 1][j] = field[i][j]
                    field[i][j] = 0

                    for k in range(i - 1, 0, -1):
                        field[k + 1][j] = field[k][j]
                        field[k][j] = 0
        # print(field)
                
    for i in range(m): # 터진거 개수 셈
        for j in range(n):
            if field[i][j] == 0:
                answer += 1     

    return answer