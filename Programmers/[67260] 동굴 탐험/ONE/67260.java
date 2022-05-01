import java.util.ArrayList;
import java.util.Stack;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        int[] before = new int[n];
        int[] after = new int[n];
        boolean[] visited = new boolean[n];
        ArrayList<Integer>[] tree = new ArrayList[n];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < n; i++)
            tree[i] = new ArrayList<>();

        for (int[] p : path) {
            tree[p[0]].add(p[1]);
            tree[p[1]].add(p[0]);
        }

        for (int[] o : order)
            before[o[1]] = o[0];

        if (before[0] != 0)
            return false;

        visited[0] = true;
        stack.addAll(tree[0]);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (visited[current])
                continue;

            if (!visited[before[current]]) {
                after[before[current]] = current;
                continue;
            }

            visited[current] = true;

            stack.addAll(tree[current]);
            stack.add(after[current]);
        }

        for(boolean v : visited)
            if (!v)
                return false;

        return true;
    }
}
