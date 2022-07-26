import java.util.*;

class Log {
    boolean enter;
    String uid;

    public Log(boolean enter, String uid) {
        this.enter = enter;
        this.uid = uid;
    }
}

class Solution {
    public String[] solution(String[] record) {
        ArrayList<String> answer = new ArrayList<>();
        Map<String, String> user = new HashMap<>();
        Queue<Log> logs = new LinkedList<>();

        for (String r : record) {
            StringTokenizer st = new StringTokenizer(r);
            String request = st.nextToken();
            String uid = st.nextToken();
            String name;

            switch (request) {
                case "Enter":
                    name = st.nextToken();
                    user.put(uid, name);
                    logs.add(new Log(true, uid));
                    break;
                case "Leave":
                    logs.add(new Log(false, uid));
                    break;
                case "Change":
                    name = st.nextToken();
                    user.put(uid, name);
            }
        }

        while (!logs.isEmpty()) {
            Log log = logs.poll();
            StringBuilder sb = new StringBuilder();

            if(log.enter)
                sb.append(user.get(log.uid)).append("님이 들어왔습니다.");
            else
                sb.append(user.get(log.uid)).append("님이 나갔습니다.");

            answer.add(sb.toString());
        }

        return answer.toArray(new String[0]);
    }
}