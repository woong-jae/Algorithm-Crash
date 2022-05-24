import java.util.*;

class Solution {
    Map<Long, Long> roomMap = new HashMap<>();
    
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        for (int i = 0; i < room_number.length; i++)
            answer[i] = getRoom(room_number[i]);
        
        return answer;
    }
    
    private long getRoom(long room) {
        // 해당 방이 아직 배정 받지 않은 경우
        if (!roomMap.containsKey(room)) {
            roomMap.put(room, room + 1);
            return room;
        }

        // 해당 방이 이미 배정된 경우, 재귀 호출을 통해 빈 방을 찾는다.
        long emptyRoom = getRoom(roomMap.get(room));
        roomMap.put(room, emptyRoom);
        
        return emptyRoom;
    }
}