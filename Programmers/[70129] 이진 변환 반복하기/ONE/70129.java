class Solution {
    public int[] solution(String s) {
        int len;
        int[] answer = new int[2];

        while (!s.equals("1")) {
            len = s.codePoints()
                    .filter(c -> c == '1')
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .length();

            answer[0]++;
            answer[1] += s.length() - len;

            s = Integer.toBinaryString(len);
        }

        return answer;
    }
}