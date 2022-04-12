import java.util.*;

class Solution {
    public int[] solution(String s) {
        // 반환을 위한 값들을 임시로 저장할 ArrayList
        ArrayList<Integer> answer = new ArrayList<>();
        // 주어진 String을 집합의 원소 단위로 저장할 ArrayList
        ArrayList<String> list = new ArrayList<>();
        // 먼저, 주어진 String을 "{ }" 단위로 분리, "[2]", "[]" 와 같은 형태로 저장
        StringTokenizer token = new StringTokenizer(s, "{}");
        
        while (token.hasMoreTokens()) {
            // 자른 token들을 다시 "[]와 ," 단위로 분리해 최종적으로 "2, 1, 3", "2" , "2, 1" 와 같은 형태로 저장
            String tmp = Arrays.toString(token.nextToken().split(",")).replace("[", "").replace("]", "");
            if (tmp.length() != 0) list.add(tmp);
        }
        
        // 저장한 문자열 배열을 각 문자열의 길이 기준으로 오름차순 정렬
        list.sort(Comparator.comparingInt(String::length));
        
        while(!list.isEmpty()) {
            // 각 문자열 원소들을 int형으로 변환
            int[] tmp = Arrays.stream(list.get(0).replace(" ","").split(",")).mapToInt(Integer::parseInt).toArray();
            
            // 튜플을 저장할 answer에 해당 원소가 없다면 추가(중복이 없기에)
            for (int i : tmp)
                if (!answer.contains(i)) answer.add(i);
            
            list.remove(0);
        }
        
        // int[]로 ArrayList를 변환
        return answer.stream().mapToInt(i -> i).toArray();
    }
}