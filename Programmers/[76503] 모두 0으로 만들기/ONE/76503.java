import java.util.*;

class Solution {
    private long answer = 0;
    private long[] longA;
    private boolean[] visited;
    private List<Integer>[] tree;

    public long solution(int[] a, int[][] edges) {
        int n = a.length;
        long sum = 0;

        longA = new long[n];
        visited = new boolean[n];
        tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            sum += a[i];
            longA[i] = a[i];
            tree[i] = new ArrayList<>();
        }

        if (sum != 0)
            return -1;

        for (int[] edge : edges) {
            this.tree[edge[0]].add(edge[1]);
            this.tree[edge[1]].add(edge[0]);
        }

        traversal(0);

        return answer;
    }

    public long traversal(int index) {
        visited[index] = true;

        for (int i = 0; i < tree[index].size(); i++) {
            int curr = tree[index].get(i);
            if (!visited[curr]) {
                longA[index] += traversal(curr);
            }
        }

        answer += Math.abs(longA[index]);

        return longA[index];
    }
}