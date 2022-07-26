import java.util.*;

class Solution {
    // 도시 이름이 들어온 순서를 저장하기 위한 큐
    Queue<String> cache = new LinkedList<>();

    public int solution(int cacheSize, String[] cities) {
        int time = 0;
        
        // cacheSize가 0이면 캐시 힛이 발생하지 않음
        if (cacheSize == 0) return cities.length * 5;
        
        for (String city : cities) {
            city = city.toLowerCase();
            // 이미 캐시에 있다면
            if (cache.contains(city)) {
                cache.remove(city);
                time++;
            } else {
                // 맵에 없는데, 더 이상 맵 공간도 없는 경우, 처음 들어온 값을 삭제
                if (cache.size() >= cacheSize) cache.poll();
                time += 5;
            }
            cache.add(city);
        }
        
        return time;
    }
}