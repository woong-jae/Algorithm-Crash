import java.util.PriorityQueue;
import java.util.Queue;

class Music implements Comparable<Music> {
    int time;
    String melody;
    String title;

    public Music(String musicInfo) {
        String[] tokens = musicInfo.split(",");

        String[] startTime = tokens[0].split(":");
        String[] endTime = tokens[1].split(":");

        this.time = (Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]))
                - (Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]));

        this.title = tokens[2];

        String m = tokens[3];

        m = m.replace("C#", "c").replace("D#", "d")
                .replace("F#", "f").replace("G#", "g")
                .replace("A#", "a");

        int len = m.length();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < time; i++)
            builder.append(m.charAt(i % len));

        melody = builder.toString();
    }

    @Override
    public int compareTo(Music o) {
        return o.time - this.time;
    }
}

class Solution {
    public String solution(String m, String[] musicinfos) {
        Queue<Music> queue = new PriorityQueue<>();

        for (String musicInfo : musicinfos) {
            Music music = new Music(musicInfo);

            m = m.replace("C#", "c").replace("D#", "d")
                    .replace("F#", "f").replace("G#", "g")
                    .replace("A#", "a");

            if(music.melody.contains(m))
                queue.add(music);
        }

        if(!queue.isEmpty())
            return queue.poll().title;
        else
            return "(None)";
    }
}