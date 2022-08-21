import sys
from itertools import permutations

def calculate(ports, permut, index, num, dist):
    global answer, rs, cs, re, ce
    if index == len(permut) -1: # 마지막 텔레포트면
        answer = min(answer, dist + distance(ports[permut[index]][num], [re, ce]))
        return

    if index == -1: # start -> port면
        d1 = distance([rs, cs], ports[permut[0]][0])
        d2 = distance([rs, cs], ports[permut[0]][1])
        calculate(ports, permut, 0, 1, dist + d1 + 10)
        calculate(ports, permut, 0, 0, dist + d2 + 10)
    else: # port -> port
        d1 = distance(ports[permut[index]][num], ports[permut[index + 1]][0])
        d2 = distance(ports[permut[index]][num], ports[permut[index + 1]][1])
        calculate(ports, permut, index + 1, 1, dist + d1 + 10)
        calculate(ports, permut, index + 1, 0, dist + d2 + 10)

def distance(start, end):
    return abs(start[0] - end[0]) + abs(start[1] - end[1])

global answer, rs, cs, re, ce
input = sys.stdin.readline
rs, cs = map(int, input().split())
re, ce = map(int, input().split())
port = []
permuts = []
answer = distance([rs, cs], [re, ce]) # 디폴트 : 시작점<->목적지 거리

for _ in range(3):
    tr1, tc1, tr2, tc2 = map(int, input().split())
    port.append([[tr1, tc1], [tr2, tc2]])

for i in range(1, 4): # 텔레포트 이용하는 경우의 수
    permuts.append(list(permutations([0, 1, 2], i)))

for i in range(3):
    for permut in permuts[i]:
        calculate(port, permut, -1, -1, 0)

print(answer)