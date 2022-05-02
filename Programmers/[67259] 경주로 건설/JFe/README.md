# [67259] 경주로 건설 - Python

## 🔍 Algorithm
**BFS, DP**

## 💻 Logic

```Python
# BFS 탐색
    q = deque()
    q.append((x, y, d, cost))
    while q:
        x, y, d, cost = q.popleft()
        for i in range(4):
            next_x, next_y = x + dx[i], y + dy[i]
            # boundary 안이고 벽이 아닌 경우
            if 0 <= next_x < N and 0 <= next_y < N and board[next_y][next_x] == 0:
                # 직선 도로 비용 추가
                next_cost = cost + 100
                # 방향이 달라지면 코너 비용 추가
                if d != i and d != -1:
                    next_cost += 500
                # 해당 cost_list 값보다 작으면 업데이트하고 append
                if next_cost < cost_list[next_y][next_x][i]:
                    cost_list[next_y][next_x][i] = next_cost
                    q.append((next_x, next_y, i, next_cost))
    answer = min(cost_list[N-1][N-1])
```
- **cost_list에 누적 최소 비용을 저장하면서 BFS 탐색**  
  boundary 안이고 벽이 아닌 경우에는 직선 도로 비용을 더해서 `next_cost`에 저장하고,  
  만약 방향이 달라진 경우에는 코너 비용도 추가해서 저장해준다.  
  해당 `cost_list[next_y][next_x][i]` 값보다 `next_cost` 값이 작으면 `cost_list` 값을 업데이트하고 **deque**에 **append**해준다.  
  마지막 위치에 해당하는 `cost_list` 값들 중 최솟값이 정답  

## 📝 Review

**BFS**를 사용해야겠다는 생각은 처음부터 하고 구현을 했고, 하다가 해당 경로의 최소 누적 비용을 저장할 필요성을 느껴서 **DP**도 섞어서 구현을 했다.  
시간은 조금 걸렸지만 구현을 다 하고 확인을 하니 계속 마지막 테스트 케이스만 틀려서 다른 사람들의 힌트를 확인했다..  
해당 좌표에 대해서만 최소 누적 비용을 저장하고 비교하는 것이 아니라 `방향`에 따라 다르게 저장을 할 필요가 있다고 해서 `cost_list` 리스트를 **3차원**으로 변경하니까 통과,,  
카카오 4번 문제부터는 확실히 난이도가 올라가는 것 같다,,  
