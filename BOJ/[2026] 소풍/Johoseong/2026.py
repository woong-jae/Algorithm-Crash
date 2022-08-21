import sys
input = sys.stdin.readline

def dfs(N, cur, arr):
    for i in arr: # 지금까지 친구관계<->현재 노드가 모두 친구 관계인지 확인
        if not friends[cur][i]:
            return [-1]

    if len(arr) + 1 == K: # 친구 관계가 K명 만족
        for i in (arr + [cur]):
            print(i)
        exit()

    for i in range(cur, N + 1):
        if friends[cur][i]:
            dfs(N, i, arr + [cur])

K, N, F = map(int, input().split())
friends = [[0] * (N + 1) for _ in range(N + 1)]
answer = []

for _ in range(F):
    a, b = map(int, input().split())
    friends[a][b] = b
    friends[b][a] = a

for i in range(1, N - K + 2):
    for j in range(i, N + 1):
        if friends[i][j]:
            answer = dfs(N, j, [i])
print(-1)