import java.util.*;

class Solution {
    private final Map<String, List<Integer>> map = new HashMap<>();
    public int[] solution(String[] info, String[] query) {
        int n = query.length;
        int[] answer = new int[n];

        for (String information : info)
            makeStringCases(0, new boolean[4], makeList(information));

        for (List<Integer> list : map.values())
            Collections.sort(list);

        for (int i = 0; i < n; i++) {
            query[i] = query[i].replaceAll(" and ", "");
            String[] tokens = query[i].split(" ");
            String key = tokens[0];
            int score = Integer.parseInt(tokens[1]);

            answer[i] = map.containsKey(key) ? binarySearch(map.get(key), score) : 0;
        }
        return answer;
    }

    private List<String> makeList(String info) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(info);

        while (st.hasMoreTokens())
            list.add(st.nextToken());

        return list;
    }

    private String makeString(boolean[] check, List<String> list) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            if(check[i])
                sb.append(list.get(i));
            else
                sb.append("-");
        }

        return sb.toString();
    }

    private void makeStringCases(int depth, boolean[] check, List<String> list) {
        if (depth == 4) {
            String result = makeString(check, list);
            int score = Integer.parseInt(list.get(4));

            if(!map.containsKey(result))
                map.put(result, new ArrayList<>());

            map.get(result).add(score);

            return;
        }

        check[depth] = true;
        makeStringCases(depth + 1, check, list);
        check[depth] = false;
        makeStringCases(depth + 1, check, list);
    }

    private int binarySearch(List<Integer> list, int score) {
        int start = 0, end = list.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if(list.get(mid) < score)
                start = mid + 1;
            else
                end = mid - 1;
        }

        return list.size() - start;
    }
}