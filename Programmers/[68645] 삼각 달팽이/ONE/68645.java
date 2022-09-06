import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    private int num, end, status;
    private int upBound, downBound, rightBound;
    private int currentX, currentY;
    private int[][] triangle;
    public int[] solution(int n) {
        num = status = 1;
        end = IntStream.rangeClosed(1, n).sum();
        upBound = -1;
        downBound = n - 1;
        rightBound = n + 1;
        triangle = new int[n][n];

        while (num <= end) {
            switch (status) {
                case 0:
                    up(); break;
                case 1:
                    down(); break;
                case 2:
                    right();
            }
        }

        return Arrays.stream(triangle)
                .flatMapToInt(Arrays::stream)
                .filter(i -> i > 0)
                .toArray();
    }

    private void up() {
        for (int i = currentX, j = currentY; i >= upBound; i--, j--) {
            triangle[i][j] = num++;
            currentY = j;
        }
        downBound--;
        currentX = upBound + 1;
        status = 1;
    }

    private void down() {
        for (int i = currentX; i <= downBound; i++)
            triangle[i][currentY] = num++;
        rightBound -= 2;
        currentX = downBound;
        currentY++;
        status = 2;
    }

    private void right() {
        for (int i = currentY; i <= rightBound; i++)
            triangle[currentX][i] = num++;
        upBound += 2;
        currentX--;
        currentY = rightBound - 1;
        status = 0;
    }
}