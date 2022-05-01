import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        // 보석의 위치를 저장할 Map
        Map<String, Integer> gemMap = new HashMap<>();
        // 진열대에 있는 보석을 중복 없이 저장한 Set
        Set<String> gemSet = new HashSet<>(Arrays.asList(gems));

        // 보석의 종류를 구하기 위해 Set 사용
        int gemTypes = gemSet.size();

        int distance = Integer.MAX_VALUE;
        int start = 0, end = 0, left = 0, right = 0;

        while (true) {
            // 아직 종류가 다 안 채워진 경우
            if (gemTypes != gemMap.size()) {
                if (right == gems.length) break;
                else {
                    // 보석을 구매하는 경우로, Map에 개수를 증가시키면서 보석을 저장
                    gemMap.put(gems[right], gemMap.getOrDefault(gems[right], 0) + 1);
                    right++;
                }
            }

            // 종류가 다 채워진 경우
            if (gemTypes == gemMap.size()) {
                // 이전까지 구한 구간의 길이와 비교하여 작다면 update
                if (right - left < distance) {
                    distance = right - left;
                    start = left;
                    end = right;
                }

                gemMap.put(gems[left], gemMap.get(gems[left]) - 1);

                // 보석 개수가 0개이면 map에서 삭제
                if (gemMap.get(gems[left]) == 0) gemMap.remove(gems[left]);

                left++;
            }
        }
        
        return new int[] {start + 1, end};
    }
}