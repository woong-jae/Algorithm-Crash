import java.util.*;

class Solution {
    private int len;
    private int[] cookie;
    private int answer = 0;
    
    public int solution(int[] cookie) { 
        len = cookie.length;
        this.cookie = cookie;
        
        // m이 첫째와 둘째 구분!
        for (int m = 0; m < len - 1; m++)
            // m을 기준으로 첫째와 둘째가 가지는 쿠키를 계산
            getCookie(m);
        
        return answer;
    }
    
    private void getCookie(int m) {
        int fIdx = m;
        int sIdx = m + 1;
        
        int first = cookie[fIdx];
        int second = cookie[sIdx];
        
        while (true) {
            // 둘의 쿠키 개수가 같고, 이전에 구한 최댓값보다 크면 갱신
            if (first == second && answer < first) answer = first;
            // 둘째의 쿠키가 더 많거나 같고, 아직 첫째에게 사줄 쿠키가 있다면 누적!
            else if (first <= second && fIdx != 0) first += cookie[--fIdx];
            // 첫째의 쿠키가 더 많거나 같고, 아직 둘째에게 사줄 쿠키가 있다면 누적!
            else if (first >= second && sIdx != len - 1) second += cookie[++sIdx];
            else break;
        }
    }
}