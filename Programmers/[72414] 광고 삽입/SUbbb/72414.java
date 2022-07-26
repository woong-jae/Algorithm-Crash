
class Solution {
    static int[] viewerCount;
    
    public String solution(String play_time, String adv_time, String[] logs) {
        int playT = stringToTime(play_time);
        int adT = stringToTime(adv_time);

        viewerCount = new int[playT];

        for (String log : logs) {
            String[] line = log.split("-");
            int startT = stringToTime(line[0]);
            int endT = stringToTime(line[1]);

            // 이상, 미만!
            // 각 구간별 초 단위 뷰어를 카운트
            for (int i = startT; i < endT; i++) 
                viewerCount[i]++;
        }

        // 초기값 설정 !
        // adT만큼의 구간 합을 구하고 이보다 큰 구간 합을 찾기 위해서
        long sum = 0;
        for (int i = 0; i < adT; i++)
            sum += viewerCount[i];
        long maxSum = sum;

        int startT = 0;
        for (int i = adT; i < playT; i++) {
            // adT 만큼의 구간을 확인하기 위해 제일 앞 값을 빼고, 더한다.
            int front = i - adT;
            sum -= viewerCount[front];
            sum += viewerCount[i];

            // 구간 합이 가장 큰 경우, 갱신
            if (sum > maxSum) {
                maxSum = sum;
                startT = front + 1;
            }
        }
        
        return timeToString(startT);
    }
    
    private static int stringToTime(String string) {
        String[] line = string.split(":");
        int time = 0;

        time += Integer.parseInt(line[0]) * 3600;
        time += Integer.parseInt(line[1]) * 60;
        time += Integer.parseInt(line[2]);

        return time;
    }

    private static String timeToString(int time) {
        StringBuilder sb = new StringBuilder();

        int h = time / 3600;
        if (h < 10) sb.append("0");
        sb.append(h).append(":");
        time %= 3600;

        int m = time / 60;
        if (m < 10) sb.append("0");
        sb.append(m).append(":");
        time %= 60;

        if (time < 10) sb.append("0");
        return sb.append(time).toString();
    }
}