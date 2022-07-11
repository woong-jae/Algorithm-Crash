from collections import defaultdict, deque
from itertools import permutations
from copy import deepcopy

def bfs(board, start, end):   # 조작 횟수 Count
    if start == end: return 0
    queue = deque([[start[0], start[1], 0]])
    visit = {start}
    while queue:                    # BFS
        x, y, c = queue.popleft()
        for dx, dy in [(0,1),(0,-1),(1,0),(-1,0)]:
            nx, ny = x+dx, y+dy     # 방향키 이동
            cx, cy = x, y
            while True:             # Ctrl + 방향키 이동
                cx, cy = cx+dx, cy+dy
                if not (0 <= cx <= 3 and 0 <= cy <= 3):
                    cx, cy = cx-dx, cy-dy
                    break
                elif board[cx][cy] != 0:
                    break
            if (nx, ny) == end or (cx, cy) == end:  # 도착 최단 경로
                return c+1
            if (0 <= nx <= 3 and 0 <= ny <= 3) and (nx, ny) not in visit:
                queue.append((nx, ny, c+1))
                visit.add((nx, ny))
            if (cx, cy) not in visit:
                queue.append((cx, cy, c+1))
                visit.add((cx, cy))

def compare(board, card, curr, order, cost):
    if len(order) == 0: return cost   # 모든 카드를 확인한 경우
    idx = order[0]
    # 현재 위치에서 A1까지 조작 횟수 + A1->A2까지의 조작 횟수 + 2(카드 선택)
    choice1 = bfs(board, curr, card[idx][0]) + bfs(board, card[idx][0], card[idx][1]) + 2
    # 현재 위치에서 A2까지 조작 횟수 + A2->A1까지의 조작 횟수 + 2(카드 선택)
    choice2 = bfs(board, curr, card[idx][1]) + bfs(board, card[idx][1], card[idx][0]) + 2
    # 선택한 카드 0으로 변경
    new_board = deepcopy(board)
    new_board[card[idx][0][0]][card[idx][0][1]] = 0
    new_board[card[idx][1][0]][card[idx][1][1]] = 0
    # 적은 조작 횟수를 한 경우를 따라 재귀
    if choice1 < choice2:
        return compare(new_board, card, card[idx][1], order[1:], cost + choice1)
    else:
        return compare(new_board, card, card[idx][0], order[1:], cost + choice2)

def solution(board, r, c):
    answer = float('inf')
    card = defaultdict(list)
    # board 정보 저장
    for row in range(4):
        for col in range(4):
            num = board[row][col]
            if num != 0:
                card[num].append((row, col))
    # 경우의 수 순열 생성
    for case in permutations(card.keys(), len(card)):
        answer = min(answer, compare(board, card, (r, c), case, 0))
    return answer