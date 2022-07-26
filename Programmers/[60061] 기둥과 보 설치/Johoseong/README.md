# [60061] 기둥과 보 설치
## Algorithm
- 구현
## Logic
### 처음 풀이
- 전체 탐색 없이 삭제랑 설치 나눠서 풀었는데, 이 방식은 삭제 부분이 애매함.. (삭제할 기둥/보 기준으로 2*2 탐색해야 할 듯한데 별론 것 같다)
### 바꾼 풀이
- 입력이 별로 안커서 그냥 삭제/설치할 때마다 전체 탐색을 하면서 가능한 경우인지 체크하도록 변경함
1. 삭제 경우엔, 해당 기둥/보 삭제 후 가능한 상태인지 체크 -> False면 그 기둥/보가 필요한 것이므로 다시 추가
2. 설치 경우엔, 일단 설치 후 가능한 상태인지 체크 -> False면 해당 기둥/보를 설치할 수 없단 것이므로 삭제
```python
for x, y, a, b in build_frame:
    if b == 0: # 삭제
        board.remove([x, y, a])
        flag = check(n, board)
        if flag == False:
            board.append([x, y, a])
    else: # 설치
        board.append([x, y, a])
        flag = check(n, board)
        if flag == False:
            board.remove([x, y, a])
```
3. ```board```를 탐색하면서, ```board```의 기둥/보 각각이 해당 위치에 존재 가능한지 체크함
```python
def check(n, board):
    for x, y, a in board:
        if a == 0: # 기둥
            if y == 0: continue # 바닥 위
            elif [x, y - 1, 0] in board: continue # 다른 기둥 위
            elif [x - 1, y, 1] in board or [x, y, 1] in board: continue # 보 한쪽 위
            else:
                return False
        else: # 보
            if [x, y - 1, 0] in board or [x + 1, y - 1, 0] in board:
                continue
            if [x - 1, y, 1] in board and [x + 1, y, 1] in board:
                continue
            return False
    return True
```

## Review
구현문제였는데 처음에 생각을 잘못해서 좀 오래 헤맸다ㅠㅠ 결국 질문하기 봤는데 설치/삭제마다 계속 전체탐색하는게 편하다고해서 지금 풀이로 바꿈.. 어렵다