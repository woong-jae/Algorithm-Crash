import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {
    public int solution(int k, int[] tangerine) {
        List<Long> groups = Arrays.stream(tangerine)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        int count = 0;
        for (long group : groups) {
            count++;
            if (k - group <= 0) {
                break;
            }
            k -= group;
        }
        return count;
    }
}