import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int solution(String[] words) {
        int answer = 0, len = words.length;
        int[] lengths = new int[len];
        List<String> list = new ArrayList<>(List.of(words));

        list.sort(Comparator.naturalOrder());

        for (int i = 0; i < len - 1; i++) {
            int commonLength = commonStringLength(list.get(i), list.get(i + 1));
            lengths[i] = Math.max(lengths[i], commonLength);
            lengths[i + 1] = commonLength;
        }

        for (int i = 0; i < len; i++) {
            answer += lengths[i];

            if(lengths[i] != list.get(i).length())
                answer += 1;
        }

        return answer;
    }

    private int commonStringLength(String current, String next) {
        int minLength = Math.min(current.length(), next.length());
        int length = 0;

        for (int i = 0; i < minLength; i++) {
            length = i;
            if(current.charAt(i) != next.charAt(i))
                return length;
        }

        return length + 1;
    }
}