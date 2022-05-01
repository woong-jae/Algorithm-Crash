
class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    // 각 엄지 위치
    Pair left = new Pair(3, 0);
    Pair right = new Pair(3, 2);
    // 누를 번호 위치
    Pair number = new Pair(0, 0);

    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();

        for (int n : numbers) {
            if (n == 1 || n == 4 || n == 7) {
                answer.append("L");
                left.x = n/3;
                left.y = 0;
            }
            else if (n == 3 || n == 6 || n == 9) {
                answer.append("R");
                right.x = n/3 - 1;
                right.y = 2;
            }
            else answer.append(thumb(hand, n));
        }

        return answer.toString();
    }

    // 2, 5, 8, 0에 대해서는 거리 계산과 잡이 정보가 필요
    private String thumb(String hand, int n) {
        if (n == 0) n = 11;

        // 현재 눌러야 하는 번호의 위치
        number.x = (n - 1)/3;
        number.y = (n - 1)%3;

        // 가까운 엄지를 찾기 위해 거리 계산
        int leftDist = Math.abs(number.x - left.x) + Math.abs(number.y - left.y);
        int rightDist = Math.abs(number.x - right.x) + Math.abs(number.y - right.y);

        // 차이가 같은 경우, 잡이 정보에 따라 반환
        if (leftDist == rightDist) {
            if (hand.equals("left")) {
                updateThumb(left);
                return "L";
            }
            updateThumb(right);
            return "R";
        } else {
            // 차이가 다른 경우
            if (leftDist < rightDist) {
                updateThumb(left);
                return "L";
            }
            updateThumb(right);
            return "R";
        }
    }

    private void updateThumb(Pair thumb) {
        thumb.x = number.x;
        thumb.y = number.y;
    }
}