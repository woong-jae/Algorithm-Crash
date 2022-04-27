import java.util.*;

class Solution {
    public int[] solution(String[] gems) {

        // 중복을 제외한 보석들을 담는 Set
        Set<String> set = new HashSet<>(Arrays.asList(gems));

        // 보석들을 순서대로 넣으며 구간을 찾기 위한 Queue
        Queue<String> queue = new LinkedList<>();

        // 보석마다 몇개씩 있는지 저장할 Map
        Map<String, Integer> map = new HashMap<>();

        int start = 0, temp = 0;
        int len = gems.length;
        for (String gem : gems) {
            map.put(gem, map.getOrDefault(gem, 0) + 1);

            queue.add(gem);

            while (true) {
                String head = queue.peek();

                // Queue의 맨앞에 있는 보석이 Map에서 2개 이상이면
                // 맨앞의 보석을 빼주고 출발점을 뒤로 한칸 땡긴다
                if (map.get(head) > 1) {
                    map.put(head, map.get(head) - 1);
                    queue.poll();
                    temp++;
                }

                else break;
            }

            // Map 과 Set이 사이즈가 같다는 건 모든 보석이 구간에 있다는 이야기고
            // 현재 찾은 구간들중 가장 작은 구간이면 최소 구간을 갱신하고
            // 임시 시작점이었던것을 정답 시작점으로 해준다
            if (map.size() == set.size() && len > queue.size()) {
                len = queue.size();
                start = temp;
            }
        }

        return new int[]{start + 1, start + len};
    }
}