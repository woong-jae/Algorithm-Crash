import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<String> queue = new LinkedList<>();

        if (cacheSize == 0)
            return cities.length * 5;

        for (String city : cities) {
            city = city.toLowerCase();

            if(queue.contains(city)){
                queue.remove(city);
                queue.add(city);
                answer += 1;
            }
            else {
                if(queue.size() >= cacheSize)
                    queue.poll();
                queue.add(city);
                answer += 5;
            }
        }
        return answer;
    }
}