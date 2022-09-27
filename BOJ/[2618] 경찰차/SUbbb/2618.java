import java.io.*;
import java.util.*;

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N;
    static int T;
    static int[][] dp = new int[1001][1001];
    static List<Pair> pathA = new ArrayList<>();
    static List<Pair> pathB = new ArrayList<>();
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        T = Integer.parseInt(br.readLine());

        for (int[] d : dp) Arrays.fill(d, -1);

        pathA.add(new Pair(1, 1));
        pathB.add(new Pair(N, N));

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Pair p = new Pair(x, y);
            pathA.add(p);
            pathB.add(p);
        }

        System.out.println(process(0, 0));

        findPath(0, 0);
        System.out.println(stringBuilder.toString());
    }

    // A는 경찰차 1, B는 경찰차 2를 의미합니다.
    private static int process(int A, int B) {
        // A, B중 마지막 사건 번호를 저장하고 있는, 즉 마지막 사건까지 처리한 경우 종료합니다.
        if (A == T || B == T) return 0;

        // A가 처리한 사건과 B가 처리한 사건의 최소 거리
        int result = dp[A][B];

        // 이미 처리된 경우?
        if (result != -1) return result;

        // A와 B는 각각 사건 번호를 저장하고 있기 때문에 둘 중 최댓값의 + 1이 다음 사건 번호입니다.
        int nextT = Math.max(A, B) + 1;

        // A가 다음 사건을 처리하는 경우
        int distA = computeDist(pathA.get(nextT).x, pathA.get(nextT).y, pathA.get(A).x, pathA.get(A).y);
        // B가 다음 사건을 처리하는 경우
        int distB = computeDist(pathB.get(nextT).x, pathB.get(nextT).y, pathB.get(B).x, pathB.get(B).y);

        // A가 다음 사건을 맡은 경우의 거리
        int retA = process(nextT, B) + distA;
        // B가 다음 사건을 맡은 경우의 거리
        int retB = process(A, nextT) + distB;

        result = Math.min(retA, retB);
        dp[A][B] = result;

        return result;
    }

    private static void findPath(int A, int B) {
        if (A == T || B == T) return;

        // A와 B는 각각 사건 번호를 저장하고 있기 때문에 둘 중 최댓값의 + 1이 다음 사건 번호입니다.
        int nextT = Math.max(A, B) + 1;

        // A가 다음 사건을 처리하는 경우
        int distA = computeDist(pathA.get(nextT).x, pathA.get(nextT).y, pathA.get(A).x, pathA.get(A).y);
        // B가 다음 사건을 처리하는 경우
        int distB = computeDist(pathB.get(nextT).x, pathB.get(nextT).y, pathB.get(B).x, pathB.get(B).y);

        // A가 다음 사건을 맡은 경우의 거리
        int retA = process(nextT, B) + distA;
        // B가 다음 사건을 맡은 경우의 거리
        int retB = process(A, nextT) + distB;

        if (retA > retB) {
            // B가 처리한 경우 더 최소 거리입니다.
            stringBuilder.append(2).append('\n');
            findPath(A, nextT);
        } else {
            stringBuilder.append(1).append('\n');
            findPath(nextT, B);
        }
    }

    private static int computeDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}