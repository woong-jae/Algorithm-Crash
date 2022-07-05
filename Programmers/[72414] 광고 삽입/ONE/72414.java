class Solution {

    class Log {
        int start;
        int end;

        public Log(String log) {
            String[] times = log.split("-");
            this.start = stringToTime(times[0]);
            this.end = stringToTime(times[1]);
        }

        public void accumulate(int[] window) {
            for(int i = start; i < end; i++)
                window[i]++;
        }
    }

    public String solution(String play_time, String adv_time, String[] logs) {
        int play = stringToTime(play_time);
        int adv = stringToTime(adv_time);
        int[] window = new int[play];

        if(play == adv)
            return timeToString(0);

        for (String log : logs)
            new Log(log).accumulate(window);

        return timeToString(findMax(play, adv, window));
    }

    private int stringToTime(String str) {
        String[] times = str.split(":");
        int hour = Integer.parseInt(times[0]) * 3600;
        int minute = Integer.parseInt(times[1]) * 60;
        int second  = Integer.parseInt(times[2]);

        return hour + minute + second;
    }

    private String timeToString(int time) {
        return String.format("%02d", time / 3600) + ":" +
                String.format("%02d", time % 3600 / 60) + ":" +
                String.format("%02d", time % 3600 % 60);
    }

    private int findMax(int play, int adv, int[] window) {
        int index = 0;
        long max = 0, current = 0;

        for(int i = 0; i < adv; i++)
            max += window[i];

        current = max;

        for (int i = 0, j = adv; j < play; i++, j++) {
            current = current - window[i] + window[j];
            if (current > max) {
                index = i + 1;
                max = current;
            }
        }
        return index;
    }
}