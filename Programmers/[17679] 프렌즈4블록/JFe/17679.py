def solution(m, n, board):
    answer, temp, play = 0, [], True
    for i, b in enumerate(board):
        board[i] = list(b)
    # 더이상 지울게 없을 때까지 반복
    while play:
        play = False
        temp = []
        # 블록 확인
        for i in range(m-1):
            for j in range(n-1):
                # 2X2 형태면 temp에 append
                if board[i][j] != 0 and board[i][j] == board[i+1][j] == board[i][j+1] == board[i+1][j+1]:
                    temp.append((i, j))
                    play = True
        # 블록 지우기
        for i, j in temp:
            board[i][j] = 0
            board[i+1][j] = 0
            board[i][j+1] = 0
            board[i+1][j+1] = 0
        # 빈 공간 채우기
        for i in range(m-1):
            for j in range(n):
                # 밑에 빈 공간 있으면 내리기
                if board[i][j] != 0 and board[i+1][j] == 0:
                    board[i+1][j], board[i][j] = board[i][j], board[i+1][j]
                    # 해당 블록 위에 블록들도 내려주기
                    for k in range(i-1, 0, -1):
                        if board[k][j] != 0:
                            board[k][j], board[k+1][j] = board[k+1][j], board[k][j]
    # 지운 블록 수 체크
    for i in range(m):
        for j in range(n):
            if board[i][j] == 0:
                answer += 1
    return answer