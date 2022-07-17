# [92345] 사라지는 발판
## Algorithm
- DFS
## Logic
- 플레이어 A, B의 순서마다 DFS로 모든 경로를 탐색하는데, (1) 지는 경우와 (2) 최선의 경기를 한다는 점을 잘 처리해줘야한다
- (1) 현재 턴의 플레이어가 지는 경우는, 지금 위치에서 이동을 못하는 경우 (=주위 네칸의 발판이 다 사라짐)와 딛고 있는 발판이 사라진 경우 2개가 있다
- (2) 최선의 경기를 한다는 건, 현재 턴의 플레이어가 이겼으면 이긴 경우 중 최소 이동 횟수 or 졌으면 진 경우 중 최대 이동 횟수를 의미한다
- 그러므로, 누가 이기던지 일단 현재 턴의 플레이어가 이겼냐 졌냐에 따라 최소/최대 횟수를 반환해나가면 된다
1. DFS로 플레이어 A, B를 번갈아가면서 탐색을 함 (첫 시작은 A)
- 파라미터 ```turn```이 0 -> 현재 A 턴, 1 -> 현재 B 턴
```python
def dfs(board, a, b, turn, cnt):
    global R, C
    if turn == 0: # 현재 A 차례
        cur_r, cur_c = a
    else: # 현재 B 차례
        cur_r, cur_c = b
```
2. 현재 턴의 발판이 사라진 상태면? -> 현재 턴의 플레이어가 졌다는 뜻으로 False + 현재 이동 횟수 return
```python
    if board[cur_r][cur_c] == 0:
        return [False, cnt]
```
3. 현재 위치에서 상하좌우를 탐색함
- 다음 위치가 범위를 벗어남 or 사라진 발판이면 해당 위치로 이동 불가
- ```can_move```로 상하좌우 어떤 칸으로도 이동 불가능한 경우를 체크함 -> 이 경우, 현재 턴의 플레이어가 졌음
- 다음 칸이 이동 가능한 발판이면, 현재 딛고 있는 발판을 없앤 후 다음 턴으로 넘겨줌 -> 이 때, 상대 플레이어의 승패여부 ```win```과 그에따른 상대방의 최선의 이동횟수 ```new_cnt```를 반환받음!!
```python
    can_move = False # 현재 위치에서 내가 이동 가능한지 판단
    winner = []
    loser = []
    for d in [[-1, 0], [1, 0], [0, -1], [0, 1]]:
        next_r = cur_r + d[0]
        next_c = cur_c + d[1]
        if not (0 <= next_r < R and 0 <= next_c < C) or (board[next_r][next_c] == 0):
            continue

        can_move = True
        next_board = copy.deepcopy(board)
        next_board[cur_r][cur_c] = 0 # 현재 딛고있는 발판 없앰
        if turn == 0: # 현재 A 차례면 B 차례로 넘어감
            win, new_cnt = dfs(next_board, [next_r, next_c], b, 1, cnt + 1)
        else: # 현재 B 차례면 A 차례로 넘어감
            win, new_cnt = dfs(next_board, a, [next_r, next_c], 0, cnt + 1)
```
4. 상대의 승패여부에 따라 최선의 이동 횟수를 기록해감
```python
        if win == False: # 상대가 졌음 == 내가 이김
            winner.append(new_cnt)
        else: # 상대가 이김
            loser.append(new_cnt)
```
5. 상하좌우를 탐색하면서 체크한 ```can_move```와 기록한 이동 횟수에 따라 최선의 이동 횟수를 구함
- ```can_move```가 True -> 내가 이동 가능한 경우 / False -> 내가 이동 불가능한 경우. 즉, 내가 졌다는 뜻의 False와 이동 횟수 반환
- 이동 가능한 경우면, 기록한 이동 횟수에 따라, 내가 이긴 경우가 있으면 그 중 최소 횟수 / 내가 지는 게임이면 그 중 최대 횟수 반환
```python
    if can_move: # 내가 이동을 할 수 있는 상태면?
        if winner: # 내가 이김 -> 이긴 경우 중 이동경로 최소가 최선
            return [True, min(winner)]
        else: # 내가 짐 -> 진 경우 중 이동경로 최대가 최선
            return [False, max(loser)]
    else: # 내가 이동 불가능한 상태면? -> 내가 졌음
        return [False, cnt]
```

## Review
DFS 활용 너무 어렵다.. 처음엔 누가 이겼는지를 알아야한다고 생각해서 이긴 플레이어가 누군지+이동횟수를 같이 기록했는데 풀이를 보니까 사실 승패 여부는 몰라도 되는 정보였다. 문제를 이해하는 거부터 어려운 문제였음.. 백준 16571이랑 비슷하다고 하니까 풀어보기.