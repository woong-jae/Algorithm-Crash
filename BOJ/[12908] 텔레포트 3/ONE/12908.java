import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getTime(Node o) {
            return Math.abs(this.x - o.x) + Math.abs(this.y - o.y);
        }
    }

    static class Teleport {
        Node start;
        Node end;

        public Teleport(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    private static long min = Long.MAX_VALUE;
    private static List<Teleport> teleports = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Node subin = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        st = new StringTokenizer(br.readLine());
        Node home = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken()),
                    x2 = Integer.parseInt(st.nextToken()), y2 = Integer.parseInt(st.nextToken());
            teleports.add(new Teleport(new Node(x1, y1), new Node(x2, y2)));
        }

        dfs(0, 0, subin, home, new boolean[3]);

        System.out.println(min);
    }

    private static void dfs(int depth, long time, Node current, Node dest, boolean[] visited) {
        if (depth == 3) {
            min = Math.min(min, time + current.getTime(dest));
            return;
        }
        for (int i = 0; i < 3; i++)
            if (!visited[i]) {
                Teleport teleport = teleports.get(i);
                visited[i] = true;
                // 앞쪽으로 텔 탈 때
                dfs(depth + 1, time + current.getTime(teleport.start) + 10,
                        new Node(teleport.end.x, teleport.end.y), dest, visited);
                // 뒤쪽으로 텔 탈 때
                dfs(depth + 1, time + current.getTime(teleport.end) + 10,
                        new Node(teleport.start.x, teleport.start.y), dest, visited);
                // 텔 안탈 때
                dfs(depth + 1, time, new Node(current.x, current.y), dest, visited);
                visited[i] = false;
            }
    }
}