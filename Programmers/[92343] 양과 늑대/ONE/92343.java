import java.util.ArrayList;
import java.util.List;

class Solution {
    private int answer;
    private int[] info;
    private boolean[] visited;
    private List<Integer>[] tree;
    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        this.info = info;
        this.visited = new boolean[n];

        tree = new List[n];
        for (int i = 0; i < n; i++)
            tree[i] = new ArrayList<>();

        for (int[] edge : edges)
            tree[edge[0]].add(edge[1]);

        List<Integer> next = new ArrayList<>();
        next.add(0);

        traversal(0, 0, 0, next);

        return answer;
    }

    private void traversal(int num, int sheep, int wolf, List<Integer> next) {
        sheep += info[num] ^ 1;
        wolf += info[num];

        if (sheep <= wolf)
            return;

        answer = Math.max(answer, sheep);

        List<Integer> temp = new ArrayList<>(next);

        if (!tree[num].isEmpty())
            temp.addAll(tree[num]);
        temp.remove(Integer.valueOf(num));

        for (int n : temp)
            traversal(n, sheep, wolf, temp);
    }
}