from itertools import permutations
from collections import deque
import copy
def find_move(board, cur, d):
    sub = 2 # 한칸 이동은 어차피 봤으니까 2부터 시작
    next_r = cur[0] + 2 * d[0]
    next_c = cur[1] + 2 * d[1]
    while True:
        if next_r < 0 or next_r > 3 or next_c < 0 or next_c > 3:
            if sub == 2:
                return False, [0, 0]
            else:
                return True, [d[0] * (sub - 1), d[1] * (sub - 1)]
        if board[next_r][next_c] != 0:
            return True, [d[0] * sub, d[1] * sub]
        next_r += d[0]
        next_c += d[1]
        sub += 1

def bfs(board, start, end):
    visited = [[10 ** 5] * 4 for _ in range(4)]
    que = deque()
    que.append([start[0], start[1], 0]) # 시작점, 횟수
    
    while que:
        cur_r, cur_c, cnt = que.popleft()
        if visited[cur_r][cur_c] < cnt: continue
        if cur_r == end[0] and cur_c == end[1]: 
            return cnt
        visited[cur_r][cur_c] = cnt

        for d in [[-1, 0], [0, 1], [1, 0], [0, -1]]:
            next_r = cur_r + d[0]
            next_c = cur_c + d[1]
            next_cnt = cnt + 1
            if next_r < 0 or next_r > 3 or next_c < 0 or next_c > 3:
                continue
            que.append([next_r, next_c, next_cnt])
            if board[next_r][next_c] == 0:
                flag, ctrl = find_move(board, [cur_r, cur_c], d) # 지금 방향 + 컨트롤 이동 구하기
                if flag == False: continue
                que.append([cur_r + ctrl[0], cur_c + ctrl[1], next_cnt])

def solution(board, r, c):
    answer = 10 ** 10
    cards = dict()
    for i in range(4): # 카드번호 별로 위치 저장
        for j in range(4):
            n = board[i][j]
            if n != 0:
                cards.setdefault(n, [])
                cards[n].append([i, j])

    num = [i for i in cards]
    permuts = list(permutations(num, len(num))) # 지우는 순서 경우의 수 다 구함

    for per in permuts:
        b = copy.deepcopy(board)
        tmp = 0
        start = [r, c]
        for p in list(per): # 카드쌍 안에서도 뭐 먼저 거치냐에 따라 값 달라짐 -> 두 경우 모두 고려해서 작은 값으로 
            end1, end2 = cards[p][0], cards[p][1]
            case1 = bfs(b, start, end1)
            case2 = bfs(b, start, end2)
            case1 += bfs(b, end1, end2)
            case2 += bfs(b, end2, end1)
            if case1 < case2:
                tmp += case1
                start = end2
            else:
                tmp += case2
                start = end1
            tmp += 2
            b[end1[0]][end1[1]] = 0
            b[end2[0]][end2[1]] = 0
        answer = min(answer, tmp)
        # print(per, answer)
    return answer