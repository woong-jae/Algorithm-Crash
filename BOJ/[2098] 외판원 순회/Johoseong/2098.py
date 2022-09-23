import sys
input = sys.stdin.readline

def solve(i, route):
    if route == (1 << N) - 1: # 모든 노드 다 거쳤음
        if weight[i][0] != 0: # 0으로 돌아오는 길 있음
            return weight[i][0]
        else: # 0으로 돌아오는 길 없음
            return int(1e9)

    if dp[i][route] != 0: # 이미 구했던 값이면 바로 반환
        return dp[i][route]
    
    min_dist = int(1e9)
    for j in range(1, N): # 현재 i에서 갈 수 있는 곳 구하기
        if weight[i][j] == 0: # i->j로 갈 수 없음
            continue
        if route & (1 << j) != 0: # 이미 방문한 곳임
            continue

        min_dist = min(min_dist, solve(j, route | (1 << j)) + weight[i][j])
    dp[i][route] = min_dist
    return min_dist

N = int(input())
dp = [[0] * (1 << N) for _ in range(N)]
weight = [list(map(int, input().split())) for _ in range(N)]

print(solve(0, 1))