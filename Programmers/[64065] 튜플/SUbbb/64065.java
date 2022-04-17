import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    // 숫자만 추출하고, 나타나는 횟수를 Map에 저장
    // 많이 등장한 순으로 튜플에 저장
    public int[] solution(String s) {
        Map<Integer, Integer> map = new HashMap<>();

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(s);

        while(matcher.find()) {
            int num = Integer.parseInt(matcher.group(0));
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        ArrayList<Integer> tuple = new ArrayList<>(map.keySet());
        tuple.sort((value1, value2) -> (map.get(value2).compareTo(map.get(value1))));

        return tuple.stream().mapToInt(i -> i).toArray();
    }
}