class Main {
    private static int answer; // 최소 횟수를 저장
    private static int destination; // 목적지 채널
    private static boolean[] buttons; // 고장난 숫자 버튼을 표현
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String channel = br.readLine();

        destination = Integer.parseInt(channel);
        buttons = new boolean[10];

        StringTokenizer st;
        if (!br.readLine().equals("0")) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens())
                buttons[Integer.parseInt(st.nextToken())] = true;
        } br.close();

        System.out.println(move(channel.length()));
    }

    private static int move(int n) {
        if (destination == 100)
            return 0;

        answer = Math.abs(destination - 100); // 기본값을 +, - 로만 이동하는 횟수로 설정

        dfs(0, n - 1, new StringBuilder()); // EX) 10000 이고 0, 1 버튼이 고장 났을 때 9999가 제일 최소 횟수
        dfs(0, n, new StringBuilder());
        dfs(0, n + 1, new StringBuilder()); // EX) 9999 이고 9 버튼이 고장 났을 때 10000이 제일 최소 횟수

        return answer;
    }

    private static void dfs(int depth, int n, StringBuilder sb) {
        if (depth >= n) {
            if (!sb.toString().equals(""))
                // (목적지 자릿수 n -> 숫자 버튼을 누른 수) + (채널 수의 차 -> +, -버튼을 누른 횟수)
                answer = Math.min(answer, n + Math.abs(destination - Integer.parseInt(sb.toString())));
            return;
        }

        for (int i = 0; i < 10; i++)
            if (!buttons[i]) {
                dfs(depth + 1, n, sb.append(i));
                sb.delete(sb.length() - 1, sb.length());
            }
    }
}