import sys
sys.setrecursionlimit(10 ** 6)

def dfs(x):
    if orders[x] and visited[orders[x]] == 0: # 먼저 들려야하는 선수될 방 있고, 선수될 방이 아직 방문 안된 상태면?
        after[orders[x]] = x # 선수될 방 방문 됐으면, 현재 방 어떻게든 들릴 수 있음 -> 바로 현재 방 방문하게 after 배열에 체크
        return

    visited[x] = 1

    for next_x in tree[x]:
        if visited[next_x] == 0: # 이어진 방 중에 방문 안했으면 방문
            dfs(next_x)
    
    if after[x] != -1: # 지금 방 방문으로 인해서 방문 가능해진 방 바로 방문
        dfs(after[x])

def solution(n, path, order):
    global tree, orders, after, visited
    tree = [[] for _ in range(n)]
    visited = [0] * n
    orders = [0] * n
    after = [-1] * n

    for p in path:
        a, b = p
        tree[a].append(b)
        tree[b].append(a)

    for o in order:
        a, b = o
        orders[b] = a
 
    dfs(0)

    return True if min(visited) != 0 else False # 방문 배열 다 1이면 True, 0인거 하나라도 있으면 False

print(solution(9, [[8,1],[0,1],[1,2],[0,7],[4,7],[0,3],[7,5],[3,6]], [[4,1],[5,2]]))
