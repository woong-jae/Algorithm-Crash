class Solution {
    public int solution(int n, int[] stations, int w) {
        int start = 1, boundary = 1 + w * 2, answer = 0;

        for (int station : stations) {
            int stationStart = station - w;
            if (stationStart > start)
                answer += countStation(stationStart - start, boundary);
            start = station + w + 1;
        }

        if (start <= n)
            answer += countStation(n - start + 1, boundary);

        return answer;
    }

    private int countStation(int n, int boundary) {
        int count = n / boundary;
        if (n % boundary != 0)
            count += 1;
        return count;
    }
}