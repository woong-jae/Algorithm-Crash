import java.util.ArrayList;
import java.util.List;

class Stage {
    int num;
    int currentNum;
    int reachNum;
    float failureRate;

    public Stage(int num) {
        this.num = num;
        this.currentNum = 0;
        this.reachNum = 0;
    }

    public void setFailureRate() {
        if (reachNum == 0)
            this.failureRate = 0;
        else
            this.failureRate = (float)currentNum / reachNum;
    }
}

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        List<Stage> list = new ArrayList<>();

        for(int i = 1; i <= N + 1; i++)
            list.add(new Stage(i));

        for (int stage : stages) {
            for(int i = 1; i <= stage; i++)
                list.get(i - 1).reachNum++;
            list.get(stage - 1).currentNum++;
        }

        for(Stage stage : list)
            stage.setFailureRate();

        list.remove(N);

        list.sort((o1, o2) -> {
            if (o1.failureRate == o2.failureRate)
                return o1.num - o2.num;
            else if(o2.failureRate > o1. failureRate)
                return 1;
            else
                return -1;
        });

        for(int i = 0; i < N; i++)
            answer[i] = list.get(i).num;

        return answer;
    }
}