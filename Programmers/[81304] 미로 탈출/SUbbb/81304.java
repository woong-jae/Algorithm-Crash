import java.util.*;

class Node implements Comparable<Node> {
    int to;
    int weight;
    int status;

    public Node(int to, int weight, int status) {
        this.to = to;
        this.weight = weight;
        this.status = status;
    }

    // 오름차순 정렬을 위함, 여러 경로 중 최소 비용을 먼저 탐색하기 위함
    @Override
    public int compareTo(Node n) {
        return this.weight - n.weight;
    }
}

class Maze {
    List<Node>[] orgList;
    List<Node>[] reverseList;
    Map<Integer, Integer> trapMap;
    Queue<Node> queue;
    int[][] dist;
    int canForward = 0;

    public Maze(int n, int[][] roads, int[] traps) {
        // List 초기화
        orgList = new ArrayList[n + 1];
        reverseList = new ArrayList[n + 1];
        initList(n);
        fillList(roads);

        // 트랩 초기화(좌표 압축을 통해 2,5,10 -> 1,2,3으로 저장)
        trapMap = new HashMap<>();
        initTrap(traps);

        dist = new int[n+1][1<<trapMap.size()+1];
        initDist(n);
    }

    private void initList(int n) {
        for (int i = 0; i < n + 1; i++) {
            orgList[i] = new ArrayList<>();
            reverseList[i] =new ArrayList<>();
        }
    }

    // 초기 경로와, 트랩으로 인해 반대로 전환되는 경로를 저장
    private void fillList(int[][] roads) {
        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            int weight = road[2];

            orgList[from].add(new Node(to, weight, 0));
            reverseList[to].add(new Node(from, weight, 0));
        }
    }

    // 좌표 압축
    private void initTrap(int[] traps) {
        for (int i = 0; i < traps.length; i++)
            trapMap.put(traps[i], 1 << (i + 1));
    }

    private void initDist(int n) {
        for (int i = 0; i < n + 1; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
    }

    public int solution(int start, int end) {
        dijkstra(start, end);

        int minCost = Integer.MAX_VALUE;
        for (int d : dist[end])
            minCost = Math.min(minCost, d);

        return minCost;
    }

    private void dijkstra(int s, int e) {
        queue = new PriorityQueue<>();
        dist[s][0] = 0;
        queue.add(new Node(s, 0, 0));

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int to = node.to;
            int weight = node.weight;
            int status = node.status;

            if (to == e) return;

            // 이동하려는 노드가 트랩인지 확인하고, 트랩이라면 이미 밟은 트랩인지 확인
            // f = 0, 밟지 않은 트랩이거나 일반 노드 / f = 1, 이미 밟은 트랩
            int f = 0;
            if (trapMap.containsKey(to)) {
                if ((status & trapMap.get(to)) != 0)
                    f = 1;
            }

            forward(to, weight, status, f);
            backward(to, weight, status, f);
        }
    }

    // orgList = 정방향에 대한 탐색
    private void forward(int to, int weight, int status, int f) {
        for (Node next : orgList[to]) {
            canForward = f;
            int nStatus = status;
            if (trapMap.containsKey(next.to)) {
                if ((status & trapMap.get(next.to)) != 0)
                    canForward ^= 1;
                nStatus ^= trapMap.get(next.to);
            }

            if (canForward != 0) continue;
            if (dist[next.to][status] > weight + next.weight) {
                dist[next.to][status] = weight + next.weight;
                queue.add(new Node(next.to, dist[next.to][status], nStatus));
            }
        }
    }

    // reverseList = 역방향에 대한 탐색
    private void backward(int to, int weight, int status, int f) {
        for (Node next : reverseList[to]) {
            canForward = f;
            int nStatus = status;
            if (trapMap.containsKey(next.to)) {
                if ((status & trapMap.get(next.to)) != 0)
                    canForward ^= 1;
                nStatus ^= trapMap.get(next.to);
            }

            if (canForward != 1) continue;
            if (dist[next.to][status] > weight + next.weight) {
                dist[next.to][status] = weight + next.weight;
                queue.add(new Node(next.to, dist[next.to][status], nStatus));
            }
        }
    }
}

class Solution {
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        Maze m = new Maze(n, roads, traps);

        return m.solution(start, end);
    }
}