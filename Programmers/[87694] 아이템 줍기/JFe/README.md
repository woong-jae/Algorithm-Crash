# [87694] 아이템 줍기 - Python

## 🔍 Algorithm
**BFS**

## 💻 Logic

```Python
    field = [[False for _ in range(101)]for _ in range(101)]    # 크기 2배로 늘리기 (ㄷ자인 경우 처리하기 위함)
    # 직사각형 field에 표시
    for x1, y1, x2, y2 in rectangle:
        for i in range(y1*2, y2*2+1):
            for j in range(x1*2, x2*2+1):
                field[i][j] = True
    # 직사각형 내부 지우기
    for x1, y1, x2, y2 in rectangle:
        for i in range(y1*2+1, y2*2):
            for j in range(x1*2+1, x2*2):
                field[i][j] = False
```

- **크기 2배로 늘려서 field에 직사각형 저장**  
    테두리가 `ㄷ`자인 경우에는 최단 거리를 구하기 위해 `ㅁ`과 같이 계산을 하므로, 크기를 2배로 늘려서 예외 처리해주어야 함  
- **직사각형 field에 표시**
    우선 직사각형 전체를 field에 True로 표시하고,  
    그 다음 각 직사각형의 내부를 False로 바꾸는 방법으로 테두리만 표시  
    

```Python
def bfs(field, characterX, characterY, itemX, itemY):
    visited = [[False for _ in range(101)]for _ in range(101)]
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    q = deque()
    q.append([characterX, characterY, 0])
    while q:
        x, y, v = q.popleft()
        visited[y][x] = True
        # itemX, itemY에 도착하면 종료
        if x == itemX and y == itemY: return v
        for i in range(4):
            nx, ny, nv = x + dx[i], y + dy[i], v + 1
            # 범위 안, 테두리 위, 방문 X면 BFS 탐색
            if 0 <= nx <= 100 and 0 <= ny <= 100 and field[ny][nx] and not visited[ny][nx]:
                q.append([nx, ny, nv])
```

- **테두리를 따라 BFS 탐색**  
    itemX, itemY에 도착하면 종료  
    범위 안, 테두리 위, 방문 X면 계속해서 탐색  


## 📝 Review

테두리로 BFS 탐색 구현까지는 했지만 크기를 2배로 늘려서 해결한다는 생각은 하지 못했다..