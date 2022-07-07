class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String[] nums = convertToK(n, k).split("0");
        
        for (String num : nums) {
            if (num.equals("") || num.equals("1")) continue;
            if (isPrime(Long.parseLong(num))) answer++;
        }
        
        return answer;
    }
    
    private static String convertToK(int num, int k) {
        StringBuilder sb = new StringBuilder();

        while(num > 0) {
            sb.append(num % k);
            num /= k;
        }

        return sb.reverse().toString();
    }

    private static boolean isPrime(long n) {
        for (int i = 2; i <= (int) Math.sqrt(n); i++)
            if (n % i == 0)
                return false;

        return true;
    }
}