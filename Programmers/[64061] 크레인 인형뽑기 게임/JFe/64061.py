def solution(board, moves):
    answer, bucket = 0, []
    for i in moves:     # moves 배열 반복
        for j in range(len(board[0])):
            if board[j][i-1] == 0:      # 0이 아닌 값 나올 때까지 반복, continue 이용해서 깊이 깊어지지 않도록
                continue
            if bucket and bucket[-1] == board[j][i-1]:  # stack의 top과 값이 같으면 append 하지 않고 pop한 뒤, answer 값 +2 증가
                board[j][i-1] = 0
                bucket.pop()
                answer += 2
                break
            # top과 값이 다르면 append
            bucket.append(board[j][i-1])
            board[j][i-1] = 0
            break
    return answer