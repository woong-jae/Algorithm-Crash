from collections import defaultdict

def solution(info, edges):
    # DFS 탐색
    def dfs(cur, sheep, wolf, order):
        nonlocal answer
        answer = max(answer, sheep)
        order.update(graph[cur])    # 다음 노드 추가
        for next in order:
            # 양인 경우
            if info[next] == 0:
                dfs(next, sheep + 1, wolf, order - {next})
            # 늑대이면서 양의 수가 늑대 수보다 여전히 많은 경우
            elif sheep > wolf + 1:
                dfs(next, sheep, wolf + 1, order - {next})
    answer = 0
    graph = defaultdict(set)
    for a, b in edges:
        graph[a].add(b)
    dfs(0, 1, 0, graph[0])
    return answer