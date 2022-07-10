import java.util.*;

class Solution {
    // 직원별 워크숍에 참여했을 때의 매출과 참여하지 않았을 때의 매출, 0: 참여 X, 1: 참여 O
    static int[][] costs;
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    static int[] sales;
    
    public int solution(int[] sales, int[][] links) {
        this.sales = sales;
        init();
        
        makeAdjList(links);
        
        traversal(0);
        
        return Math.min(costs[0][0], costs[0][1]);
    }
    
    private static void init() {
        costs = new int[sales.length][2];
        
        for (int i = 0; i < sales.length; i++) 
            adjList.add(new ArrayList<>());
    }
    
    private static void makeAdjList(int[][] links) {
        for (int[] link : links)
            adjList.get(link[0] - 1).add(link[1] - 1);
    }
    
    private static void traversal(int node) {
        // 해당 직원이 워크숍에 참여하지 않는 경우
        costs[node][0] = 0;
        // 해당 직원이 워크숍에 참여한 경우 -> 해당 직원 매출을 저장
        costs[node][1] = sales[node];
        
        // 리프 노드라면, 상위로 최소 비용을 전달해야 함
        if (adjList.get(node).isEmpty()) return;
        
        int extraCost = 10001;
        for (int child : adjList.get(node)) {
            traversal(child);
            
            // 최소 비용을 부모 노드(팀장)에 누적
            if (costs[child][0] < costs[child][1]) {
                costs[node][0] += costs[child][0];
                costs[node][1] += costs[child][0];
                // 팀장과 팀원 모두 참여하지 않으면 안되기에 필요한 추가 비용 계산
                extraCost = Math.min(extraCost, costs[child][1] - costs[child][0]);
            } else {
                costs[node][0] += costs[child][1];
                costs[node][1] += costs[child][1];    
                // 둘 중 한 명이라도 참여했다면 추가 비용은 필요 없음
                extraCost = 0;
            }
        }
        
        costs[node][0] += extraCost;
    }
}