def is_bomb(lst):
    row = set()
    col = set()
    for r, c in lst:
        if r not in row:
            row.add(r)
        if c not in col:
            col.add(c)
    answer = []
    for r in row:
        for c in col:
            if (r,c) not in lst:
                answer.append((r,c))
    return answer

def can_del(lst,black):
    for block in lst:
        if block not in black:
            return False
    return True        

def solution(board):
    N = len(board)
    answer = 0
    updated = True
    while updated:
        block_info = {}
        black = set()
        check = [False for _ in range(N)]
        for i in range(N):
            for j in range(N):
                typed = board[i][j]
                if typed != 0:
                    if typed in block_info:
                        block_info[typed][0].append((i,j))
                    else:
                        block_info[typed] = [[(i,j)],list()]
                    check[j] = True
                else:
                    if not check[j]:
                        black.add((i,j))
        for typed in block_info:
            block_info[typed][1] = is_bomb(block_info[typed][0])

        updated = False
        for typed in block_info:
            if can_del(block_info[typed][1],black):
                # 삭제 가능
                answer +=1
                updated = True
                for i,j in (block_info[typed][0] + block_info[typed][1]):
                    board[i][j] = 0
    return answer
