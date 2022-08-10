class Solution {
    public int solution(String s) {
        int answer = 0, n = s.length();

        for (int x = 0; x < n; x++)
            answer += check(n, x, s) ? 1 : 0;

        return answer;
    }

    private boolean check(int n, int x, String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = x; i < n + x; i++) {
            char c = s.charAt(i % n);
            switch (c) {
                case '[':
                case '{':
                case '(':
                    stack.push(c); break;
                case ']':
                    if (stack.isEmpty() || stack.peek() != '[')
                        return false;
                    else
                        stack.pop(); break;
                case '}':
                    if (stack.isEmpty() || stack.peek() != '{')
                        return false;
                    else
                        stack.pop(); break;
                case ')':
                    if (stack.isEmpty() || stack.peek() != '(')
                        return false;
                    else
                        stack.pop(); break;
            }
        }
        return stack.isEmpty();
    }
}