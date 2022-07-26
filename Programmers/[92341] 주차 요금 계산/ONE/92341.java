import java.util.*;
import java.util.stream.Collectors;

class Solution {
    static class Time {
        int in;

        public Time(int in) {
            this.in = in;
        }

        public int calculate(int out) {
            return out - in;
        }
    }

    public int[] solution(int[] fees, String[] records) {
        Map<String, Time> parkingLot = new HashMap<>();
        Map<String, Integer> accumulateMap = new HashMap<>();

        for (String record : records) {
            StringTokenizer st = new StringTokenizer(record);
            int time = timeToInt(st.nextToken());
            String number = st.nextToken();
            String move = st.nextToken();

            if (move.equals("IN"))
                parkingLot.put(number, new Time(time));
            else {
                accumulateMap.put(number,
                        accumulateMap.getOrDefault(number, 0) + parkingLot.get(number).calculate(time));
                parkingLot.remove(number);
            }
        }

        for (String number : parkingLot.keySet())
            accumulateMap.put(number,
                    accumulateMap.getOrDefault(number, 0) + parkingLot.get(number).calculate(timeToInt("23:59")));

        int index = 0;
        int[] answer = new int[accumulateMap.size()];

        for(String number : accumulateMap.keySet().stream().sorted(Comparator.naturalOrder())
                .collect(Collectors.toCollection(LinkedHashSet::new)))
            answer[index++] = feeHandling(accumulateMap.get(number), fees);

        return answer;
    }

    private int timeToInt(String time) {
        String[] times = time.split(":");
        return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }

    private int feeHandling(int time, int[] fees) {
        if(time <= fees[0])
            return fees[1];
        return fees[1] + (int)Math.ceil((time - fees[0]) / (double) fees[2]) * fees[3];
    }
}
