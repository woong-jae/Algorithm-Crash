# [2098] 외판원 순회
## Algorithm
- DP, Bitmask, DFS?
## Logic
- DP를 이용해서 `DP[현재 위치][지금까지 들린 노드들]`의 최솟값을 저장해야함. 
- 여기서 `지금까지 들린 노드들`을 나타내기 위해서 Bitmask를 사용해야지 적은 메모리+빠른 시간으로 값을 구할 수 있음.
- 어차피 순회하는 경로를 구하는 것이라서 0->0만 구하면 되고, 이 경우에 바로 값을 return하려고 재귀로 구현.
재귀는
1. 모든 노드를 다 거친 경우면, 현재 i에서 0으로 돌아오는 길이 있으면 그 weight 반환, 없으면 이 루트는 못 가는 길 처리
2. (기본 종료 조건) 이미 구한 값이면 바로 구한 값 반환
3. 노드 1~N을 탐색하면서 갈 수 있는 곳 구하기
과정으로 이뤄짐.
```python
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
```

## Review
알고리즘 수업 떄 과제로도 했는데 또 모르겠음 ㅋㅋ 아 DP는 진짜 어렵다.. 아니 그리고 DP 기본값으로 처음에 int(1e9)로 두고 푸니까 시간초과됨.. 혹시나 해서 0으로 하니까 통과.. 힘들었다