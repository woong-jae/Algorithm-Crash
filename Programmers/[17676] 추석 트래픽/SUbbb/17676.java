import java.util.*;

class Time {
    int startSec;
    int endSec;

    public Time(int startSec, int endSec) {
        this.startSec = startSec;
        this.endSec = endSec;
    }
}

class Solution {
    ArrayList<Time> times = new ArrayList<>();
    
    public int solution(String[] lines) {
        // 로그 개수가 1개면 무조건 1 반환
        if (lines.length == 1) return 1;
        
        for (String line : lines) {
            String[] times = line.substring(11).split(" ");
            String[] doneTime = times[0].split(":");
            int procTime = (int) (Double.parseDouble(times[1].replace("s", "")) * 1000);
            getTime(doneTime, procTime);
        }

        int max = 0;
        // 시작점이 걸쳐있는 경우와 끝점이 걸쳐있는 경우, time의 start와 end를 기점으로 1초 안에 몇개의 요청이 처리되는지 카운트
        for (Time time : times)
            max = Math.max(max, Math.max(findMaxRequest(time.startSec), findMaxRequest(time.endSec)));

        return max;
    }
    
    // 시작 시간과 종료 시간을 구해서 ArrayList에 추가
    private void getTime(String[] doneTime, int procTime) {
        int hour = Integer.parseInt(doneTime[0]);
        int min = Integer.parseInt(doneTime[1]);
        int sec = (int) (Double.parseDouble(doneTime[2]) * 1000);

        int endSec = hour * 1000 * 60 * 60 + min * 1000 * 60 + sec;
        int startSec = endSec - procTime + 1;

        times.add(new Time(startSec, endSec));
    }

    private int findMaxRequest(int startTime) {
        int count = 0;
        // start ~ end 사이에 있다면 count + 1;
        int endTime = startTime + 1000;

        for (Time time : times) {
            if (startTime <= time.startSec && time.startSec < endTime) {
                count++;
            } else if (startTime <= time.endSec && time.endSec < endTime) {
                count++;
            } else if (endTime <= time.endSec && time.startSec <= startTime) {
                count++;
            }
        }

        return count;
    }
}