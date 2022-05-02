import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String s) {
        Map<String, String> map = initMap();
        StringBuilder sb = new StringBuilder();
        StringBuilder tmp = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9')
                sb.append(c);

            else {
                tmp.append(c);

                if (map.containsKey(tmp.toString())) {
                    sb.append(map.get(tmp.toString()));
                    tmp.delete(0, tmp.length());
                }
            }
        }

        return Integer.parseInt(sb.toString());
    }

    private Map<String, String> initMap() {
        Map<String, String> map = new HashMap<>();

        map.put("zero", "0");
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");
        map.put("seven", "7");
        map.put("eight", "8");
        map.put("nine", "9");

        return map;
    }
}