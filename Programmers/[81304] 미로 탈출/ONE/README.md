# [81304] 미로 탈출
## Algorithm
- **Dijkstra**, **Priority Queue**
## Logic
- 다익스트라 알고리즘과, 우선순위 큐를 사용한다
- 방문을 확인하는데에 노드의 인덱스 뿐만 아니라 각 함정 들의 상태에 따라서도 다르게 저장해야한다
- 비트값을 이용해 함정의 발동 유무를 확인한다

```java
private int dijkstra(int n, int start, int end, int[] traps) {

    // 가중치의 오름차순으로 정렬
    Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    // 노드번호와 함정의 발동된 상태를 비트형으로 표현(최대 10개)
    boolean[][] visited = new boolean[MAX_N][1 << 10];

    queue.add(new int[]{0, start, 0});

    while (!queue.isEmpty()) {
        int[] current = queue.poll();
        int w = current[0];
        int u = current[1];
        int state = current[2];

        if(u == end) return w;

        if(visited[u][state]) continue;

        visited[u][state] = true;

        // 현재 노드가 함정인지 확인
        boolean currentTrapped = false;

        // 함정의 정보를 담을 Map
        Map<Integer, Boolean> trapped = new HashMap<>();

        for (int i = 0; i < traps.length; i++) {

            // 함정 배열의 인덱스에 해당하는 비트가 1로 설정 됨
            int bit = 1 << i;

            // 비트가 켜져있으면 -> 함정이 발동된 상태
            if ((state & bit) != 0) {
                // 이미 발동되어 있는 노드에 방문했을 때 비트를 꺼준다
                if(traps[i] == u) state &= ~bit;

                    // 함정에 처음 왔으니 Map 에 넣어준다
                else trapped.put(traps[i], true);
            }

            // 비트가 꺼져있으면
            else {
                // 함정일 떄 비트를 켜준다
                if (traps[i] == u) {
                    state |= bit;
                    trapped.put(traps[i], true);
                    currentTrapped = true;
                }
            }
        }

        for (int v = 1; v <= n; v++) {
            if(v == u) continue;

            // 다음에 이동할 노드가 발동된 함정이라면
            boolean nextTrapped = trapped.containsKey(v);

            // 둘다 발동된 함정이거나 발동되지 않은 함정들일 때
            if(currentTrapped == nextTrapped) {
                if(graph[u][v] != INF)
                    queue.add(new int[]{w + graph[u][v], v, state});
            }

            // 한쪽만 발동된 함정일 때는 뒤집힌 간선으로 확인한다
            else {
                if(graph[v][u] != INF)
                    queue.add(new int[]{w + graph[v][u], v, state});
            }
        }
    }

    return INF;
}
```

## Review
매워서 정신이 나갈것 같았다  
다익스트라와 비트연산에 대한 아이디어를 생각 자체를 못했고  
비트 연산 또한 이해하는데에 시간이 걸려서 해당 문제에 대한 유튜브로 수업을 들었다  
그래도 점수가 처음에 50점은 나왔으니 실전에서도 선방하면 좋겠다...
