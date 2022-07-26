import sys
sys.setrecursionlimit(10**6)

adj_list = [[] for _ in range(300000)]
cost = [[0, 0] for i in range(300000)]

def dfs(sales, node):
    cost[node][0] = 0
    cost[node][1] = sales[node]
    if not adj_list[node]: return

    extra = sys.maxsize
    for child in adj_list[node]:
        dfs(sales, child)
        if cost[child][0] < cost[child][1]:
            cost[node][0] += cost[child][0]
            cost[node][1] += cost[child][0]
            extra = min(extra, cost[child][1] - cost[child][0])
        else:
            cost[node][0] += cost[child][1]
            cost[node][1] += cost[child][1]
            extra = 0
    cost[node][0] += extra
    
def solution(sales, links):
    for link in links:
        adj_list[link[0] - 1].append(link[1] - 1)
    dfs(sales, 0)
    return min(cost[0][0], cost[0][1])