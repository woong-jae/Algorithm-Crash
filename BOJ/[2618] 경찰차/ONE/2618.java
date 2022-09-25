import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, W;
    private static int[][] dp = new int[1002][1002];
    private static int[][] eventPosition = new int[1002][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer	st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());

        for(int i = 1; i <= W; i++) {
            st = new StringTokenizer(br.readLine());
            eventPosition[i][0] = Integer.parseInt(st.nextToken());
            eventPosition[i][1] = Integer.parseInt(st.nextToken());
        }
        sb.append(solution(1,0,0)).append("\n");

        int oneIdx = 0;
        int twoIdx = 0;
        for(int i = 1; i <= W; i++) {
            int oneDistance = distance(1, oneIdx, i);

            if(dp[oneIdx][twoIdx] - oneDistance == dp[i][twoIdx]) {
                oneIdx = i;
                sb.append(1).append("\n");
            }else {
                twoIdx = i;
                sb.append(2).append("\n");
            }
        }
        System.out.println(sb);
    }

    static int solution(int eventIdx, int oneIdx, int twoIdx) {
        if(eventIdx > W)
            return 0;

        if(dp[oneIdx][twoIdx] != 0)
            return dp[oneIdx][twoIdx];

        int oneMoveCount = solution(eventIdx+1, eventIdx, twoIdx) + distance(1, oneIdx, eventIdx);
        int twoMoveCount = solution(eventIdx+1, oneIdx, eventIdx) + distance(2, twoIdx, eventIdx);

        return dp[oneIdx][twoIdx] = Math.min(oneMoveCount, twoMoveCount);
    }

    static int distance(int type, int startIdx, int endIdx) {
        int[] startPosition = getStartPosition(type, startIdx);
        return Math.abs(startPosition[0] - eventPosition[endIdx][0]) + Math.abs(startPosition[1] - eventPosition[endIdx][1]);
    }

    static int[] getStartPosition(int type, int idx) {
        if(idx == 0) {
            if(type == 1)
                return new int[]{1,1};
            return new int[]{N, N};
        }
        return eventPosition[idx];
    }
}