# [12908] 텔레포트 3 - Python

## 🔍 Algorithm
**Dijkstra**

## 💻 Logic

```Python
# idx에 좌표 전부 저장
idx = []
idx.extend([[xs, ys], [xe, ye]])
for x1, y1, x2, y2 in teleport:
    idx.extend([[x1, y1], [x2, y2]])
# graph 생성
graph = defaultdict(list)
for i, (x1, y1) in enumerate(idx):
    for j in range(i + 1, len(idx)):
        x2, y2 = idx[j][0], idx[j][1]
        graph[i].append([j, abs(x2 - x1) + abs(y2 - y1)])
        graph[j].append([i, abs(x2 - x1) + abs(y2 - y1)])
        # 연결되어 있는 텔레포트 좌표인 경우
        if (i == 2 and j == 3) or (i == 4 and j == 5) or (i == 6 and j == 7):
            graph[i].append([j, 10])
            graph[j].append([i, 10])
```
- **`idx`에 좌표 전부 저장**  
    시작 좌표와 끝 좌표, 텔레포트 좌표를 모두 쌍을 지어서 같은 리스트에 저장해준다.  
- **`graph` 생성**  
    - **맨허튼 거리**를 계산해서 `graph`에 value 값으로 도착 좌표와 함께 저장한다.  
    - 서로 연결되어 있는 텔레포트 좌표인 경우에는 **10**만큼만 value 값으로 저장한다.  

```Python
def dijkstra(graph, start, end):
    h = []
    INF = int(1e9)
    distance = [INF for _ in range(8)]
    heapq.heappush(h, (0, start))
    distance[start] = 0
    while h:
        cost, node = heapq.heappop(h)
        if cost > distance[node]: continue
        for next_node, value in graph[node]:
            next_cost = cost + value
            if next_cost < distance[next_node]:
                distance[next_node] = next_cost
                heapq.heappush(h, (next_cost, next_node))
    return distance[end]
```
- **Dijkstra 탐색**  


## 📝 Review

다른 사람들 풀이와는 다르게 다익스트라를 사용해서 풀었는데 예제 테스트케이스는 전부 통과하는데 제출했을 때 틀린 케이스를 못찾겠다...  
그래프를 생성하는 부분에 문제가 있는 것 같은데... 문제 이해를 잘못했나  
