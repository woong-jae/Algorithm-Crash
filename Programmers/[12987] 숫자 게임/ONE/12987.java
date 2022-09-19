import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0, N = A.length;

        Arrays.sort(A);
        Arrays.sort(B);

        for (int indexA = N - 1, indexB = N - 1; indexA >= 0; indexA--)
            if (B[indexB] > A[indexA]){
                answer++;
                indexB--;
            }
        return answer;
    }
}