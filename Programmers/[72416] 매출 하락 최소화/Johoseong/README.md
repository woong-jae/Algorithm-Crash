# [72416] 매출 하락 최소화
## Algorithm
- DP, DFS
## Logic
- 크게 생각하면, 조직도의 리프노드(= 그냥 팀원)부터 참가 여부를 판단하며 최소 비용을 구해가는 Bottom-Up 형식으로 생각해야 한다.
- 자식노드를 갖는 노드는 무조건 한 팀의 **팀장**임에 유의.
- 한 팀원에 최소 한명은 참석을 해야한다. 그러므로 팀원마다 (1)참가 (2)불참이라는 경우가 생긴다.
- 중요한 점은 **팀원이 다른 팀의 팀장이어서 이미 참석하는 경우의 존재**를 고려해야한다.  
1. 먼저, DFS를 사용해서 리프노드부터 참석/불참 여부를 판단함 (리프노드 불참의 경우 따로 대체할 수 없으므로 그냥 0)
2. 밑에서 올라온 다른 팀의 참석자 비용은 sum_child로 기억해둠
3. 팀장이 참석하는 경우 = 다른 팀 참석자 비용 + 팀장 참석 비용
```python
def dfs(graph, sales, DP, node): # node는 팀장
    if not graph[node]: # 1.
        DP[node][0] = sales[node]
        return

    sum_child = 0
    for child in graph[node]:
        dfs(graph, sales, DP, child)
        sum_child += min(DP[child][0], DP[child][1]) # 2.
    DP[node][0] = sum_child + sales[node] # 3.
```
4. 현재 팀원 중, **다른 팀의 팀장으로서 참석하는 경우**가 있는지 판단.
- 그런 경우가 있으면, 이미 그 팀원의 참석으로 인해 ```밑의 다른팀 참석 + 우리팀 참석```을 충족하게 됨. 그래서 현재 팀장이 불참해도 비용은 sum_child(다른 팀의 비용)로 충분해짐.
- 그런 경우가 없으면, 우리 팀원 중 비용이 작은 것과 sum_child를 합한게 지금까지의 참가비용이 됨.
```python
    attend = False
    for child in graph[node]: # 4.
        if DP[child][1] > DP[child][0]:
            attend = True
    if attend: 
        DP[node][1] = sum_child
    else: 
        DP[node][1] = sum_child + min((DP[k][0] - DP[k][1] for k in graph[node]), default=0)
```

## Review
진짜 어렵다.. 질문하기에 상세하게 설명한 글이 있어서 어떻게 풀어야하는지 이해하는 데 도움이 많이 됐다. 구현 자체도 정말 어렵다;; 바텀업으로 올라가는 생각을 해내는게 정말 대단한 것 같다.. 졌잘싸..