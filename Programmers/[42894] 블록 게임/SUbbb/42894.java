
class Board {
    int N;
    int[][] board;
    // 지울 수 있는 블록의 수
    int removableBlocks = 0;
    
    public Board(int[][] board) {
        this.N = board.length;
        this.board = board;
    }
    
    public void findRemovableBlock() {
        int count;
        
        // 더 이상 삭제 가능한 블록이 없을 때까지 진행
        do {
            count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 가로 또는 세로 범위의 직사각형 블록 여부 확인
                    if ((i <= N - 2 && j <= N - 3 && find(i, j, 2, 3)) || (i <= N - 3 && j <= N - 2 && find(i, j, 3, 2)))
                        count++;
                }
            }
            removableBlocks += count;
        } while(count != 0);
    }
    
    private boolean find(int x, int y, int h, int w) {
        // 빈 공간을 카운트, 빈 공간은 2개까지만 가능하다. 블록 범위의 크기는 6이고 블록의 크기는 4이기 때문
        int emptyCount = 0;
        // 빈 공간이 아닌 블록 번호를 저장, 빈 공간이 아닌 경우에는 이 값과 모두 동일해야 함
        int lastNum = -1;
        
        for (int i = x; i < x + h; i++) {
            for (int j = y; j < y + w; j++) {
                // 빈 공간인 경우
                if (board[i][j] == 0) {
                    if (!canFill(i, j)) return false;
                    if (++emptyCount > 2) return false;
                } else {
                    // 빈 공간이 아닌 블록 번호가 있고, 현재 영역과 다른 번호라면 같은 블록이 아니므로 종료
                    if (lastNum != -1 && lastNum != board[i][j]) return false;
                    lastNum = board[i][j];
                }
            }
        }
        
        removeBlock(x, y, h, w);
        
        return true;
    }
    
    // 해당 공간을 채울 수 있는지 확인하는 함수, 해당 행의 윗 행까지가 비어있는지 확인
    private boolean canFill(int x, int y) {
        for (int i = 0; i < x; i++)
            if (board[i][y] != 0) return false;
        
        return true;
    }
    
    // 블록 삭제
    private void removeBlock(int x, int y, int h, int w) {
        for (int i = x; i < x + h; i++)
            for (int j = y; j < y + w; j++)
                board[i][j] = 0;
    }
    
    public int getCount() {
        return removableBlocks;
    }
}

class Solution {
    public int solution(int[][] board) {
        Board b = new Board(board);
        
        b.findRemovableBlock();
        
        return b.getCount();
    }
}