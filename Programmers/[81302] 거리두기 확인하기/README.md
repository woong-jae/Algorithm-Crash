# [81302] 거리두기 확인하기 - Python

## 🔍 Algorithm
**DFS**

## 💻 Logic

```Python
# 강의실마다 확인
    for place in places:
        check = True
        for i in range(5):
            for j in range(5):
                # 사람이 있는 경우에만 탐색하고, 반환값 체크
                if place[i][j] == 'P' and not search(place, j, i):
                    check = False
        # check에 따라 값 입력
        if check: answer.append(1)
        else: answer.append(0)
    return answer
```
- **강의실마다 거리두기 확인**  
    사람이 있는 경우에만 `search()` 함수로 탐색하고, 반환값이 **False**면 `check`도 **False**로 저장  
    전부 탐색 끝난 후에 `check` 값에 따라 `answer`에 거리두기 여부 **append**  

```Python
def search(place, x, y):
    stack = [(x, y, 0)]
    visited = [[False for _ in range(5)]for _ in range(5)]
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    visited[y][x] = True
    # DFS 탐색
    while stack:
        x, y, d = stack.pop()
        if d == 2: continue     # 깊이 2까지만
        for i in range(4):
            next_x, next_y = x + dx[i], y + dy[i]
            if next_x < 0 or next_x >= 5 or next_y < 0 or next_y >= 5 or visited[next_y][next_x]: continue
            if place[next_y][next_x] == "P": return False
            if place[next_y][next_x] == "O": stack.append((next_x, next_y, d+1))
    return True
```
- **DFS 탐색 함수**  
    **DFS**로 탐색하면서 `P`를 만나면 **False** 반환 / `O`를 만나면 `stack`에 **append**해서 탐색 계속 ***(깊이 2까지만)***  
    시작 좌표 `x, y` 의 `visited` 값을 **True**로 지정하고 예외 처리  

## 📝 Review

DFS로 깊이 2까지만 탐색하면 되는 문제.  
코드가 예쁘지는 않지만 코테 대비해서 최대한 빠르게 풀려고 했는데 걸린 시간은 만족!
