import java.util.ArrayList;

class Log {
    int requestTime;
    int responseTime;

    public Log(int responseTime, int sec) {
        this.requestTime = responseTime - sec + 1;
        this.responseTime = responseTime;
    }
}

class Solution {
    public int solution(String[] lines){
        int answer = 0;
        ArrayList<Integer> timeList = new ArrayList<>();
        ArrayList<Log> logList = new ArrayList<>();

        for (String line : lines) {
            String[] tokens = line.split(" ");
            String[] timeLine = tokens[1].split(":");

            int hour = Integer.parseInt(timeLine[0]) * 1000 * 60 * 60;
            int minute = Integer.parseInt(timeLine[1]) * 1000 * 60;
            int second = (int) (Double.parseDouble(timeLine[2]) * 1000);
            int processingTime = (int) (Double.parseDouble(tokens[2].substring(0, tokens[2].length() - 1)) * 1000);

            Log log = new Log(hour + minute + second, processingTime);
            logList.add(log);
            timeList.add(log.requestTime);
            timeList.add(log.responseTime);
        }

        for (int time : timeList) {
            int left = time;
            int right = time + 999;
            int count = 0;

            for (Log log : logList) {
                if ((log.requestTime >= left && log.requestTime <= right)
                        || (log.responseTime >= left && log.responseTime <= right)
                        || (log.requestTime <= left && log.responseTime >= right))
                    count++;
            }
            answer = Math.max(answer, count);
        }

        return answer;
    }
}