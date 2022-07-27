class Solution {
    public String solution(int n) {
        int num = n;
        int[] matching = {4, 1, 2};
        StringBuilder answer = new StringBuilder();

        while (num > 0) {
            answer.insert(0, matching[num % 3]);
            num /= 3;
            if (answer.charAt(0) == '4')
                num--;
        }
        return answer.toString();
    }
}