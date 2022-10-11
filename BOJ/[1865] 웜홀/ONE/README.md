# [1865] 웜홀
## Algorithm
- **Bellman Ford**

## Logic
- 음의 가중치가 존재할 때에는 벨만 포드 알고리즘을 이용해 최단거리를 구한다
- N-1번동안 가중치를 갱신하며 최단거리를 구한다
  - 해당 순환중에 값 변경이 없다면 음의 가중치가 없다는 것이기 때문에 return false
  - N 번째에 갱신이 발생했다는 것은 음의 사이클이 있다는 것으로 return true를 해준다

```java
private static boolean bellmanFord(int N) {
    boolean check = false;
    Arrays.fill(dist, INF);
    dist[1] = 0;

    for (int i = 1; i < N; i++) {
        check = false;
        for (int j = 1; j <= N; j++) {
            for (Node next : graph[j]) {
                if (dist[j] + next.cost < dist[next.vertex]) {
                    check = true;
                    dist[next.vertex] = dist[j] + next.cost;
                }
            }
        }
        if (!check)
            return false;
    }

    return check;
}
```

## Review
여러 방면으로 고민하다가 어려워 알고리즘 힌트를 보고 풀었다.
음의 가중치가 있는 그래프에서는 벨만-포드 알고리즘을 사용해야한다는 것을 확실히 알게 되었고  
문제 자체가 알고리즘만 안다면 쉽지만 알고리즘을 모르면 나처럼 난이도가 올라갈거같다...