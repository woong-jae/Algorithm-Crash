import sys
from collections import defaultdict

def dfs(node, dist):
    global max_dist, max_node
    # 최댓값 업데이트
    if max_dist < dist:
        max_dist = dist
        max_node = node
    visited[node] = True
    # 연결된 다음 노드 탐색
    for next_node, next_dist in graph[node]:
        if visited[next_node]: continue
        next_dist += dist
        dfs(next_node, next_dist)

V = int(sys.stdin.readline())
graph = defaultdict(list)
visited = [False for _ in range(V + 1)]
max_dist, max_node = 0, 0
# 트리 연결
for _ in range(V):
    temp = list(map(int, sys.stdin.readline().split()))
    for i in range(1, len(temp) - 1, 2):
        graph[temp[0]].append([temp[i], temp[i + 1]])
# 임의의 지점 1에서 갈 수 있는 가장 먼 노드 dfs 탐색
dfs(1, 0)
visited = [False for _ in range(V + 1)]
# dfs 탐색 후 나온 가장 먼 노드에서 갈 수 있는 가장 먼 노드 탐색
dfs(max_node, 0)
print(max_dist)