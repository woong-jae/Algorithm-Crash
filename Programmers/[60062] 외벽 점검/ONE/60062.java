class Solution {
    public int n, answer;
    public int[] weak, dist;
    public int[][] rotateWeak;
    public boolean[] visit;
    public int solution(int n, int[] weak, int[] dist) {
        answer = Integer.MAX_VALUE;
        this.n = n;
        this.weak = weak;
        this.dist = dist;
        rotateWeak = new int[weak.length][weak.length];
        visit = new boolean[dist.length];

        rotate();

        for (int i = 1; i <= weak.length; i++)
            permutation(0, i, "");

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    public void rotate() {
        int len = weak.length;

        for(int j = 0; j < len; j++) {
            int[] rotation = new int[len];
            int index = j;

            for(int i = 0; i < len; i++) {
                rotation[i] = weak[index % len];

                if(index >= len)
                    rotation[i] += n;
                index++;
            }
            rotateWeak[j] = rotation;
        }
    }

    private void permutation(int count, int length, String s) {
        if (count == length) {
            check(s.trim().split(" "));
            return;
        }

        for(int i = 0; i < dist.length; i++)
            if(!visit[i]) {
                visit[i] = true;
                permutation(count + 1, length, s + dist[i] + " ");
                visit[i] = false;
            }
    }

    private void check(String[] s) {
        int[] friends = new int[s.length];

        for(int i = 0; i < s.length; i++)
            friends[i] = Integer.parseInt(s[i]);

        for (int[] rotation : rotateWeak) {
            int index = 0;
            int start = rotation[index];

            for (int friend : friends) {
                while (index < rotateWeak.length && rotation[index] - start <= friend)
                    index++;
                if (index >= rotateWeak.length)
                    answer = Math.min(answer, friends.length);
                else
                    start = rotation[index];
            }
        }
    }
}