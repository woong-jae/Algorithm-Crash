import java.util.*;

class Solution {
    // 최대 노드의 개수
    private final static int MAX_N = 1001;
    // 무한값
    private final static int INF = Integer.MAX_VALUE;
    // 인접 그래프
    private int[][] graph = new int[MAX_N][MAX_N];
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++) {
                if(i == j) graph[i][j] = 0;
                else graph[i][j] = INF;
            }

        for (int[] road : roads) {
            int u = road[0], v = road[1], w = road[2];

            // 가중치가 여러개일 때, 현재 값보다 크면 갱신하지 않는다
            if(w < graph[u][v])
                graph[u][v] = w;
        }

        return dijkstra(n, start, end, traps);
    }

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
}
