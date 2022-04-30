from collections import deque

def solution(board):
    answer = 100000
    N = len(board)
    check = [[[100000] * N for i in range(N)] for j in range(4)]
    queue = deque()

    queue.append((0, 0, 0, 0)) # r, c, 방향, 값
    queue.append((0, 0, 1, 0))

    while queue:
        now_r, now_c, now_dir, now_cost = queue.popleft()
        
        for next_dir, D in enumerate([[0, 1], [1, 0], [0, -1], [-1, 0]]): # 오 아래 왼 위 (0 1 2 3)
            cost = 0
            next_r = now_r + D[0]
            next_c = now_c + D[1]

            # if now_dir + 2 == next_dir or now_dir - 2 == next_dir: # 180도 반대 방향이면 그냥 볼필요 없음
            #     continue

            if 0 <= next_r < N and 0 <= next_c < N and board[next_r][next_c] == 0: # 다음칸이 벽 아니고 & 격자 안임
                if now_dir == next_dir: # 방향 같으면 100만 증가
                    cost = 1 + now_cost
                else: # 90도 꺾이면 100 + 500 증가
                    cost = 6 + now_cost

                if check[next_dir][next_r][next_c] > cost: # 더 작은 값 발생
                    check[next_dir][next_r][next_c] = cost
                    queue.append([next_r, next_c, next_dir, cost]) 
    
    for i in range(4):
        answer = min([answer, check[i][N - 1][N - 1]])

    return answer * 100