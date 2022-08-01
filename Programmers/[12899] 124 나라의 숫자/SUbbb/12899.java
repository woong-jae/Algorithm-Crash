class Solution {
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        
        while (n > 0) {
            int remain = n % 3;
            
            if (remain == 0) remain = 4;
            sb.append(remain);
            
            n = (n - 1) / 3;
        }
        
        return sb.reverse().toString();
    }
}