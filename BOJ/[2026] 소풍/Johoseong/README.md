# [2026] 소풍
## Algorithm
- DFS
## Logic
- 친구 정보 인접 행렬을 만든 후, 친구 관계를 탐색하는 DFS를 시행해주면 됨
- 주의할 점은 A-B, B-C 친구라고 A-C가 친구인거 아님 -> DFS 시 현재까지의 친구 리스트를 갖고 있어야함
- so, 현재 노드와 친구 리스트의 친구들이 모두 서로 친구인지 확인해주는 과정 필요
```python
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
```

## Review
N의 크기가 별로 안커서 큰 문제 없이 DFS로 풀 수 있었다.
