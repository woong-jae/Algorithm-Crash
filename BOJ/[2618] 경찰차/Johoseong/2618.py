import sys
import copy
input = sys.stdin.readline

def calc(police, event):
    return abs(police[0] - event[0]) + abs(police[1] - event[1])

N = int(input())
W = int(input())
dp = [[[0 for _ in range(3)] for _ in range(3)] for _ in range(W + 1)]
police1 = [1, 1]
police2 = [N, N]
events = [[0, 0] for _ in range(W + 1)]
answer = [[], [1], [2]]

for i in range(1, W + 1):
    r, c = map(int, input().split())
    events[i][0] = r
    events[i][1] = c

    if i == 1:
        dp[i][1] = [1, 0, calc(police1, [r, c])] # 1번째 사건을 1번 경찰차가 감
        dp[i][2] = [0, 1, calc(police2, [r, c])] # 1번째 사건을 2번 경찰차가 감
        continue
    
    tmp1 = []
    tmp2 = []

    if dp[i - 1][1][0] == 0:
        past1_cur1 = dp[i - 1][1][2] + calc(police1, [r, c])
    else:
        past1_cur1 = dp[i - 1][1][2] + calc(events[dp[i - 1][1][0]], [r, c])
    if dp[i - 1][2][0] == 0:
        past2_cur1 = dp[i - 1][2][2] + calc(police1, [r, c])
    else:
        past2_cur1 = dp[i - 1][2][2] + calc(events[dp[i - 1][2][0]], [r, c])

    if past1_cur1 < past2_cur1:
        dp[i][1][0] = i
        dp[i][1][1] = dp[i - 1][1][1]
        dp[i][1][2] = past1_cur1
        tmp1 = copy.deepcopy(answer[1])
        tmp1.append(1)
    else:
        dp[i][1][0] = i
        dp[i][1][1] = dp[i - 1][2][1]
        dp[i][1][2] = past2_cur1
        tmp1 = copy.deepcopy(answer[2])
        tmp1.append(1)

    if dp[i - 1][1][1] == 0:
        past1_cur2 = dp[i - 1][1][2] + calc(police2, [r, c])
    else:
        past1_cur2 = dp[i - 1][1][2] + calc(events[dp[i - 1][1][1]], [r, c])
    if dp[i - 1][2][1] == 0:
        past2_cur2 = dp[i - 1][2][2] + calc(police2, [r, c])
    else:
        past2_cur2 = dp[i - 1][2][2] + calc(events[dp[i - 1][2][1]], [r, c])
    
    if past1_cur2 < past2_cur2:
        dp[i][2][0] = dp[i - 1][1][0]
        dp[i][2][1] = i
        dp[i][2][2] = past1_cur2
        tmp2 = copy.deepcopy(answer[1])
        tmp2.append(2)
    else:
        dp[i][2][0] = dp[i - 1][2][0]
        dp[i][2][1] = i
        dp[i][2][2] = past2_cur2
        tmp2 = copy.deepcopy(answer[2])
        tmp2.append(2)

    answer[1] = tmp1
    answer[2] = tmp2

if dp[W][1][2] < dp[W][2][2]:
    print(dp[W][1][2])
    for i in range(W):
        print(answer[1][i])
else:
    print(dp[W][2][2])
    for i in range(W):
        print(answer[2][i])
print(answer)