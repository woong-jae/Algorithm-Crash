import java.util.*;

class Solution {
    
    static int basicTime;
    static int basicFee;
    static int unitTime;
    static int unitFee;
    
    // 차 번호별 입차 시간을 저장하는 map
    static Map<String, Integer> carTimeMap = new HashMap<>();
    // 차 번호별 누적 주차 시간을 저장하는 map
    static Map<String, Integer> accumCarMap = new HashMap<>();
    
    public int[] solution(int[] fees, String[] records) {
        init(fees);

        readRecords(records);

        processRemainingCars();

        return calculateFees();
    }

    private static void init(int[] fees) {
        basicTime = fees[0];
        basicFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];
    }

    private static void readRecords(String[] records) {
        for (String record : records) {
            String[] info = record.split(" ");
            int time = timeToInt(info[0]);
            String carN = info[1];
            boolean in = Objects.equals(info[2], "IN");

            // 입차한 경우
            if (in) carTimeMap.put(carN, time);
            // 출차한 경우
            else {
                // 입차 시간 가져옴
                int inTime = carTimeMap.get(carN);
                // 주차 시간 계산
                time -= inTime;
                
                accumCarMap.put(carN, accumCarMap.getOrDefault(carN, 0) + time);
                carTimeMap.remove(carN);
            }
        }
    }

    private static int timeToInt(String info) {
        String[] line = info.split(":");
        int hour = Integer.parseInt(line[0]) * 60;
        int min = Integer.parseInt(line[1]);

        return hour + min;
    }

    // 출차 기록이 없는 차 처리
    private static void processRemainingCars() {
        for (Map.Entry<String, Integer> entry : carTimeMap.entrySet()) {
            String carN = entry.getKey();
            int time = 1439 - entry.getValue();
            
            accumCarMap.put(carN, accumCarMap.getOrDefault(carN, 0) + time);
        }
    }

    // 차 번호별 누적 주차 시간을 이용해 주차 요금 계산
    private static int[] calculateFees() {
        // 차 번호 오름차순으로 정렬
        Object[] keys = accumCarMap.keySet().toArray();

        Arrays.sort(keys);
        
        int[] answer = new int[accumCarMap.size()];
        int idx = 0;
        
        // 정렬된 차 번호 순으로 누적 요금 계산
        for (Object key : keys) {
            int time = accumCarMap.get(key.toString());
            int fee = basicFee;
            
            if (time > basicTime) {
                time = (int) Math.ceil((double) (time - basicTime) / unitTime);
                fee += (time * unitFee);
            }
            
            answer[idx] = fee;
            idx++;
        }
        
        return answer;
    }
}