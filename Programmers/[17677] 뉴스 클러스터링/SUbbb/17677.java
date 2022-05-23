import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        int size1 = 0;
        int size2 = 0;

        // 단어와 빈도 수 초기화
        for (int i = 0; i < str1.length() - 1; i++) {
            String str = parsing(str1, i);
            if (str.length() != 2) continue;

            size1++;
            if (map1.containsKey(str)) map1.put(str, map1.get(str) + 1);
            else map1.put(str, 1);
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            String str = parsing(str2, i);
            if (str.length() != 2) continue;

            size2++;
            if (map2.containsKey(str)) map2.put(str, map2.get(str) + 1);
            else map2.put(str, 1);
        }

        // 교집합 구하기, 두 빈도 수 중 작은 값을 교집합의 크기로 지정
        int inter = 0;
        for (String key : map1.keySet())
            if (map2.containsKey(key))
                inter += map1.get(key) > map2.get(key) ? map2.get(key) : map1.get(key);
        
        // 두 집합이 모두 공집합인 경우 J = 1
        if (map1.size() == 0 && map2.size() == 0) return 65536;
        
        float j = (float) inter / (float) (size1 + size2 - inter);

        int answer = (int) (j * 65536);

        return answer;
    }

    private String parsing(String str, int i) {
        return str.substring(i, i+2).replaceAll("[^a-zA-Z]","");
    }
}