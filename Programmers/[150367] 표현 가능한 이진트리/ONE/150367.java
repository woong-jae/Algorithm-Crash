import java.util.Arrays;

class Solution {
    public int[] solution(long[] numbers) {
        return Arrays.stream(numbers)
                .mapToObj(this::getBinaryString)
                .mapToInt(number -> divideConquer(number, 0, number.length() - 1))
                .toArray();
    }

    private int divideConquer(String number, int start, int end) {
        if (start >= end) {
            return 1;
        }
        int mid = (start + end) / 2;
        if (number.charAt(mid) == '0'
                && ((number.charAt((start + mid - 1) / 2) == '1' || number.charAt((mid + 1 + end) / 2) == '1'))) {
            return 0;

        }
        return divideConquer(number, start, mid - 1) & divideConquer(number, mid + 1, end);
    }

    private String getBinaryString(long number) {
        String binaryString = Long.toBinaryString(number);
        StringBuilder sb = new StringBuilder();
        int length = binaryString.length();
        for (int i = 0; i <= 5; i++) {
            if (length >= Math.pow(2, i) - 1 && length <= Math.pow(2, i + 1) - 1) {
                sb.append("0".repeat((int) (Math.pow(2, i + 1) - 1 - length)));
                break;
            }
        }
        return sb.append(binaryString).toString();
    }
}