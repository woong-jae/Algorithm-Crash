def check_partition(r1, c1, r2, c2):
    if r1 == r2: # row 같음
        if place[r1][int((c1 + c2) / 2)] == 'X':
            return 1
    elif c1 == c2: # col 같음
        if place[int((r1 + r2) / 2)][c1] == 'X':
            return 1
    else: # 대각선 경우
        if r1 > r2:
            if place[r1 - 1][c1] == 'X' and place[r2 + 1][c2] == 'X':
                return 1
        else:
            if place[r2 - 1][c2] == 'X' and place[r1 + 1][c1] == 'X':
                return 1
    return 0
        
def check_dist(r, c):
    for i in range(5):
        for j in range(5):
            if place[i][j] == 'P':
                dist = (abs(r - i) + abs(c - j))
                if dist == 1: # 거리 1이면 바로 리턴 0
                    return 0
                if dist == 2 and check_partition(r, c, i, j) == 0: # 거리 2면 중간에 파티션 있는지 검사. 없으면 리턴 0
                    return 0
    return 1

def solution(places):
    global place
    answer = []

    for room in places:
        ans = 1
        place = []

        for r in room:
            place.append(list(r))

        for i in range(5):
            for j in range(5):
                if place[i][j] == 'P':
                    ans = min(check_dist(i, j), ans)

        answer.append(ans)

    return answer