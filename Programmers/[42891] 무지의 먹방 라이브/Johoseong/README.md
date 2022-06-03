# [42891] 무지의 먹방 라이브
## Algorithm
- 우선순위 큐, 그리디
## Logic
- 음식 양이 작은 것 먼저 먹는게 핵심. 한바퀴에 모든 음식을 1씩 먹으니까, 음식의 양이 바퀴의 수(즉, 시간)임. 작은 것 먼저 먹으면서 그때 그때 다 먹은 음식 & 걸린 시간 고려하면 됨.
1. 음식 양이 작은 것 먼저 먹어야하므로, ```heap```에 음식과 인덱스를 저장
```python
heap = []
for i in range(N):
    heapq.heappush(heap, [food_times[i], i + 1])
```
2. 제일 양이 작은 음식을 다 먹고 해당 바퀴를 끝내는 시간을 계산 -> 그 시간이 k보다 작을 때까지 음식을 다 먹음
```python
time, pre = 0, 0
while time + (heap[0][0] - pre) * N <= k:
    cur = heapq.heappop(heap)
    time += (cur[0] - pre) * N
    N -=1
    pre = cur[0]
```
3. k초 보다 적은 시간 안에서 최대로 바퀴를 돌았으므로, 남은 시간에 위치한 음식이 answer.
```python
answer = sorted(heap, key=lambda x:x[1])
return answer[(k - time) % N][1]
```

## Review
정렬 후 작은 음식부터 먹어야한다는 것까진 생각해냈는데, 걸린 시간을 바퀴 수(= 음식 양)로 계산 안하고 좀 복잡하게 계산해서 계속 틀렸음.. 다른 코드 참고해서 풀었다 ㅜ