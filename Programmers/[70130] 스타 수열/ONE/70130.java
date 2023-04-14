import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] a) {
        Map<Integer, Long> candidate = Arrays.stream(a)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        int answer = 0;
        for (int key : candidate.keySet()) {
            if (candidate.get(key) <= answer) {
                continue;
            }

            int count = 0;
            for (int index = 0; index < a.length - 1; index++) {
                if (a[index] == a[index + 1]) {
                    continue;
                }
                if (a[index] != key && a[index + 1] != key) {
                    continue;
                }
                count++;
                index++;
            }
            answer = Math.max(answer, count);
        }
        return answer * 2;
    }

}