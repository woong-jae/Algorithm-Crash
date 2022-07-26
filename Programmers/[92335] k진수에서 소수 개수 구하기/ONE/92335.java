class Solution {
    public int solution(int n, int k) {
        int answer = 0;

        String[] tokens = Integer.toString(n, k).replaceAll("0+", "0").split("0");

        for (String token : tokens)
            if (isPrime(Long.parseLong(token)))
                answer++;

        return answer;
    }

    private boolean isPrime(long num) {
        if (num == 1)
            return false;
        for (long i = 2; i < Math.sqrt(num); i++)
            if (num % i == 0)
                return false;
        return true;
    }
}