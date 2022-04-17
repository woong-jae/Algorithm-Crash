import java.util.*;

class Solution {
    public int[] solution(String s) {
        String[] tokens = s.replace('{',' ').replace('}', ' ').trim().split(" , ");
        Arrays.sort(tokens, Comparator.comparingInt(String::length));

        ArrayList<Integer> list = new ArrayList<>();

        for (String token : tokens)
            for (String num : token.split(","))
                if(!list.contains(Integer.parseInt(num)))
                    list.add(Integer.parseInt(num));

        return list.stream().mapToInt(i -> i).toArray();
    }
}