# [62050] 지형 이동
## Algorithm
- BFS, 우선순위 큐
## Logic
### 처음 풀이
1. BFS로 이동 가능 지형 나눔
2. 이번엔 BFS로 다음 칸이 다른 지형인 경우, ```높이 차이```와 ```현재 지형번호와 다음 지형번호의 bitmask```을 heap에 넣음
3. pop 하면서 전체 지형을 잇는 최소 높이 차이 구함
-> 20번부터 시간초과..
### 바꾼 풀이
- 처음 BFS를 돌 때, 그냥 큐가 아니라 우선순위 큐를 쓰면
1. 현재 칸에서 다음 칸이 이동가능한 경우를 우선 탐색하기 위해 최소값(0) 넣고
2. 이동 불가한 경우엔 높이차를 넣어서, 높이차가 가장 작은 경우를 1번 이후의 우선순위로 둘 수 있음
- so, 높이차를 기준으로 ```최소 heap```을 구성하며 BFS 탐색을 하면 자연스럽게 다른 지형으로 갈 때 높이차가 가장 작은 칸으로 가게 됨
```python
while heap:
    cur_h, cur_r, cur_c = heapq.heappop(heap)

    if visited[cur_r][cur_c] == 1: # 이미 사다리로 내려가서 탐색한 지역이면 패스
        continue
    visited[cur_r][cur_c] = 1
    answer += cur_h

    for D in [[-1, 0], [1, 0], [0, -1], [0, 1]]:
        next_r = cur_r + D[0]
        next_c = cur_c + D[1]
        if not (0 <= next_r < N and 0 <= next_c < N) or visited[next_r][next_c] == 1:
            continue
        sub = abs(land[next_r][next_c] - land[cur_r][cur_c])
        if sub > height: # 못가는 블록
            heapq.heappush(heap, [sub, next_r, next_c])
        else:
            heapq.heappush(heap, [0, next_r, next_c])
```

## Review
처음 풀이 고집하다가 점점 미궁으로 빠져서 질문하기 봄.. 보니까 첨 풀이에서 2번 부분을 union-find 써서 푼 사람들도 많던데, 이거보다 현재 풀이 방식이 정말 너무 좋은 아이디어같아서 이렇게 풀었다. 