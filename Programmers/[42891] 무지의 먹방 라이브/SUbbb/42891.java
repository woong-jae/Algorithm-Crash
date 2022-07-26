import java.util.*;

class Food implements Comparable<Food> {
    int time;
    int idx;
    
    public Food(int time, int idx) {
        this.time = time;
        this.idx = idx;
    }
    
    @Override
    public int compareTo(Food f) {
        return this.time - f.time;
    }
}

class Solution {
    public int solution(int[] food_times, long k) {
        Queue<Food> pq = new PriorityQueue<>();
        // 모든 음식을 먹는데 걸리는 총 시간
        long foodSum = 0;
        int len = food_times.length;
        
        for (int i = 0; i < len; i++) {
            pq.add(new Food(food_times[i], i));
            foodSum += food_times[i];
        }
    
        // 음식을 다 섭취했는데 아직 K초가 되지 않았다면 -1 반환
        if (foodSum <= k) return -1;
        
        // 먹는데 사용한 시간
        long totalTime = 0;
        // 직전에 다 먹은 음식 시간
        long prevTime = 0;
        
        // 현재 음식을 다 먹을 수 있는 경우, totalTime을 증가
        while (totalTime + ((pq.peek().time - prevTime) * len) <= k) {
            int now = pq.poll().time;
            totalTime += (now - prevTime) * len;
            len -= 1;
            prevTime = now;
        }
        
        ArrayList<Food> remainFoods = new ArrayList<>();
        
        while (!pq.isEmpty()) remainFoods.add(pq.poll());
        
        // idx를 기준으로 오름차순 정렬
        remainFoods.sort(Comparator.comparingInt(o -> o.idx));
        
        return remainFoods.get((int) ((k - totalTime) % len)).idx + 1;
    }
}