import java.util.*;

class Solution {
    
    // 언어, 직군, 경력, 소울푸드를 key로 가지고, 점수 리스트를 값으로 가지는 map 선언
    static Map<String, ArrayList<Integer>> scoreMap = new HashMap<>();

    // 조합 생성을 위해 사용할 배열, 코테 점수, 지원자의 정보 배열
    static String[] combStr;
    static int infoScore;
    static String[] infoLine;
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        // 주어진 info 배열을 가지고 scoreMap 초기화
        // -를 포함한 모든 조합을 생성하여 map의 key로 추가
        initScoreMap(info);

        // 이진탐색을 위해 점수 리스트를 오름차순으로 정렬
        for (String key : scoreMap.keySet())
            Collections.sort(scoreMap.get(key));

        for (int i = 0; i < query.length; i++) {
            String[] line = query[i].split(" and | ");
            StringBuilder sb = new StringBuilder(line[0] + line[1] + line[2] + line[3]);
            String key = sb.toString();
            int score = Integer.parseInt(line[4]);

            if (scoreMap.containsKey(key)) {
                ArrayList<Integer> list = scoreMap.get(key);
                answer[i] = list.size() - findLower(list, score);
            }
        }
        
        return answer;
    }
    
    private static void initScoreMap(String[] info) {
        for (String i : info) {
            combStr = new String[4];
            infoLine = i.split(" ");
            infoScore = Integer.parseInt(infoLine[4]);
            makeComb(0);
        }
    }

    // -를 붙이면서 가능한 조합을 생성하여 map에 추가한다.
    private static void makeComb(int length) {
        if (length == 4) {
            String key = String.join("", combStr);
            scoreMap.putIfAbsent(key, new ArrayList<>());
            scoreMap.get(key).add(infoScore);
        } else {
            // -가 아닌 경우와 -인 경우로 나눠 가능한 조합 생성
            combStr[length] = infoLine[length];
            makeComb(length + 1);
            combStr[length] = "-";
            makeComb(length + 1);
        }
    }

    private static int findLower(ArrayList<Integer> list, int value) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int v = list.get(mid);

            if (v < value) left = mid + 1;
            else right = mid - 1;
        }

        return left;
    }
}