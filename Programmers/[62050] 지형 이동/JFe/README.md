# [62050] 지형 이동 - Python

## 🔍 Algorithm
**BFS, Priority Queue**

## 💻 Logic

```Python
    # 우선순위 큐를 이용한 탐색
    # heapq 정보 : [사다리 비용, x좌표, y좌표]
    heapq.heappush(h, [0, 0, 0])
    while h:
        w, x, y = heapq.heappop(h)
        if visited[y][x]: continue  # 방문했으면 통과(우선순위가 밀린 경우)
        visited[y][x] = True
        answer += w
        # 상하좌우 탐색
        for i in range(4):
            next_x, next_y = x + dx[i], y + dy[i]
            if 0 <= next_x < n and 0 <= next_y < n and not visited[next_y][next_x]:
                # 사다리 비용(높이 차) 계산
                next_w = abs(land[next_y][next_x] - land[y][x])
                # 높이 차이가 height보다 크면: 사다리 비용 그대로 넣고 heappush
                if next_w > height:
                    heapq.heappush(h, [next_w, next_x, next_y])
                # 높이 차이가 height 이하면: 사다리 비용으로 0 넣고 heappush
                else:
                    heapq.heappush(h, [0, next_x, next_y])
```
- **우선순위 큐를 이용한 BFS 탐색**  
    `heapq` 정보 : [사다리 비용, x좌표, y좌표]
- **상하좌우 탐색**  
    - 높이 차이가 `height`보다 **크면**: 사다리 비용 그대로 넣고 **heappush**  
    - 높이 차이가 `height` **이하면**: 사다리 비용으로 **0** 넣고 **heappush**  


## 📝 Review

일단 BFS 탐색을 하긴 하는데 그룹을 나눠서 비교하면서 사다리 비용을 계산해야 하나.. 계속 고민하다가 안되겠어서 다른 풀이를 참고했다.  
처음에는 그룹 나눠서 크루스칼로 계산하는 풀이가 처음 내가 생각했던 풀이와 유사해서 그렇게 하려다가 이 문제에서는 그냥 우선순위 큐만 이용해서도 풀 수 있다는 풀이를 보고 이 풀이를 참고해서 풀었다.  
똑똑한 사람.. 참 많네요..  
