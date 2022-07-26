import java.util.*;

class JustNowMusic {
    // Map의 순서 보장을 위해 LinkedHashMap 사용
    LinkedHashMap<String, String> music;

    public JustNowMusic(String[] musicinfos) {
        music = new LinkedHashMap<>();

        for (String mi : musicinfos) initMusic(mi);
    }

    private void initMusic(String mi) {
        String[] info = mi.split(",");

        int runTime = parsing(info[0], info[1]);

        StringBuilder score = new StringBuilder();

        info[3] = convert(info[3]);
        
        // 재생 시간만큼 악보 반복
        for (int i = 0, idx = 0; i < runTime; i++) {
            if (i % info[3].length() == 0) idx = 0;
            score.append(info[3].charAt(idx));
            idx++;
        }

        // 제목과 악보를 Map에 저장
        music.put(info[2], score.toString());
    }
    
    // #이 들어가는 음 치환
    private String convert(String s) {
        return s.replace("C#", "c").replace("D#", "d").replace("F#", "f").replace("G#", "g").replace("A#", "a");
    }

    private int parsing(String info1, String info2) {
        int startTime = getTime(info1);
        int endTime = getTime(info2);

        return endTime - startTime;
    }

    private int getTime(String info) {
        String[] time = info.split(":");
        int hour = Integer.parseInt(time[0]);
        int min = Integer.parseInt(time[1]);
        
        return hour * 60 + min;
    }

    public String findMusic(String m) {
        m = convert(m);
        int maxPlayTime = -1;
        
        String answer = "";
        for (Map.Entry<String, String> entry : music.entrySet()) {
            String score = entry.getValue();
            int playTime = score.length();
            
            // 기억한 멜로디를 포함하고, 재생 시간이 제일 긴 음악인 경우 answer에 해당 제목 저장
            if (score.contains(m) && playTime > maxPlayTime) {
                answer = entry.getKey();
                maxPlayTime = playTime;
            }
        }

        return maxPlayTime == -1? "(None)" : answer;
    }
}

class Solution {
    public String solution(String m, String[] musicinfos) {
        JustNowMusic jnm = new JustNowMusic(musicinfos);
        
        return jnm.findMusic(m);
    }
}