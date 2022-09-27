import sys

def tsp(cur, visit):
    min_cost = sys.maxsize
    if dp[cur][visit] != 0: return dp[cur][visit]
    # 모든 도시를 다 방문하고, cur에서 다시 출발점(0)으로 가는 경우
    if visit == (1 << N) - 1:
        if graph[cur][0]: return graph[cur][0]
        else: return sys.maxsize
    # 모든 도시를 방문할 때까지 탐색하면서 최솟값 업데이트
    for i in range(1, N):
        # 경로가 없는 경우
        if not graph[cur][i]: continue
        # 이미 방문한 경우
        if visit & (1 << i): continue
        # 현재 위치에서 가능한 최소 비용 = 다음 위치에서 가능한 최소 비용 + 현재 위치 비용
        min_cost = min(min_cost, tsp(i, visit | (1 << i)) + graph[cur][i])
        dp[cur][visit] = min_cost
    return min_cost

N = int(sys.stdin.readline())
graph = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
dp = [[0 for _ in range(1 << N)] for _ in range(N)]
print(tsp(0, 1))