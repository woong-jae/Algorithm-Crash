def solution(board, moves):
    answer = 0
    stack = [0] # 바구니

    for col in moves:
        for row in range(len(board)):
            if board[row][col - 1] != 0:
                if stack[-1] == board[row][col - 1]: # 바구니에 두개 연속 쌓임
                    answer += 2
                    stack.pop()
                else: # 바구니에 서로 다른거 쌓임
                    stack.append(board[row][col - 1])
                board[row][col - 1] = 0
                break
    
    return answer
