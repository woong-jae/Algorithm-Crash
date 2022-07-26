import java.util.*;

class Shuttle {
    Queue<Integer> crueTimes;
    ArrayList<ArrayList<Integer>> busTimes;
    int n;
    int t;
    int m;
    int lastCrue;

    public Shuttle(int n, int t, int m) {
        this.n = n;
        this.t = t;
        this.m = m;
        busTimes = new ArrayList<>();
        crueTimes = new PriorityQueue<>();
        initBusTimes();
    }

    private void initBusTimes() {
        for (int i = 0; i < n; i++)
            busTimes.add(new ArrayList<>());
    }

    public void fillCrue(String[] timetable) {
        for (String tt : timetable) {
            String[] tmp = tt.split(":");
            crueTimes.add(Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]));
        }
    }

    public int rideBus() {
        int departBus = 540;

        for (int i = 0; i < n; i++) {
            while(!crueTimes.isEmpty()) {
                int crue = crueTimes.peek();

                if (departBus >= crue && busTimes.get(i).size() < m) {
                    crueTimes.poll();
                    busTimes.get(i).add(crue);
                    lastCrue = crue - 1;
                } else break;
            }
            departBus += t;
        }
        
        if (busTimes.get(n - 1).size() < m) 
            lastCrue = departBus - t;
        
        return lastCrue;
    }
}

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        Shuttle st = new Shuttle(n, t, m);

        st.fillCrue(timetable);

        StringBuilder answer = new StringBuilder();
        int time = st.rideBus();
        
        int hour = time/60;
        int min = time%60;
        
        if (hour < 10) answer.append("0");
        answer.append(hour).append(":");
        if (min < 10) answer.append("0");
        answer.append(min);
        
        return answer.toString();
    }
}