# [17679] 프렌즈4블록
## Algorithm
- 구현(시뮬레이션)
## Logic
- 문제 흐름 그대로 차근차근 구현하면 됨
1. 판을 탐색하면서 2*2가 같은 블록들을 찾음. 찾은 블록은 지워야하므로, ```remove```에 위치 기억.
```python
for i in range(m - 1): # 2*2 같은 블록 찾음 (나중에 지워야해서 위치 기억해둠)
    for j in range(n - 1):
        if field[i][j] == 0:
            continue

        if field[i][j] == field[i][j + 1] == field[i + 1][j] == field[i + 1][j + 1]:
            flag = 1
            remove.append([i, j])
            remove.append([i, j + 1])
            remove.append([i + 1, j])
            remove.append([i + 1, j + 1])
```
2. 만약 1번에서 지울 블록 못찾으면 게임 종료.
3. ```remove```에 담긴 위치들은 0으로 갱신. (블록 지움)
```python
while remove: # 지움
    r, c = remove.pop()
    field[r][c] = 0
```
4. 갱신된 판 재탐색 하면서, 지워진 블록 위에 있는 블록들 아래로 내림.
```python
for i in range(m - 1): # 위에 있는거 내림
    for j in range(n):
        if field[i + 1][j] == 0 and field[i][j] != 0:
            field[i + 1][j] = field[i][j]
            field[i][j] = 0

            for k in range(i - 1, 0, -1):
                field[k + 1][j] = field[k][j]
                field[k][j] = 0
```
- 시간복잡도 O(N^3)? (n, m 범위가 30이하로 작음)

## Review
귀찮은 문제였다.. 그냥 무작정 구현하니까 풀렸다.
