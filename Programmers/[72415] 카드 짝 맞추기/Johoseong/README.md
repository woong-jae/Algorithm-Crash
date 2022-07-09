# [72415] 카드 짝 맞추기
## Algorithm
- 구현, BFS

## Logic
- 최소조작수를 구하기 위해서 (1) 카드를 지울 순서뿐만 아니라 (2) 카드쌍 2개의 거치는 순서까지 고려를 해야한다. 
- (1) 카드 지울 순서는 permutation으로 모든 경우의 수를 구할 수 있다.
- (2) 카드쌍 2개의 거치는 순서를 정하기 위해선, ```현재위치->카드쌍1 + 카드쌍1->카드쌍2```의 값이 더 작은 카드를 먼저 거치면 된다.
1. 카드쌍의 위치를 구해서 ```cards```에 저장
```python
cards = dict()
for i in range(4): # 카드번호 별로 위치 저장
    for j in range(4):
        n = board[i][j]
        if n != 0:
            cards.setdefault(n, [])
            cards[n].append([i, j])
```
2. 카드를 지울 순서를 permutation으로 구함
```python
permuts = list(permutations(num, len(num)))
```
3. 각 지우는 순서에서 최소조작수를 구함 -> 그 중 제일 작은 값이 answer
- 현재 지울 카드쌍에서도 2개의 카드 중 무엇을 먼저 거칠지 정해야하므로, ```현재위치->카드쌍1```와 ```현재위치->카드쌍2``` 둘 다 구함
- 카드쌍 중 하나를 선택했으니 남은 카드쌍을 거쳐야함. 그러므로 위의 두 경우에 대해서 ```카드쌍1->카드쌍2```와 ```카드쌍2->카드쌍1```의 최소조작수 구하고 위의 값과 더함 -> 둘 중 더 작은걸 기준으로 해서 계속 탐색하면 됨!
```python
for per in permuts:
    b = copy.deepcopy(board)
    tmp = 0
    start = [r, c]
    for p in list(per): # 카드쌍 안에서도 뭐 먼저 거치냐에 따라 값 달라짐 -> 두 경우 모두 고려해서 작은 값으로 
        end1, end2 = cards[p][0], cards[p][1]
        case1 = bfs(b, start, end1)
        case2 = bfs(b, start, end2)
        case1 += bfs(b, end1, end2)
        case2 += bfs(b, end2, end1)
        if case1 < case2:
            tmp += case1
            start = end2
        else:
            tmp += case2
            start = end1
        tmp += 2
        b[end1[0]][end1[1]] = 0
        b[end2[0]][end2[1]] = 0
    answer = min(answer, tmp)
```
4. 최소조작수를 구하기 위해서 BFS를 사용해야 함
- 일반적인 4방향 한칸 이동 외에도 ```ctrl```이동이 추가적으로 있음에 유의.
- so, 4방향 탐색에 추가적으로 컨트롤 누른 이동을 고려해줌. 즉, 최대 8가지 이동이 있을 수 있음.
```python
def bfs(board, start, end):
    visited = [[10 ** 5] * 4 for _ in range(4)]
    que = deque()
    que.append([start[0], start[1], 0]) # 시작점, 횟수
    
    while que:
        cur_r, cur_c, cnt = que.popleft()
        if visited[cur_r][cur_c] < cnt: continue
        if cur_r == end[0] and cur_c == end[1]: 
            return cnt
        visited[cur_r][cur_c] = cnt

        for d in [[-1, 0], [0, 1], [1, 0], [0, -1]]:
            next_r = cur_r + d[0]
            next_c = cur_c + d[1]
            next_cnt = cnt + 1
            if next_r < 0 or next_r > 3 or next_c < 0 or next_c > 3:
                continue
            que.append([next_r, next_c, next_cnt])
            if board[next_r][next_c] == 0:
                flag, ctrl = find_move(board, [cur_r, cur_c], d)
                if flag == False: continue
                que.append([cur_r + ctrl[0], cur_c + ctrl[1], next_cnt])
```
5. ```ctrl```이동은, 4방향 한칸 이동과 겹치지 않고 범위를 넘어가지 않으면 추가하도록 함.
```python
def find_move(board, cur, d):
    sub = 2
    next_r = cur[0] + 2 * d[0]
    next_c = cur[1] + 2 * d[1]
    while True:
        if next_r < 0 or next_r > 3 or next_c < 0 or next_c > 3:
            if sub == 2:
                return False, [0, 0]
            else:
                return True, [d[0] * (sub - 1), d[1] * (sub - 1)]
        if board[next_r][next_c] != 0:
            return True, [d[0] * sub, d[1] * sub]
        next_r += d[0]
        next_c += d[1]
        sub += 1
```

## Review
아이디어는 잘 잡았는데 구현이 힘들어서 너무 오래 걸렸다. 특히 컨트롤 이동 부분이 계속 이상하게 돼서 코드가 많이 더러워졌다; BFS가 구현이랑 섞이면 난이도가 확 올라가는 느낌.. 레벨3 아닌 것 같다 진짜