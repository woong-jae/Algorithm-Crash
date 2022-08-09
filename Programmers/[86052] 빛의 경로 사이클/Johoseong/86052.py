D = [[-1, 0], [0, 1], [1, 0], [0, -1]]

def cycle(grid, matrix, r, c, d):
    global R, C
    cnt = 0
    cur_r, cur_c, cur_d = r, c, d
    while True: # 경로 따라 가봄
        if matrix[cur_r][cur_c][cur_d]: # 이미 해당 경로 간 적 있으면?
            break
        matrix[cur_r][cur_c][cur_d] = True
        cnt += 1

        next_r = cur_r + D[cur_d][0] # 다음 노드 정해줌
        next_c = cur_c + D[cur_d][1]
        if next_r < 0: next_r = R - 1
        elif next_r >= R: next_r = 0
        if next_c < 0: next_c = C - 1
        elif next_c >= C: next_c = 0

        if grid[next_r][next_c] == 'S': # 다음 노드 뭐냐에 따라 방향 정해줌
            next_d = cur_d
        elif grid[next_r][next_c] == 'R':
            next_d = cur_d + 1 if cur_d < 3 else 0
        else:
            next_d = cur_d - 1 if cur_d > 0 else 3
        cur_r, cur_c, cur_d = next_r, next_c, next_d
        
    if cur_r == r and cur_c == c and cnt != 0:
        return True, cnt
    else: return False, -1

def solution(grid):
    global R, C
    answer = []
    R, C = len(grid), len(grid[0])
    matrix = [[[False for _ in range(4)] for _ in range(C)] for _ in range(R)] # 시계방향 체크

    for r in range(R):
        for c in range(C):
            for d in range(4): # 시계방향
                flag, cnt = cycle(grid, matrix, r, c, d)
                if flag: answer.append(cnt)
    answer.sort()
    return answer
