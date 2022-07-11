import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int n = id_list.length;
        int[] answer = new int[n];
        Map<String, Set<String>> reportedMap = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();

        for (String s : report) {
            StringTokenizer st = new StringTokenizer(s);
            String user = st.nextToken();
            String reported = st.nextToken();

            if(!reportedMap.containsKey(reported))
                reportedMap.put(reported, new HashSet<>());
            reportedMap.get(reported).add(user);
        }

        for (String s : reportedMap.keySet())
            if (reportedMap.get(s).size() >= k)
                for (String user : reportedMap.get(s))
                    countMap.put(user, countMap.getOrDefault(user, 0) + 1);

        for (int i = 0; i < n; i++) {
            if(!countMap.containsKey(id_list[i]))
                continue;
            answer[i] = countMap.get(id_list[i]);
        }
        return answer;
    }
}