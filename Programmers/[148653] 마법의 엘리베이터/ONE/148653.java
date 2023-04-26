class Solution {
    private final int UNIT = 10;
    public int solution(int storey) {
        int count = 0;
        while (storey > 0) {
            int numberOfCurrentUnit = storey % UNIT;
            int upperNumber = storey / UNIT % UNIT;

            if (numberOfCurrentUnit > 5 || numberOfCurrentUnit == 5 && upperNumber >= 5) {
                count += (10 - numberOfCurrentUnit);
                storey += (10 - numberOfCurrentUnit);
            } else {
                count += numberOfCurrentUnit;
            }

            storey /= UNIT;
        }
        return count;
    }
}