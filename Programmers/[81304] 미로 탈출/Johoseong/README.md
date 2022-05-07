# [81304] 미로 탈출
## Algorithm
- 다익스트라, 비트마스크
## Logic
### 처음 풀이
- 함정을 밟을 때마다 그래프 새로 그림..
- 거기서 다익스트라 실행
- 테스트 케이스 5개 시간초과 뜸
### 현재 풀이
- 다익스트라로 푸는 건 동일. 그 대신 함정을 밟는 경우를 4가지 (현재/다음 노드 함정 상태)로 나눠서 비트마스킹 함
> 현재랑 다음 노드 모두 함정 : 0<br>
> 현재 노드만 함정 : 1<br>
> 다음 노드만 함정 : 1<br>
> 현재랑 다음 노드 모두 함정 아님 : 0<br>
1. heap을 이용한 다익스트라를 사용 (heap은 [시간, 노드 번호, 함정 상태] 형태)
2. 현재 노드와 연결돼있는 노드들을 탐색하면서 함정 상태 비트마스킹
```python
if cur_node in traps:
    if next_node in traps: # 현재 노드랑 다음 노드 다 함정임 -> 0
        cur_flag  = ((cur_state&(1<<trap_index[cur_node]))//(1<<trap_index[cur_node]) + (cur_state&(1<<trap_index[next_node]))//(1<<trap_index[next_node]))%2
    else: # 현재 노드만 함정임 -> 1
        cur_flag = (cur_state&(1<<trap_index[cur_node]))//(1<<trap_index[cur_node])
else:
    if next_node in traps: # 다음 노드만 함정임 -> 1
        cur_flag = (cur_state&(1<<trap_index[next_node]))//(1<<trap_index[next_node])
    else: # 현재 노드랑 다음 노드 다 함정 아님 -> 0
        cur_flag = 0
```
3. 두 노드의 함정 상태 확인해본 결과, 현재 함정 상태와 같다면? (즉, 다음 노드로 갈 수 있는 상태라는 뜻)
- dp에 저장해둔 값보다 현재 시간이 더 작으면 heap에 넣어서 해당 노드 탐색
```python
if cur_flag == state:
    if dp[cur_state][next_node] > cur_time + next_time:
        dp[cur_state][next_node] = cur_time + next_time
        if next_node in traps:
            heapq.heappush(heap,(dp[cur_state][next_node], next_node, cur_state^(1<<trap_index[next_node])))
        else:
            heapq.heappush(heap,(dp[cur_state][next_node],next_node,cur_state))
```

- 시간복잡도 O(NlogN)

## Review
솔직히 처음 풀이는 무조건 시간 초과 날 거라고 생각했다.<br>
다익스트라는 확실한데, 함정에 따라 그래프를 어떻게 갱신해야할 지 고민하다가 도저히 안돼서 질문하기 봤다..<br>
어떻게 비트마스크 사용할 생각을 하지..? 어려운 문제들에서 비트마스크 활용하는 경우가 많은 것 같다.. 
