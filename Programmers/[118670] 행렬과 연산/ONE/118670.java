import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    static class Matrix {
        private final int r;
        private final int c;
        Deque<Deque<Integer>> centerRows;
        Deque<Integer> leftCol;
        Deque<Integer> rightCol;

        public Matrix(int[][] rc) {
            centerRows = new ArrayDeque<>();
            leftCol = new ArrayDeque<>();
            rightCol = new ArrayDeque<>();

            this.r = rc.length;
            this.c = rc[0].length;

            for (int[] row : rc) {
                leftCol.addLast(row[0]);
                rightCol.addLast(row[c - 1]);

                Deque<Integer> centerRow = new ArrayDeque<>();
                for (int colIndex = 1; colIndex < c - 1; colIndex++) {
                    centerRow.addLast(row[colIndex]);
                }
                centerRows.addLast(centerRow);
            }
        }

        public void rotate() {
            centerRows.peekFirst().addFirst(leftCol.pollFirst());
            rightCol.addFirst(centerRows.peekFirst().pollLast());
            centerRows.peekLast().addLast(rightCol.pollLast());
            leftCol.addLast(centerRows.peekLast().pollFirst());
        }

        public void shiftRow() {
            rightCol.addFirst(rightCol.pollLast());
            centerRows.addFirst(centerRows.pollLast());
            leftCol.addFirst(leftCol.pollLast());
        }

        public int[][] toArray() {
            int[][] matrix = new int[this.r][this.c];

            for (int rowIndex = 0; rowIndex < r; rowIndex++) {
                matrix[rowIndex][0] = leftCol.pop();
                matrix[rowIndex][c - 1] = rightCol.pop();

                Deque<Integer> centerRow = centerRows.pop();
                for (int colIndex = 1; colIndex < c - 1; colIndex++) {
                    matrix[rowIndex][colIndex] = centerRow.pop();
                }
            }
            return matrix;
        }
    }

    public int[][] solution(int[][] rc, String[] operations) {
        Matrix matrix = new Matrix(rc);
        for (String operation : operations) {
            switch (operation) {
                case "Rotate":
                    matrix.rotate();
                    break;
                case "ShiftRow":
                    matrix.shiftRow();
            }
        }
        return matrix.toArray();
    }
}