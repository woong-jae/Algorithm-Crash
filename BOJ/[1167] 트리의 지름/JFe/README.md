# [1167] 트리의 지름 - Python

## 🔍 Algorithm
**DFS**

## 💻 Logic

```Python
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
```
- **가장 먼 노드 DFS 탐색**  


```Python
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
```
- **트리의 지름** : 임의의 노드에서 갈 수 있는 가장 먼 노드 찾고 -> 그 가장 먼 노드에서 갈 수 있는 가장 먼 노드까지의 거리  


## 📝 Review

모든 경우의 수를 다 구해서 비교하는 방법 말고는 생각나지 않아서 풀이 참고 했는데 멋지다,,  
[https://www.acmicpc.net/board/view/83695](https://www.acmicpc.net/board/view/83695)  
단순하면서도 어떻게 이런 생각을 하지  