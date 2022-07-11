import java.util.*;

class Solution {
    
    // 유저별 신고당한 횟수 저장 map
    Map<String, Integer> reportMap = new HashMap<>();
    // 유저별 신고한 유저 저장 map
    Map<String, ArrayList<String>> userReportMap = new HashMap<>();
    // 유저별 메일 받을 횟수 저장 map
    Map<String, Integer> mailMap = new HashMap<>();
    
    public int[] solution(String[] id_list, String[] report, int k) {
        for (String rep : report) {
            String[] line = rep.split(" ");
            String id = line[0];
            String bannedId = line[1];

            ArrayList<String> list = null;
            // 유저별 신고한 유저 저장
            if (userReportMap.containsKey(id)) {
                list = userReportMap.get(id);
                if (!list.contains(bannedId)) {
                    list.add(bannedId);
                    reportMap.put(bannedId, reportMap.getOrDefault(bannedId, 0) + 1);
                }
            } else {
                list = new ArrayList<>();
                list.add(bannedId); 
                reportMap.put(bannedId, reportMap.getOrDefault(bannedId, 0) + 1);
            }
            userReportMap.put(id, list);
        }
        
        // 유저별 신고한 유저가 정지 기준을 넘은 횟수를 mailMap에 저장
        for (Map.Entry<String, ArrayList<String>> entry : userReportMap.entrySet()) {
            ArrayList<String> list = entry.getValue();
            for (String id : list)
                if (reportMap.get(id) >= k)
                    mailMap.put(entry.getKey(), mailMap.getOrDefault(entry.getKey(), 0) + 1);
        }
        
        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++)
            answer[i] = mailMap.getOrDefault(id_list[i], 0);
        return answer;
    }
}