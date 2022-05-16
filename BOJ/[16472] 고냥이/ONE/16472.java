import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int len = str.length(), answer = 0;

        Queue<Character> queue = new LinkedList<>();
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            queue.add(c);
            map.put(c, map.getOrDefault(c, 0) + 1);
            set.add(c);

            if (set.size() > N) {
                do {
                    char head = queue.poll();
                    map.put(head, map.get(head) - 1);

                    if (map.get(head) == 0)
                        set.remove(head);

                } while (set.size() > N);
            }

            if (queue.size() > answer)
                answer = queue.size();
        }

        System.out.println(answer);
    }
}