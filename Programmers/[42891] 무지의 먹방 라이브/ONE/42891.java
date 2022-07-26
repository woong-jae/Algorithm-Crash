import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Food{
    int num;
    int time;

    public Food(int num, int time) {
        this.num = num;
        this.time = time;
    }
}

class Solution {
    public int solution(int[] food_times, long k) {
        List<Food> list = new ArrayList<>();
        int n = food_times.length;

        for (int i = 1; i <= n; i++)
            list.add(new Food(i, food_times[i - 1]));

        list.sort(Comparator.comparingInt(o -> o.time));

        int index = 0;
        int previous = 0;
        for (Food current : list) {
            long diff = current.time - previous;

            if (diff != 0) {
                long spend = diff * n;

                if (spend <= k) {
                    k -= spend;
                    previous = current.time;
                }
                else {
                    k %= n;
                    list.subList(index, list.size()).sort(Comparator.comparingInt(o -> o.num));
                    return list.get(index + (int) k).num;
                }
            }
            ++index;
            --n;
        }
        return -1;
    }
}
