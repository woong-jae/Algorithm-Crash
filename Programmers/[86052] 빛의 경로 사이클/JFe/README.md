# [86052] 빛의 경로 사이클 - Python

## 🔍 Algorithm
**구현**

## 💻 Logic

```Python
def check(grid, visited, x, y, d):
    r, c, cnt = len(grid), len(grid[0]), 0
    q = deque()
    q.append([x, y, d])
    # 빛 이동
    while q:
        cur_x, cur_y, cur_d = q.popleft()
        # 이미 방문했으면 break
        if visited[cur_y][cur_x][cur_d]: break
        visited[cur_y][cur_x][cur_d] = True
        # 다음 위치 계산 (격자 끝을 넘어가면 반대쪽 끝으로 가도록)
        next_x, next_y = cur_x + dx[cur_d], cur_y + dy[cur_d]
        if next_x == c: next_x = 0
        elif next_x == -1: next_x = c - 1
        if next_y == r: next_y = 0
        elif next_y == -1: next_y = r - 1
        # 다음 방향 계산
        if grid[next_y][next_x] == 'S': next_d = cur_d
        elif grid[next_y][next_x] == 'L': 
            if cur_d == 0: next_d = 3
            else: next_d = cur_d - 1
        elif grid[next_y][next_x] == 'R': 
            if cur_d == 3: next_d = 0
            else: next_d = cur_d + 1
        q.append([next_x, next_y, next_d])
        cnt += 1
    return cnt
```
- **방문 여부 visited 표시**  
    `visited[y][x][d]` 형태로 x, y 좌표와 상하좌우 방향을 저장하는 d까지 3차원 리스트로 만들어서 방문 여부 표시  
    이미 방문했으면 break  
- **다음 위치, 다음 방향 계산**  
    격자 끝을 넘어가면 반대쪽 끝으로 가도록 다음 위치 계산  
    적힌 문자 보고 다음 방향 계산  


## 📝 Review

문제 이해가 안돼서 헤맸다.. 시작 위치도 바뀔 수 있다는 점도 몰랐고.. 그래도 문제 이해하고 나서는 어렵지 않게 구현할 수 있었는 듯..!
