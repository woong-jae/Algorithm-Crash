import java.util.*;

class Solution {
    private final Map<String, Integer> map = new HashMap<>();
    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> answer = new ArrayList<>();

        for (int num : course) {
            for (String order : orders)
                if (order.length() >= num) {
                    ArrayList<Character> list = new ArrayList<>();

                    for(int i = 0; i < order.length(); i++)
                        list.add(order.charAt(i));

                    Collections.sort(list);

                    combination(list, new boolean[list.size()], 0, list.size(), num);
                }

            ArrayList<String> keys = new ArrayList<>(map.keySet());
            keys.sort((v1, v2) -> (map.get(v2) - map.get(v1)));

            int maxCount = 0;

            for (String key : keys) {
                if(maxCount > map.get(key))
                    break;

                maxCount = map.get(key);

                if(maxCount >= 2)
                    answer.add(key);
            }
            map.clear();
        }

        Collections.sort(answer);

        return answer.toArray(new String[0]);
    }

    private void combination(ArrayList<Character> list, boolean[] visited, int depth, int n, int r) {
        if (r == 0) {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < visited.length; i++)
                if(visited[i])
                    builder.append(list.get(i));

            map.put(builder.toString(), map.getOrDefault(builder.toString(), 0) + 1);

            return;
        }

        if (depth == n) return;

        visited[depth] = true;
        combination(list, visited, depth + 1, n, r - 1);

        visited[depth] = false;
        combination(list, visited, depth + 1, n, r);
    }
}