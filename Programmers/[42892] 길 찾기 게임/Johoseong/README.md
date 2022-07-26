# [42892] 길 찾기 게임
## Algorithm
- Binary Tree, postorder, preorder
## Logic
- 잘 생각해보면, nodeinfo를 y값 기준 내림차순 해주면 더 이상 y값을 고려하지 않아도 됨! (트리가 만들어질 수 있는 경우만 input으로 들어온다 했으므로)
- 그러므로, 정렬 후에는 x의 크기만 고려해서 일반적인 이진트리를 만들면 됨.
1. nodeinfo를 y값 기준 정렬 + 현재 노드 번호를 추가해줌
```python
nodes = [[i + 1, node[0], node[1]] for i, node in enumerate(nodeinfo)]
nodes.sort(key=lambda x: x[2], reverse=True) # y값 기준 내림차순 정렬 -> 이제 y값 고려 안해도됨
```
2. 정렬된 노드의 **x값 기준으로 이진트리 생성**
- tree의 인덱스(노드 번호)에는 ```[해당 노드의 x값], [left 노드 번호, right 노드 번호]``` 정보를 넣음
- -1은 자식 노드 없다는 뜻
```python
def make_tree(cur, node):
    global tree
    # 현재 위치의 노드가 자식 X
    if tree[cur][1][0] == -1 and tree[cur][0][0] > node[1]: # 추가할 노드의 x값 < 노드의 x값
        tree[cur][1][0] = node[0]
        tree[node[0]].append([node[1]])
        tree[node[0]].append([-1, -1])
        return
    elif tree[cur][1][1] == -1 and tree[cur][0][0] < node[1]: # 추가할 노드의 x값 > 노드의 x값
        tree[cur][1][1] = node[0]
        tree[node[0]].append([node[1]])
        tree[node[0]].append([-1, -1])
        return
    # 현재 위치의 노드가 자식 O
    if node[1] < tree[cur][0][0]: # 추가할 노드의 x값 < 노드의 x값
        next = tree[cur][1][0]
        make_tree(next, node)
    elif node[1] > tree[cur][0][0]: # 추가할 노드의 x값 > 노드의 x값
        next = tree[cur][1][1]
        make_tree(next, node)
```
3. 생성된 트리에서 preorder, postorder 진행

## Review
아이디어는 금방 떠올랐는데 갖고있어야할 정보가 여러개라서 구현이 시간 좀 걸렸다;;