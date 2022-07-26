# [92343] 양과 늑대
## Algorithm
- DFS
## Logic
- 방문한 노드들을 ```path```에 저장하면서 탐색을 한다. 현재 ```path```에서 갈 수 있는 노드들을 탐색하고, 그때마다 최대 양의 개수를 갱신해나가면 풀린다.
1. DFS를 이용해서 탐색
- 노드 0에서 시작, ```path```에 현재 탐색 경로 저장
- 현재 노드가 양/늑대냐에 따라 count 해야함
2. 현재 탐색 경로인 ```path```에서 다음으로 갈 노드를 선택함. 선택한 노드는 ```path```에 저장하고 1번 과정 반복 -> 끝나면 해당 노드 pop (백트랙킹)
```python
def dfs(cur, info, path, sheep, wolf):
    global graph
    if info[cur] == 0: sheep += 1
    else: wolf += 1
    if sheep <= wolf:
        return 0
    
    MAX = sheep
    for p in path:
        for n in graph[p]:
            if n not in path:
                path.append(n)
                MAX = max(MAX, dfs(n, info, path, sheep, wolf))
                path.pop() # 백트랙킹
    return MAX
```

## Review
어려웠다. 처음에 무지성 백트랙킹했다가 시간초과나고.. 아마 처음 풀이는 중복 탐색이 많이 된 거 같다. 질문하기 보고 풀었다 휴!