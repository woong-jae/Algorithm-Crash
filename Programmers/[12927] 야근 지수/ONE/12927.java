import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public long solution(int n, int[] works) {

        Queue<Integer> remainWorks = new PriorityQueue<>(Comparator.reverseOrder());

        for (int work : works) {
            remainWorks.add(work);
        }

        while (n-- > 0) {
            int work = remainWorks.poll();
            remainWorks.add(--work);
        }

        return remainWorks.stream()
                .filter(work -> work > 0)
                .mapToLong(work -> (long) work * work)
                .sum();
    }
}