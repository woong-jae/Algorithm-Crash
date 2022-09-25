# [2098] 외판원 순회 - Python

## 🔍 Algorithm
**DP**, **Bit Mask**

## 💻 Logic

```Python
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
```
- **점화식** : `현재 위치에서 가능한 최소 비용 = 다음 위치에서 가능한 최소 비용 + 현재 위치 비용`  
    `graph[cur][visit]` => `cur` : 현재 도시 / `visit` : 방문한 도시들 비트 마스킹  
- 모든 도시를 방문할 때까지 탐색하면서 최솟값 업데이트 (경로가 없는 경우, 이미 방문한 경우는 예외 처리)  
- 모든 도시를 다 방문하고, `cur`에서 다시 출발점(**0**)으로 가는 경우 따로 처리  


## 📝 Review

예전에 한창 DP 공부할 때 봤었던 문제,,  
그 때는 풀이를 보고도 이해를 계속 못했었는데 그래도 이제는 비트 마스크 사용 방법을 조금 알아서 그런가 이해는 된다. 그래도 어렵군  