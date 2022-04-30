from collections import defaultdict
def solution(n, path, order):
    stack = []
    tree = defaultdict(list)
    orders = defaultdict(int)
    visited = [False for _ in range(n)]
    after = [-1 for _ in range(n)]
    # tree 정보 입력
    for a, b in path:
        tree[a].append(b)
        tree[b].append(a)
    # 방문 순서 정보 입력
    for a, b in order:
        # 0을 나중에 방문해야 하는 경우는 존재 X
        if b == 0: return False
        orders[b] = a
    # DFS 탐색
    stack.extend(tree[0])
    while stack:
        num = stack.pop()
        if visited[num]: continue
        # 먼저 방문해야 할 방이 있지만, 방문하지 않은 경우
        if orders[num] and not visited[orders[num]]:
            after[orders[num]] = num
            continue
        # 방문하지 않은 경우
        visited[num] = True
        stack.extend(tree[num])
        if after[num] != -1:
            stack.append(after[num])    # after 값이 있으면 append
    # 하나라도 방문하지 않았으면 False
    if False in visited:
        return False
    return True