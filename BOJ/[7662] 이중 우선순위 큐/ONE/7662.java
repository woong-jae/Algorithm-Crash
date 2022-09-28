import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static Queue<Integer> ascendingQueue;
    private static Queue<Integer> descendingQueue;
    private static Map<Integer, Integer> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            ascendingQueue = new PriorityQueue<>(Comparator.naturalOrder()); // 오름차순 정렬
            descendingQueue = new PriorityQueue<>(Comparator.reverseOrder()); // 내림차순 정렬
            map = new HashMap<>();

            int k = Integer.parseInt(br.readLine());
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                String cal = st.nextToken();
                int n = Integer.parseInt(st.nextToken());

                if (cal.equals("I")) {
                    insert(n);
                    continue;
                }

                if (map.isEmpty()) continue; // 삭제할 수가 없다면 무시

                if (n == 1) {
                    delete(descendingQueue, map); // 최댓값 삭제
                } else {
                    delete(ascendingQueue, map); // 최솟값 삭제
                }
            }

            if (map.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                int max = delete(descendingQueue, map);
                int min = map.isEmpty() ? max : delete(ascendingQueue, map);
                System.out.println(max + " " + min);
            }
        }
        br.close();
    }

    private static void insert(int n) {
        ascendingQueue.add(n);
        descendingQueue.add(n);
        map.put(n, map.getOrDefault(n, 0) + 1);
    }

    private static int delete(Queue<Integer> queue, Map<Integer, Integer> map) {
        int result = 0, count;

        while (!queue.isEmpty()) {
            result = queue.poll();
            count = map.getOrDefault(result, 0);

            if (count == 0) continue; // 해당 숫자가 존재하지 않으면 map 에서 제거 하고 다음 조건에 해당되는 수를 찾는다

            if (count == 1) // 1개 밖에 없을 경우 맵에서 삭제
                map.remove(result);
            else
                map.put(result, count - 1); // 2개 이상일 경우 개수를 1개 줄인다
            break;
        }
        return result;
    }
}