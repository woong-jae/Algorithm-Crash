# [60063] 블록 이동하기
## Algorithm
- BFS
## Logic
- 로봇이 두 칸을 차지한다는 점, 회전이 가능하다는 점을 주의해서 구현해야 햠. 특히 회전은 4가지 경우 발생함에 유의!!
1. (1, 1), (1, 2)를 시작으로 두기 위해서 ```board```의 크기를 변경
2. 현재 상태를 queue와 visited에 넣음
- 일반적인 배열 visited를 사용하기엔 까다로워서 set 사용
```python
queue = deque([((1, 1), (1, 2), 0)])
visited = set([((1, 1), (1, 2))])
```
3. queue를 탐색하면서, 현재 위치에서 이동가능한 경우를 구함
- 단순 이동과 회전을 나눠서 고려. 회전의 경우 총 4가지 경우를 모두 살펴야함.
```python
def move(cur1, cur2, board):
    moving = []
    # 이동
    for d in [(-1, 0), (1, 0), (0, 1), (0, -1)]:
        nxt1 = (cur1[0] + d[0], cur1[1] + d[1])
        nxt2 = (cur2[0] + d[0], cur2[1] + d[1])
        if board[nxt1[0]][nxt1[1]] == 0 and board[nxt2[0]][nxt2[1]] == 0:
            moving.append((nxt1, nxt2))
    # 회전
    if cur1[0] == cur2[0]:
        for d in [-1, 1]:
            if board[cur1[0] + d][cur1[1]] == 0 and board[cur2[0] + d][cur2[1]] == 0:
                moving.append((cur1, (cur1[0] + d, cur1[1])))
                moving.append((cur2, (cur2[0] + d, cur2[1])))
    else:
        for d in [-1, 1]:
            if board[cur1[0]][cur1[1] + d] == 0 and board[cur2[0]][cur2[1] + d] == 0:
                moving.append(((cur1[0], cur1[1] + d), cur1))
                moving.append(((cur2[0], cur2[1] + d), cur2))
                
    return moving
```
4. 구한 이동범위에 따라서, 이동 위치를 방문 안했다면 queue에 삽입 -> 3번 반복
```python
while queue:
    cur1, cur2, count = queue.popleft()
    if cur1 == (N, N) or cur2 == (N, N):
        return count
    for next in move(cur1, cur2, board):
        if next not in visited:
            queue.append((*next, count+1))
            visited.add(next)
```

## Review
queue에 방문 위치 두개를 모두 입력해야한다는 점은 알았는데 계속 실패 떴다.. 결국 다른 코드 참조했다. 이동 범위 구하는게 엄청 빡구현인 문제였는듯..