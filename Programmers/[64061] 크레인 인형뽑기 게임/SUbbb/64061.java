import java.util.*;

class Solution {
    // 인형 담을 바구니
    Stack<Integer> basket = new Stack<>();
    
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        
        for (int move : moves) {
            int r = dollIdx(board, move - 1, board.length);
            
            // 인형이 없는 경우, 다음 move 확인
            if (r == -1) continue;
            
            // 바구니의 TOP과 집은 인형 비교, 같다면 바구니 pop, 다르면 집은 인형 push
            int doll = board[r][move - 1];
            if (basket.size() >= 1) {
                if (basket.peek() == doll) { 
                    basket.pop();
                    answer += 2;
                }
                else basket.push(doll);
            } else basket.push(doll);
            
            // 보드 인형 제거
            board[r][move - 1] = 0;
        }
        return answer;
    }
    
    private int dollIdx(int[][] board, int r, int c) {
        // 인형이 위치한 행을 반환하는 함수, 없으면 -1 반환
        for (int i = 0; i < c; i++) 
            if (board[i][r] != 0) return i;
        return -1;
    }
}