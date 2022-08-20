import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static List<Integer> answer = new ArrayList<>();
    private static List<Integer>[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()), N = Integer.parseInt(st.nextToken()),
                F = Integer.parseInt(st.nextToken());
        tree = new List[N + 1];
        for (int i = 0; i <= N; i++)
            tree[i] = new ArrayList<>();

        for (int i = 0; i < F; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            int size = tree[i].size();
            if (size < K - 1)
                continue;
            tree[i].sort(Comparator.naturalOrder());
            dfs(i, 0, K - 1, new boolean[size], new ArrayList<>());
            if (!answer.isEmpty())
                break;
        }
        if (answer.isEmpty())
            System.out.println(-1);
        else {
            answer.sort(Comparator.naturalOrder());
            for (int n : answer)
                System.out.println(n);
        }
    }

    private static void dfs(int i, int depth, int n, boolean[] check, List<Integer> list) {
        if (!answer.isEmpty())
            return;

        if (depth == n) {
            for (int index : list)
                for (int num : list)
                    if (num != index && !tree[index].contains(num))
                        return;
            answer.add(i);
            answer.addAll(list);
            return;
        }

        for (int j = 0; j < check.length; j++)
            if (!check[j]) {
                check[j] = true;
                list.add(tree[i].get(j));
                dfs(i, depth + 1, n, check, list);
                list.remove(tree[i].get(j));
            }

    }
}