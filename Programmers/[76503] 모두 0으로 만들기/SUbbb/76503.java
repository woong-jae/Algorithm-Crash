import java.util.*;

class Solution {
    private ArrayList<Integer>[] adjList;
    private int size;
    private long answer = 0;
    private long[] values;
    private boolean[] visited;
    
    public long solution(int[] a, int[][] edges) {
        size = a.length;
        
        values = new long[size];
        visited = new boolean[size];
        adjList = new ArrayList[size];
        
        if (init(a) != 0) return -1;
        
        for (int[] edge : edges) {
            int n1 = edge[0];
            int n2 = edge[1];
            
            adjList[n1].add(n2);
            adjList[n2].add(n1);
        }
        
        dfs(0);
        
        return answer;
    }
    
    private int init(int[] a) {
        int sum = 0;
        
        for (int i = 0; i < size; i++) {
            sum += a[i];
            values[i] = a[i];
            adjList[i] = new ArrayList<>();
        }
        
        return sum;
    }
    
    private long dfs(int node) {
        visited[node] = true;
        
        // 방문하지 않은 자식 노드가 있다면 탐색
        for (int i = 0; i < adjList[node].size(); i++) {
            int cur = adjList[node].get(i);
            if (!visited[cur])
                values[node] += dfs(cur);
        }
        
        // 런타임 에러 원인
        // for (int n : adjList[node])
        //     if (!visited[n]) 
        //         values[node] += dfs(n);

        long value = values[node];
        answer += Math.abs(value);

        return value;
    }
}