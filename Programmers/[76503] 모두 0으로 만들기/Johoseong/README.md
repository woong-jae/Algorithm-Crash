# [76503] 모두 0으로 만들기
## Algorithm
- DFS
## Logic
- 트리라 cycle이 안생기므로, 최소의 정답을 구하기위한 별다른 알고리즘이 필요없음
- 즉, 단순하게 **bottom-up**으로 가중치를 부모에게 합쳐주면서 루트(0노드)까지 올라가면 됨
- 주의할 점 : ```edges```만으론 부모-자식 관게를 알 수 없어서 dfs 메소드의 파라미터에 부모 정보를 담아줘야함
```python
def dfs(par, cur, graph, weight):
    global answer
    childs = graph[cur]
    if len(childs) == 1 and childs[0] == par: # 리프노드임
        parent = childs[0]
        weight[parent] += weight[cur]
        answer += abs(weight[cur])
        weight[cur] = 0
        return

    for c in childs: # 자식노드로 dfs
        if c != par:
            dfs(cur, c, graph, weight)
    weight[par] += weight[cur] # 현재노드의 weight -> 부모노드로 합침
    answer += abs(weight[cur])
    weight[cur] = 0
```
- 전체 노드의 가중치 합이 0이 되는 경우만 가능하므로, 처음부터 sum을 통해 0이 아니면 -1 반환 가능
```python
if sum(weight) != 0:
    return -1
```

## Review
트리라고 주어졌는데도 cycle 생기는 케이스 고민하느라 오래걸렸다 ㅎㅎ.. 레벨3치곤 쉬운 문제였다