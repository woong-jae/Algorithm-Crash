import java.util.*;

class Node {
    int num;
    int sheepCount;
    int wolfCount;
    // 갈 수 있는 노드를 저장하는 리스트!
    ArrayList<Integer> list;
    
    public Node(int num, int sheepCount, int wolfCount, ArrayList<Integer> list) {
        this.num = num;
        this.sheepCount = sheepCount;
        this.wolfCount = wolfCount;
        this.list = list;
    }
}

class Solution {
    static ArrayList<ArrayList<Integer>> infoList;
    static int[] info;
    
    public int solution(int[] info, int[][] edges) {
        init(info.length, edges);
        this.info = info;
        
        return bfs();
    }
    
    private void init(int length, int[][] edges) {
        infoList = new ArrayList<>();
        
        for (int i = 0; i < length; i++)
            infoList.add(new ArrayList<>());
        
        for (int[] edge : edges) {
            int n1 = edge[0];
            int n2 = edge[1];
            
            infoList.get(n1).add(n2);
        }
    }
    
    private int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 1, 0, infoList.get(0)));
        int max = 0;
        
        while(!queue.isEmpty()) {
            Node now = queue.poll();
            int nowN = now.num;
            int sheepCount = now.sheepCount;
            int wolfCount = now.wolfCount;
            ArrayList<Integer> nowNodeList = now.list;
            
            if (max < sheepCount)
                max = sheepCount;
            
            // 현 노드에서 갈 수 있는 노드에 대해 확인
            // nowN = 0, nextNode = {1, 8}, fromNextNode = {{2, 4}, {7, 9}}
            for (int nextNode : nowNodeList) {
                // 양이거나, 늑대의 수가 양의 수보다 적은 경우
                if (info[nextNode] == 0)
                    queue.add(new Node(nextNode, sheepCount + 1, wolfCount, makeList(nowNodeList, nowN, nextNode)));
                else
                    if (wolfCount + 1 < sheepCount)
                        queue.add(new Node(nextNode, sheepCount, wolfCount + 1, makeList(nowNodeList, nowN, nextNode)));
            }
        }
        
        return max;
    }
    
    private ArrayList<Integer> makeList(ArrayList<Integer> nowNodeList, int nowN, int nextNode) {
        // 이동할 수 있는 노드를 저장할 리스트
        ArrayList<Integer> nextList = new ArrayList<>();

        // 다음 노드에서 갈 수 있는 노드를 저장, 연결된 자식 노드에 대해서만!
        for (int fromNextNode : infoList.get(nextNode)) nextList.add(fromNextNode);

        // 현 노드까지 오는데 방문했던 노드들 중 현 노드와 직전에 방문한 노드를 제외하고 저장, DFS로 따지면 백트래킹하는 느낌..
        for (int checkNode : nowNodeList) 
            if (checkNode != nowN && checkNode != nextNode)
                nextList.add(checkNode);
        
        return nextList;
    }
}