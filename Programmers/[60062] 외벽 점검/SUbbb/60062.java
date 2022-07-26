class Solution {
    static int[] unrolledWeak;
    static boolean isFinish;
    static int[] weak;
    static int[] dist;
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int[] weak, int[] dist) {
        this.weak = weak;
        this.dist = dist;
        this.isFinish = false;
        int distLen = dist.length;

        unrollWeak(n, weak);

        // i개의 순열 만들기
        for (int i = 1; i <= distLen; i++)
            makeDistPerm(0, i, new boolean[distLen], new int[i]);
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    private void makeDistPerm(int depth, int count, boolean[] visited, int[] friends) {
        if (isFinish) return;

        if (depth == count) {
            canCheck(friends);
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i]) {
                friends[depth] = dist[i];
                visited[i] = true;
                makeDistPerm(depth + 1, count, visited, friends);
                visited[i] = false;
            }
        }
    }

    private void canCheck(int[] friends) {
        for (int i = 0; i < weak.length; i++) {
            int start = i;
            boolean canCheck = true;

            for (int idx = 0; idx < friends.length; idx++) {
                for (int j = i; j < i + weak.length; j++) {
                    // 두 점 간 거리가 friends로 뽑힌 친구가 가능한 거리보다 큰 경우는 점검 불가능
                    // 따라서 현재 지점을 시작점으로 지정
                    if (unrolledWeak[j] - unrolledWeak[start] > friends[idx]) {
                        start = j;
                        idx++;

                        // 현재 지점을 점검할 수 없는데, 더 점검할 친구가 없다면, 종료
                        if (idx == friends.length) {
                            canCheck = false;
                            break;
                        }
                    }
                }

                // 점검 완료라면, 더 이상 점검할 필요가 없다. 따라서 flag를 true 지정
                if (canCheck) {
                    answer = idx + 1;
                    isFinish = true;
                    return;
                }
            }
        }
    }

    // weak를 1차원으로 펼침
    private void unrollWeak(int n, int[] weak) {
        int len = weak.length;
        unrolledWeak = new int[len * 2 - 1];

        System.arraycopy(weak, 0, unrolledWeak, 0, len);

        for (int i = 0; i < len - 1; i++)
            unrolledWeak[i + len] = weak[i] + n;
    }
}