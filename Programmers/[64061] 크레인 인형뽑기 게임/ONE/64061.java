import java.util.Stack;

class Solution {
    // 1. 바구니에 담는 과정을 Stack 을 이용하여 구현
    // 2. 크레인을 이용해 해당 Col 의 제일 위에 있는 수를 뽑는 것을 구현
    //     2-1. 해당 Col 에 아무것도 없다면 실행하지 않음
    // 3. 크레인에서 뽑아온 수와 Stack 의 제일 위에 있는수를 비교
    //     3-1. 같다면 뽑아온 수를 Stack 에 넣지않고 Pop 한 숫자와 같이 Count
    //     3-2. 다르면 Pop 한 수를 다시 Stack 에 넣고 뽑아온 수도 스택에 넣어줌
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> basket = new Stack<>();

        for (int move : moves) {
            int top = topNum(board, move);
            if (top != 0) {
                // 만약 바구니가 비어있으면 top 넣고 continue
                if (basket.isEmpty()) {
                    basket.push(top);
                    continue;
                }

                int basketTop = basket.pop();

                if(top == basketTop)
                    answer += 2;

                else {
                    basket.push(basketTop);
                    basket.push(top);
                }
            }
        }
        return answer;
    }

    // Col 을 세로로 검사하여
    // 비어있지 않다면 0 이 아닌 제일 위에 있는 수를 0 으로 바꾼 후 반환
    // 비어있다면 0을 반환
    private int topNum(int[][] board, int col) {
        for (int[] row : board)
            if (row[col - 1] != 0){
                int topNum = row[col - 1];
                row[col - 1] = 0;
                return topNum;
            }
        return 0;
    }
}