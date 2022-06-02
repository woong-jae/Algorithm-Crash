import java.util.*;

class Solution {
    // 후보키들을 저장할 ArrayList
    static ArrayList<HashSet<Integer>> candidateKeys = new ArrayList<>();
    
    public int solution(String[][] relation) {
        int n = relation[0].length;
        
        // 1개부터 n개까지의 후보키 생성 시도
        for (int i = 0; i < n; i++)
            combination(0, 0, i + 1, new HashSet<>(), n, relation);
        
        return candidateKeys.size();
    }
    
    private void combination(int idx, int start, int keySize, HashSet<Integer> keys, int n, String[][] relation) {
        if (idx == keySize) {
            // 이미 뽑은 후보키라면 제외(유일성, 최소성 검사), keys에 key가 포함된다면 최소성 만족 X
            for (HashSet<Integer> key : candidateKeys)
                if (keys.containsAll(key)) return;

            // 뽑은 후보키의 인덱스를 확인하여 유일성을 확인할 set
            HashSet<String> spareKeys = new HashSet<>();

            for (String[] rel : relation) {
                StringBuilder sb = new StringBuilder();

                // 선택(keys에 추가)한 속성 체크
                for (int k : keys)
                    sb.append(rel[k]);

                spareKeys.add(sb.toString());
            }

            // map의 크기와 relation의 길이가 같다면 모든 튜플에 대해서 중복된 값이 없다는 의미이므로 후보키가 될 수 있음
            if (spareKeys.size() == relation.length)
                candidateKeys.add(keys);
        } else {
            for (int i = start; i < n; i++) {
                HashSet<Integer> selectedKeys = new HashSet<>(keys);
                selectedKeys.add(i);
                combination(idx + 1, i + 1, keySize, selectedKeys, n, relation);
            }
        }
    }
}