def solution(key, lock):
    m, n = len(key), len(lock)
    for angle in range(4):
        # 처음 제외하고, 시계 방향 회전
        if angle != 0:
            temp = [[0 for _ in range(m)]for _ in range(m)]
            for i in range(m):
                for j in range(m):
                    temp[j][m - 1 - i] = key[i][j]
            key = temp
        # key 옮기면서 확인
        for i in range(m + n):
            for j in range(m + n):
                clear = True
                # 큰 배열 grid 만들어서 key 옮기기
                grid = [[-1 for _ in range(n + m * 2)] for _ in range(n + m * 2)]
                for r in range(m):
                    for c in range(m):
                        grid[i+r][j+c] = key[r][c]
                # grid 상 key와 lock 비교
                for r in range(n):
                    for c in range(n):
                        if grid[m-1+r][m-1+c] == -1 and lock[r][c] == 0:    # key가 벗어났는데 그 부분이 자물쇠 홈인 경우
                            clear = False
                        elif grid[m-1+r][m-1+c] != -1 and not grid[m-1+r][m-1+c]^lock[r][c]:   # key와 lock의 값이 같은 경우 (맞지 않는 경우)
                            clear = False
                if clear: 
                    return True
    return False