# [86052] 빛의 경로 사이클
## Algorithm
- 구현
## Logic
- 핵심: 사이클을 체크할 때, 현재 노드에서 이동해야하는 방향으로 이미 간 적이 있으면 해당 사이클은 중복임 -> 즉, ```행*열*4방향```의 3차원 방향 체크 배열을 만들어서 사이클을 체크하면 됨
- 모든 노드에서 사이클을 체크해야됨!
1. 먼저, 모든 노드에서 4방향 사이클 체크를 함
```python
for r in range(R):
    for c in range(C):
        for d in range(4): # 시계방향
            flag, cnt = cycle(grid, matrix, r, c, d)
            if flag: answer.append(cnt)
```
2. 현재 위치와 방향을 기억해두면서 사이클을 이어나감
3. 방향 체크 배열을 이용해서, 현재 위치와 방향을 이미 간 적이 있으면? -> 중복 경로로 간주해서 answer에서 제외시킴
4. 3번 경우에 해당하지 않으면, 계속해서 경로를 count하고 answer에 추가
```python
def cycle(grid, matrix, r, c, d):
    global R, C
    cnt = 0
    cur_r, cur_c, cur_d = r, c, d
    while True: # 경로 따라 가봄
        if matrix[cur_r][cur_c][cur_d]: # 이미 해당 경로 간 적 있으면?
            break
        matrix[cur_r][cur_c][cur_d] = True
        cnt += 1

        next_r = cur_r + D[cur_d][0] # 다음 노드 정해줌
        next_c = cur_c + D[cur_d][1]
        if next_r < 0: next_r = R - 1
        elif next_r >= R: next_r = 0
        if next_c < 0: next_c = C - 1
        elif next_c >= C: next_c = 0

        if grid[next_r][next_c] == 'S': # 다음 노드 뭐냐에 따라 방향 정해줌
            next_d = cur_d
        elif grid[next_r][next_c] == 'R':
            next_d = cur_d + 1 if cur_d < 3 else 0
        else:
            next_d = cur_d - 1 if cur_d > 0 else 3
        cur_r, cur_c, cur_d = next_r, next_c, next_d
        
    if cur_r == r and cur_c == c and cnt != 0:
        return True, cnt
    else: return False, -1
```

## Review
문제 이해에서 시간이 좀 걸렸다. 테스트케이슨 다 통과했는데 정작 제출하니까 1개도 통과못해서 당황했음.. 알고보니 난 처음에 (0,0) 시작이 고정인줄 알았는데 그게 아니라 모든 노드에서 시작할 수 있는거였다 ㅎㅎ..ㅋ 귀찮은 문제였다