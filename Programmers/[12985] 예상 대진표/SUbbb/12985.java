class Solution {
    public int solution(int n, int a, int b) {
        int answer = 0;
        int nA = (a + 1) >> 1;
        int nB = (b + 1) >> 1;
        
        while (nA != nB) {
            nA = (nA + 1) >> 1;
            nB = (nB + 1) >> 1;
            answer++;
        }

        return answer + 1;
    }
}