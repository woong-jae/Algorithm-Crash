def check_partition(room, r1, c1, r2, c2):
    if r1 == r2: # row 같음
        if room[r1][int((c1 + c2) / 2)] == 'X':
            return 1
    elif c1 == c2: # col 같음
        if room[int((r1 + r2) / 2)][c1] == 'X':
            return 1
    else: # 대각선 경우
        if r1 > r2:
            if room[r1 - 1][c1] == 'X' and room[r2 + 1][c2] == 'X':
                return 1
        else:
            if room[r2 - 1][c2] == 'X' and room[r1 + 1][c1] == 'X':
                return 1
    return 0
        
def check_dist(room, r, c):
    for i in range(max(0, r - 2), min(5, r + 3)):
        for j in range(max(0, c - 2), min(5, c + 3)):
            if room[i][j] == 'P':
                dist = (abs(r - i) + abs(c - j))
                if dist == 1:
                    return 0
                if dist == 2 and check_partition(room, r, c, i, j) == 0:
                        return 0
    return 1

def solution(places):
    answer = []

    for room in places:
        ans = 1

        for i in range(5):
            for j in range(5):
                if room[i][j] == 'P':
                    ans = min(check_dist(room, i, j), ans)

        answer.append(ans)

    return answer
