import sys
from collections import defaultdict

def dfs(num, l):
    if len(l) == K:
        for v in l: print(v)
        exit()
    for i in range(num + 1, N + 1):
        if not visited[i]:
            for v in l:
                if v not in adj[i]: break
            else:
                visited[i] = True
                dfs(i, l+[i])

K, N, F = map(int, sys.stdin.readline().split())
adj = defaultdict(list)
# 인접 리스트 생성
for i in range(F):
    a, b = map(int, sys.stdin.readline().split())
    adj[a].append(b)
    adj[b].append(a)
# 인접 리스트 기준으로 DFS 탐색
for i in range(1, N + 1):
    visited = defaultdict(bool)
    visited[i] = True
    dfs(i, [i])
print(-1)