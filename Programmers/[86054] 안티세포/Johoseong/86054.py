import copy
import sys
sys.setrecursionlimit(10 ** 6)
def find(parent, target):
    if target == parent[target]:
        return target
    parent[target] = find(parent, parent[target])
    return parent[target]
 
def union(parent, a, b):
    a = find(parent, a)
    b = find(parent, b)
    if a > b:
        parent[b] = a
    else:
        parent[a] = b

def dfs(N, i, cells, parent):
    global cnt
    if i == N: # 종료조건
        cnt += 1
        return

    prev = -1
    prev_sum = 0
    i_sum = 0
    i_root = find(parent, i)
    for j in range(0, i + 1): # y세포 합과 x세포 합 구함
        if find(parent, j) == i_root:
            i_sum += cells[j]
        else:
            if prev != parent[j]:
                prev = parent[j]
                prev_sum = cells[j]
            else:
                prev_sum += cells[j]

    if prev == -1: # y가 없음 -> i증가
        dfs(N, i + 1, cells, parent)
    else: # y가 있음
        if i_sum == prev_sum: # x,y 세포 합 같음 -> 1. i증가 하거나 2. 세포 융합
            dfs(N, i + 1, cells, parent) # 1.
            parent2 = copy.deepcopy(parent)
            union(parent2, prev, i)
            dfs(N, i, cells, parent2) # 2.
        else: # x,y 세포 합이 다름 -> i증가
            dfs(N, i + 1, cells, parent)

def solution(a, s):
    global cnt
    answer = []

    for i in s:
        b = a[:i]
        cnt = 0
        parent = [i for i in range(len(b))]
        dfs(len(b), 0, b, parent)
        answer.append(cnt)
        a = a[i:]

    return answer