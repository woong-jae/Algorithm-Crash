import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int containCount = 0;
        int zeroCount = 0;
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> win_num = new HashSet<>();

        for (int num : win_nums)
            win_num.add(num);

        for(int i = 1; i <= 6; i++)
            map.put(7 - i, i);
        map.put(0, 6);

        for (int num : lottos) {
            if (num == 0) {
                zeroCount++;
                continue;
            }
            if(win_num.contains(num))
                containCount++;
        }
        answer[0] = map.get(containCount + zeroCount);
        answer[1] = map.get(containCount);

        return answer;
    }
}
