import sys
sys.setrecursionlimit(10 ** 6)
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

def solution(weight, edges):
    global answer
    answer = 0
    if sum(weight) != 0:
        return -1

    graph = dict()
    for a, b in edges:
        graph.setdefault(a, []) # 연결된 정점 수, 정점 정보
        graph.setdefault(b, [])
        graph[a].append(b)
        graph[b].append(a)
    dfs(-1, 0, graph, weight)
    return answer