import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    private int indexNum = 1;
    private Map<String, Integer> map = new HashMap<>();
    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        int len = msg.length();

        initMap();

        for (int i = 0; i < len; i++) {
            int index = 0;
            StringBuilder builder = new StringBuilder();

            for (int j = i; j < len; j++) {
                builder.append(msg.charAt(j));

                if (map.containsKey(builder.toString())){
                    index = map.get(builder.toString());
                    i = j;
                }
                else {
                    map.put(builder.toString(), indexNum++);
                    break;
                }
            }
            answer.add(index);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private void initMap() {
        for (int i = 0; i < 26; i++)
            map.put(String.valueOf((char)(indexNum + 64)), indexNum++);
    }
}