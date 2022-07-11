class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        boolean[] nums = new boolean[46];
        int correctCount = 0;
        int zeroCount = 0;
        
        for (int lotto : lottos) {
            if (lotto == 0) zeroCount++;
            else nums[lotto] = true;
        }
        
        for (int win : win_nums)
            if (nums[win]) correctCount++;
        
        int max = getRanking(correctCount + zeroCount);
        int min = getRanking(correctCount);
        
        return new int[]{max, min};
    }
    
    private int getRanking(int count) {
        if (count <= 1) return 6;
        return 7 - count;
    }
}