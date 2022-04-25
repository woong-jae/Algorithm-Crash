import java.util.HashMap;
import java.util.Map;

class Solution {
    static class Key {
        int r;
        int c;

        public Key(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public void moveFinger(Key key) {
            this.r = key.r;
            this.c = key.c;
        }

        public int getDistance(Key key) {
            return Math.abs(this.r - key.r) + Math.abs(this.c - key.c);
        }
    }

    static class Hand {
        Key leftFinger;
        Key rightFinger;
        String handed;

        public Hand(String handed) {
            this.leftFinger = new Key(3, 0);
            this.rightFinger = new Key(3, 2);
            this.handed = handed;
        }

        public boolean isRightHanded() {
            return this.handed.equals("right");
        }
    }

    static Map<Integer, Key> keypad = new HashMap<>();

    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        Hand h = new Hand(hand);

        setKeypad();

        for (int num : numbers)
            move(num, h, answer);

        return answer.toString();
    }

    private void setKeypad() {
        keypad.put(0, new Key(3, 1));
        keypad.put(1, new Key(0, 0));
        keypad.put(2, new Key(0, 1));
        keypad.put(3, new Key(0, 2));
        keypad.put(4, new Key(1, 0));
        keypad.put(5, new Key(1, 1));
        keypad.put(6, new Key(1, 2));
        keypad.put(7, new Key(2, 0));
        keypad.put(8, new Key(2, 1));
        keypad.put(9, new Key(2, 2));
    }

    private void move(int num, Hand hand, StringBuilder builder) {
        // Right Side
        if (num % 3 == 0 && num != 0) {
            hand.rightFinger.moveFinger(keypad.get(num));
            builder.append("R");
        }

        // Left Side
        else if (num % 3 == 1) {
            hand.leftFinger.moveFinger(keypad.get(num));
            builder.append("L");
        }

        // Center
        else {
            if (hand.rightFinger.getDistance(keypad.get(num))
                    < hand.leftFinger.getDistance(keypad.get(num))) {
                hand.rightFinger.moveFinger(keypad.get(num));
                builder.append("R");
            }

            else if (hand.rightFinger.getDistance(keypad.get(num))
                    > hand.leftFinger.getDistance(keypad.get(num))) {
                hand.leftFinger.moveFinger(keypad.get(num));
                builder.append("L");
            }

            else {
                if (hand.isRightHanded()) {
                    hand.rightFinger.moveFinger(keypad.get(num));
                    builder.append("R");
                }

                else {
                    hand.leftFinger.moveFinger(keypad.get(num));
                    builder.append("L");
                }
            }
        }
    }
}