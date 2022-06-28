# [60063] 블록 이동하기 - Python

## 🔍 Algorithm
**BFS**

## 💻 Logic

```Python
def search(location, board):
    next = []
    location = list(location)
    x1, y1, x2, y2 = location[0][0], location[0][1], location[1][0], location[1][1]
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    # 상하좌우 이동 확인
    for i in range(4):
        next_x1, next_y1, next_x2, next_y2 = x1 + dx[i], y1 + dy[i], x2 + dx[i], y2 + dy[i]
        if board[next_x1][next_y1] == 0 and board[next_x2][next_y2] == 0:
            next.append({(next_x1, next_y1), (next_x2, next_y2)})
    # 가로인 경우 회전 확인
    if x1 == x2:
        for i in [-1, 1]:
            if board[x1 + i][y1] == 0 and board[x2 + i][y2] == 0:
                next.append({(x1, y1), (x1 + i, y1)})
                next.append({(x2, y2), (x2 + i, y2)})
    # 세로인 경우 회전 확인
    elif y1 == y2:
        for i in [-1, 1]:
            if board[x1][y1 + i] == 0 and board[x2][y2 + i] == 0:
                next.append({(x1, y1), (x1, y1 + i)})
                next.append({(x2, y2), (x2, y2 + i)})
    return next
```
- **다음 이동할 위치 확인하는 함수**  
    - 상하좌우 이동할 수 있는지 다음 이동 위치 (next_x1, next_y1), (next_x2, next_y2)가 전부 비어있는지 확인하고 추가  
    - 회전은 가로인 경우, 세로인 경우 나눠서 확인하고 두 위치 다 비어있어서 이동할 수 있으면 추가  


## 📝 Review

마지막 문제까지 어렵다..  
BFS로 조금 응용해서 풀면 되는 문제라고 생각했는데 생각만큼 잘 풀리지가 않고 오래 걸려서 다른 코드 참고했다..  
