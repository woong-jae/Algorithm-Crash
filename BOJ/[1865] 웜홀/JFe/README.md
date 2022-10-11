# [1865] 웜홀 - Python

## 🔍 Algorithm
**Bellman-Ford**

## 💻 Logic

```Python
def bellman_ford(N, node):
    distance= [int(1e9) for _ in range(N + 1)]
    distance[node] = 0
    # 전체 n번 반복
    for i in range(N):
        # 모든 간선 확인
        for key, value in graph.items():
            for next_node, cost in value:
                # 다음 노드로 이동하는 거리가 더 짧은 경우 업데이트
                if distance[next_node] > distance[key] + cost:
                    distance[next_node] = distance[key] + cost
                    # N번째에도 값이 업데이트 되면, 음의 사이클이 존재
                    if i == N - 1: return True
    return False
```
- **벨만 포드 탐색 함수**  
    - 모든 간선을 확인하고, 다음 노드로 이동하는 거리가 더 짧은 경우에는 `distance` 값 업데이트  
    - 위 과정을 **N번** 반복하고, **N번째**(`i == N - 1`)에도 값이 업데이트 되면 음의 사이클이 존재하는 경우기 때문에 **True** 반환  


## 📝 Review

최근에 CS 문제에서 벨만 포드 알고리즘을 접했어서 음수 사이클을 탐색하려면 벨만 포드 알고리즘을 활용해야 한다는 건 빨리 생각해낼 수 있었다.  
구현에서 버벅거렸는데 다익스트라 말고 벨만 포드도 익숙해져야겠다,,