import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<Long, Long> map = new HashMap<>();
    public long[] solution(long k, long[] room_number) {
        int n = room_number.length;
        long[] answer = new long[n];

        for (int i = 0; i < n; i++)
            answer[i] = find(room_number[i]);

        return answer;
    }

    private long find(long num) {
        if (!map.containsKey(num)) {
            map.put(num, num + 1);
            return num;
        }

        long room = find(map.get(num));
        map.put(num, room);

        return room;
    }
}