def dfs(cur, info, path, sheep, wolf):
    global graph
    if info[cur] == 0: sheep += 1
    else: wolf += 1
    if sheep <= wolf:
        return 0
    
    MAX = sheep
    for p in path: # 현재 탐색한 path에서 다음으로 갈 노드 선택
        for n in graph[p]:
            if n not in path:
                path.append(n)
                MAX = max(MAX, dfs(n, info, path, sheep, wolf))
                path.pop() # 백트랙킹
    return MAX

def solution(info, edges):
    global graph
    N = len(info)
    graph = { i: [] for i in range(N) }
    for x, y in edges:
        graph[x].append(y)
    
    answer = dfs(0, info, [0], 0, 0)
    return answer

print(solution([0,0,1,1,1,0,1,0,1,0,1,1], [[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]))
# print(solution([0,1,0,1,1,0,1,0,0,1,0], [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6],[3,7],[4,8],[6,9],[9,10]]))