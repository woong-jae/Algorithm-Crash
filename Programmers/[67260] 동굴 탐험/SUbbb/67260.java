import java.util.*;

class Solution {
    boolean[] visited;
    int[] before;
    int[] after;
    ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    
    public boolean solution(int n, int[][] path, int[][] order) {
        visited = new boolean[n];
        before = new int[n];
        after = new int[n];
        
        // 방문 우선순위 저장
        for (int[] or : order) before[or[1]] = or[0];
        
        for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());
        
        // 인접 리스트 생성
        for (int[] p : path) {
            adjList.get(p[0]).add(p[1]);
            adjList.get(p[1]).add(p[0]);
        }
        
        // 0보다 먼저 방문해야 하는 방이 있다면 바로 false
        if (before[0] != 0) return false;
        
        dfs();
        
        // 모든 방문 후, 방문하지 않은 방이 있다면 false
        for (int i = 0; i < n; i++)
            if (!visited[i]) return false;
        return true;
    }
    
    private void dfs() {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        visited[0] = true;

        for (int next : adjList.get(0)) stack.push(next);
        
        while(!stack.empty()) {
            int now = stack.pop();
            if (visited[now]) continue;
            
            if (!visited[before[now]]) {
                after[before[now]] = now;
                continue;
            }
            
            visited[now] = true;
            
            for (int next : adjList.get(now))
                if (!visited[next]) stack.push(next);

            stack.push(after[now]);
        }
    }
}