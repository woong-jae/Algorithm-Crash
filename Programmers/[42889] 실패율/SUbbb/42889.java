import java.util.*;

class Stage implements Comparable<Stage> {
    int user;
    double fail;

    public Stage(int user) {
        this.user = user;
        this.fail = 0;
    }

    public void incFail() {
        this.fail += 1;
    }

    public void setFail(double fail) {
        this.fail = fail;
    }

    @Override
    public int compareTo(Stage o) {
        if (o.fail == this.fail) return this.user - o.user;
        if (o.fail > this.fail) return 1;
        return -1;
    }
}

class Solution {
    public int[] solution(int N, int[] stages) {
        double users = stages.length;
        ArrayList<Stage> userStage = new ArrayList<>();

        for (int i = 0; i < N; i++)
            userStage.add(new Stage(i + 1));

        for (int s : stages) {
            if (s > N) continue;
            userStage.get(s - 1).incFail();
        }

        for (Stage s : userStage) {
            if (users == 0) break;
            double tmp = s.fail / users;
            users -= s.fail;
            s.setFail(tmp);
        }

        Collections.sort(userStage);

        int[] answer = new int[N];
        for (int i = 0; i < N; i++)
            answer[i] = userStage.get(i).user;
        
        return answer;
    }
}