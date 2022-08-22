# [1726] 로봇 - Python

## 🔍 Algorithm
**BFS**

## 💻 Logic

```Python
def bfs(x, y, d):
    visited[y][x][d] = True
    q = deque([(x, y, d, 0)])
    while q:
        x, y, d, v = q.popleft()
        # 마지막 위치, 방향과 맞으면 return
        if x == end_x - 1 and y == end_y - 1 and d == end_d - 1: return v
        # 1, 2 또는 3만큼 이동
        for i in range(1, 4):
            next_x, next_y = x + dx[d] * i, y + dy[d] * i
            # boundary 체크
            if 0 > next_x or next_x >= N or 0 > next_y or next_y >= M or field[next_y][next_x]: break
            if not visited[next_y][next_x][d]:
                visited[next_y][next_x][d] = True
                q.append((next_x, next_y, d, v + 1))
        # 방향 변경
        for next_d in rotate[d]:
            if not visited[y][x][next_d]:
                visited[y][x][next_d] = True
                q.append((x, y, next_d, v + 1))
    return -1
```
- **BFS 탐색**  
    - 마지막 위치, 방향과 맞으면 return  
    - 1, 2 또는 3만큼 반복문을 이용해 이동  
    - boundary 체크해서 벗어나면 break / 방문하지 않았으면 append  
    - 앞서 선언해둔 rotate를 이용해 방향 변경  


## 📝 Review

계속 인덱스 에러가 나서 멘탈 나갔는데 입력을 잘못 받았었다... 백준 싫어할 것,,,