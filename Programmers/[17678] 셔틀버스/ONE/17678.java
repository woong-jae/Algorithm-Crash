import java.util.*;

class Time implements Comparable<Time> {

    int hour;
    int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public int compareTo(Time o) {
        if(this.hour == o.hour)
            return this.minute - o.minute;
        return this.hour - o.hour;
    }

    public boolean canRide(Time bus) {
        if (this.hour == bus.hour)
            return this.minute <= bus.minute;
        return this.hour <= bus.hour;
    }

    public void nextBus(int t) {
        this.minute += t;

        if (minute >= 60) {
            hour++;
            minute %= 60;
        }
    }

    public void makeTime() {
        if (this.minute == 0) {
            this.minute = 59;
            this.hour--;
        }
        else
            this.minute--;
    }

    public String timeToString() {
        StringBuilder builder = new StringBuilder();

        if(this.hour / 10 < 1)
            builder.append("0");
        builder.append(this.hour).append(":");

        if(this.minute / 10 < 1)
            builder.append("0");
        builder.append(this.minute);

        return builder.toString();
    }
}

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        StringBuilder answer = new StringBuilder();
        Time bus = new Time(9, 0);
        Queue<Time> line = new PriorityQueue<>();

        for (String time : timetable) {
            String[] tokens = time.split(":");
            line.add(new Time(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
        }

        for (int i = 1; i <= n; i++) {
            List<Time> canRideList = new ArrayList<>();

            for (int j = 0; j < m; j++)
                if(!line.isEmpty() && line.peek().canRide(bus))
                    canRideList.add(line.poll());

            // 콘은 마지막 셔틀을 타야 가장 늦게 탈 수 있다
            if (i == n) {
                // 사람이 가득하면 콘이 마지막 사람의 시간에서 1분을 빼고 줄을 서야한다
                if (canRideList.size() == m) {
                    Time con = canRideList.get(m - 1);
                    con.makeTime();
                    return con.timeToString();
                }
                // 자리가 남으면 셔틀이 도착하는 시간으로 타면 가장 늦은 시간으로 탈 수 있다
                else
                    return bus.timeToString();
            }
            bus.nextBus(t);
        }
    }
}