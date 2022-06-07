import sys
sys.setrecursionlimit(10**6)

def make_tree(cur, node):
    global tree
    # 자식 없는 경우
    if tree[cur][1][0] == -1 and tree[cur][0][0] > node[1]:
        tree[cur][1][0] = node[0] # cur 노드 자식 번호 갱신
        tree[node[0]].append([node[1]]) # tree에 node 추가
        tree[node[0]].append([-1, -1])
        return
    elif tree[cur][1][1] == -1 and tree[cur][0][0] < node[1]:
        tree[cur][1][1] = node[0]
        tree[node[0]].append([node[1]])
        tree[node[0]].append([-1, -1])
        return
    # 자식 있는 경우
    if node[1] < tree[cur][0][0]:
        next = tree[cur][1][0]
        make_tree(next, node)
    elif node[1] > tree[cur][0][0]:
        next = tree[cur][1][1]
        make_tree(next, node)

def preorder(pre, cur):
    global tree
    if cur == -1:
        return
    pre.append(cur)
    preorder(pre, tree[cur][1][0])
    preorder(pre, tree[cur][1][1])

def postorder(post, cur):
    global tree
    if cur == -1:
        return
    postorder(post, tree[cur][1][0])
    postorder(post, tree[cur][1][1])
    post.append(cur)

def solution(nodeinfo):
    global tree
    answer = [[], []]
    tree = [[] for _ in range(len(nodeinfo) + 1)]

    nodes = [[i + 1, node[0], node[1]] for i, node in enumerate(nodeinfo)]
    nodes.sort(key=lambda x: x[2], reverse=True) # y값 기준 내림차순 정렬 -> 이제 y값 고려 안해도됨

    tmp = nodes.pop(0)
    root = tmp[0]
    tree[root].append([tmp[1]])
    tree[root].append([-1, -1])
    for node in nodes: # 트리 만들기
        make_tree(root, node)

    preorder(answer[0], root)
    postorder(answer[1], root)
    
    return answer