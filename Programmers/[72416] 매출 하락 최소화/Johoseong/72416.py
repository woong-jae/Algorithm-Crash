def dfs(graph, sales, DP, node):
    if not graph[node]: # 리프노드(=그냥 팀원)이면 선택 시 비용 자기자신임. 바로 return
        DP[node][0] = sales[node]
        return

    sum_child = 0
    for child in graph[node]: # 한 팀의 최소 비용 구함 (그 밑에 팀 있다면 그거까지)
        dfs(graph, sales, DP, child)
        sum_child += min(DP[child][0], DP[child][1])
    DP[node][0] = sum_child + sales[node]

    attend = False
    for child in graph[node]:
        if DP[child][1] > DP[child][0]: # 이미 팀원이 다른팀의 팀장으로서 참석하는 경우!!
            attend = True
    if attend: 
        DP[node][1] = sum_child
    else: 
        DP[node][1] = sum_child + min((DP[k][0] - DP[k][1] for k in graph[node]), default=0)

def solution(sales, links):
    N = len(sales)
    graph = { (i + 1):[] for i in range(N)} # 루트는 항상 1
    sales = [0] + sales
    for a, b in links:
        graph[a].append(b)
    DP = [0] + [[0, 0] for _ in range(N)]

    dfs(graph, sales, DP, 1)

    return min(DP[1][0], DP[1][1])

print(solution([14, 17, 15, 18, 19, 14, 13, 16, 28, 17], [[10, 8], [1, 9], [9, 7], [5, 4], [1, 5], [5, 10], [10, 6], [1, 3], [10, 2]]))