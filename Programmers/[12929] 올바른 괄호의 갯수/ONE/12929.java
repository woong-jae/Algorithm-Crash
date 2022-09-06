import java.util.*;

class Solution {
    private int answer = 0;
    public int solution(int n) {

        permutation(n, n, n, new ArrayList<>());

        return answer;
    }

    private void permutation(int n, int cntOpen, int cntClose, List<Integer> result) {
        if (cntOpen == 0 && cntClose == 0) {
            if (isRight(result))
                answer++;
            return;
        }
        if (cntOpen > 0) {
            result.add(1);
            permutation(n, cntOpen - 1, cntClose, result);
            result.remove(2 * n - cntClose - cntOpen);
        }
        if (cntClose > 0) {
            result.add(2);
            permutation(n, cntOpen, cntClose - 1, result);
            result.remove(2 * n - cntClose - cntOpen);
        }
    }

    private boolean isRight(List<Integer> result) {
        Stack<Integer> stack = new Stack<>();
        for (int num : result) {
            if (num == 1)
                stack.add(1);
            else {
                if (stack.isEmpty() || stack.peek() != 1)
                    return false;
                stack.pop();
            }
        }
        return true;
    }
}