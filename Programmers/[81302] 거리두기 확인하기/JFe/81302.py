def search(place, x, y):
    stack = [(x, y, 0)]
    visited = [[False for _ in range(5)]for _ in range(5)]
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    visited[y][x] = True
    # DFS 탐색
    while stack:
        x, y, d = stack.pop()
        if d == 2: continue     # 깊이 2까지만
        for i in range(4):
            next_x, next_y = x + dx[i], y + dy[i]
            if next_x < 0 or next_x >= 5 or next_y < 0 or next_y >= 5 or visited[next_y][next_x]: continue
            if place[next_y][next_x] == "P": return False
            if place[next_y][next_x] == "O": stack.append((next_x, next_y, d+1))
    return True
            
def solution(places):
    answer = []
    # 강의실마다 확인
    for place in places:
        check = True
        for i in range(5):
            for j in range(5):
                # 사람이 있는 경우에만 탐색하고, 반환값 체크
                if place[i][j] == 'P' and not search(place, j, i):
                    check = False
        # check에 따라 값 입력
        if check: answer.append(1)
        else: answer.append(0)
    return answer