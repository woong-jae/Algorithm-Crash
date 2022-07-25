class Solution {
    private int maxDiff;
    private int n;
    private int[] lion;
    private int[] apeach;
    public int[] solution(int n, int[] info) {
        this.n = n;
        lion = new int[11];
        apeach = info;

        dfs(0, 0, new int[n]);

        return maxDiff == 0 ? new int[]{-1} : lion;
    }

    private void dfs(int start, int index, int[] combination) {
        if (index == n) {
            int[] tmpLion = new int[11];

            for(int score : combination)
                tmpLion[10 - score]++;

            int lionScore = 0;
            int apeachScore = 0;

            for (int i = 0; i < 11; i++) {
                int score = 10 - i;

                // 라이언이 이긴 경우
                if (tmpLion[i] > apeach[i])
                    lionScore += score;
                    // 어피치가 이긴경우
                else if (tmpLion[i] < apeach[i])
                    apeachScore += score;
                    // 둘 다 과녁에 1개이상 맞추고 동점일 경우
                else if (apeach[i] != 0 && tmpLion[i] == apeach[i])
                    apeachScore += score;
            }

            if (lionScore - apeachScore > maxDiff) {
                maxDiff = lionScore - apeachScore;
                lion = tmpLion;
            }
            return;
        }

        for (int i = start; i < 11; i++) {
            combination[index] = i;
            dfs(i, index + 1, combination);
        }
    }
}