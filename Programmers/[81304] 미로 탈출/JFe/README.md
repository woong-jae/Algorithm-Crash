# [81304] 미로 탈출 - Python

## 🔍 Algorithm
**Dijkstra, BitMask**

## 💻 Logic

```Python
graph = defaultdict(list)
# visited : 활성화 된 함정 노드에 따라 방문했던 노드 표시
# visited[node][활성화 된 함정 상태] (활성화 된 함정 상태는 비트마스크로 표시)
visited = [[INF for _ in range(2**len(traps))]for _ in range(n+1)]
traps_dict = {n: i for i, n in enumerate(traps)}    # 함정 노드: traps 리스트 상 index
# 그래프 정보 입력
for a, b, v in roads:
    graph[a].append([b, v, False])  # False : 정방향
    graph[b].append([a, v, True])   # True : 역방향
```
- `visited` : 활성화 된 함정 노드에 따라 방문했던 노드 표시  
  `visited[node][활성화 된 함정 상태]` (활성화 된 함정 상태는 **비트마스크**로 표시)  
- `traps_dict` : (함정노드: traps 리스트 상 index) 형태로 **dictionary** 생성  
- `defaultdict`로 생성한 `graph`에 정보 입력  
  상태가 **False**면 **정방향**, **True**면 **역방향**을 의미  

```Python
# 다익스트라 알고리즘
    h = []
    heapq.heappush(h, (0, start, 0))
    visited[start][0] = 0
    while h:
        cost, node, state = heapq.heappop(h)
        # end 노드 도착
        if node == end:
            answer = min(answer, cost)
            continue
        # 이미 방문한 값보다 크면 방문 X
        if cost > visited[node][state]:
            continue
        for next_node, next_cost, direction in graph[node]:
            # 함정 노드면 비트마스킹
            cur_trap, next_trap = False, False
            if node in traps_dict:
                cur_trap = bool(state & (1 << traps_dict[node]))
            if next_node in traps_dict:
                next_trap = bool(state & (1 << traps_dict[next_node]))
            if direction != (cur_trap ^ next_trap): # cur_trap, next_trap 상태가 같으면 정방향, 다르면 역방향
                continue
            # 다음 노드가 함정 노드인지에 따라 상태 변경
            next_state = state
            if next_node in traps_dict:
                next_state = state ^ (1 << traps_dict[next_node])
            # 다음 cost 계산하고, 다음 상태의 cost가 이미 방문했고 더 작으면 방문 X
            next_cost = cost + next_cost
            if next_cost >= visited[next_node][next_state]:
                continue
            visited[next_node][next_state] = next_cost
            heapq.heappush(h, (next_cost, next_node, next_state))
    return answer
```
- `end` 노드 도착  
  `answer`에 `answer`와 `cost` 중 **최솟값** 저장하고 **heap**에 값이 남아있을 수도 있으니 **continue** 해서 계속 진행  
- 이미 방문한 값보다 크면 방문 X  
- 함정 노드면 **비트마스킹**  
  현재 노드와 다음 노드에 대해서 함정 노드인지 확인하고, 함정 노드면 비트마스킹 한 값과 현재 상태(현재 활성화 함정 상태)를 `&` 비트 연산하여 저장  
  비트 연산한 값 `cur_trap`, `next_trap`의 값이 같으면 **정방향**, 다르면 **역방향**  
- 다음 노드가 함정 노드인지에 따라 상태 변경  
  다음 노드가 함정 노드면 현재 상태와 비트 연산하여 다음 상태 변경  
- 다음 cost 계산하고, 다음 상태의 cost가 이미 방문했고 더 작으면 방문 X  


## 📝 Review

다익스트라로 풀어야하고, 방향을 다르게 해서 구현해야 한다는 점까지는 이해했지만 쉽게 구현할 수 없었다.  
시간을 많이 투자했는데도 해결이 안돼서 다른 풀이를 참고했고, 비트마스크를 사용해서 푸는 방법을 찾을 수 있었다.  
풀이를 보고도 이해해서 다시 코드를 짜는데 시간이 많이 걸렸고, 비트마스크 방식에 대해 더 자세히 알아봐야겠다..  
카카오 문제들이 계속해서 4번부터 어려웠지만 이번 문제는 진짜 어려웠고 이해하는데 너무 오래걸렸다..
