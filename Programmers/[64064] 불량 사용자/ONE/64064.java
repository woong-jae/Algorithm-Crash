import java.util.*;

class Solution {
    private String[] banned;
    public int solution(String[] user_id, String[] banned_id) {
        Set<String> set = new HashSet<>();

        banned = Arrays.stream(banned_id).map(b -> b.replace("*", ".")).toArray(String[]::new);

        solve(set, 0, new boolean[user_id.length], user_id);

        return set.size();
    }

    private void solve(Set<String> set, int idx, boolean[] visit, String[] user_id) {
        if (idx == banned.length) {
            StringBuilder builder = new StringBuilder();

            for(int i = 0; i < user_id.length; i++)
                if(visit[i])
                    builder.append(user_id[i]).append("-");

            set.add(builder.toString());

            return;
        }

        for (int i = 0; i < user_id.length; i++)
            if(!visit[i] && user_id[i].matches(banned[idx])) {
                visit[i] = true;
                solve(set, idx + 1, visit, user_id);
                visit[i] = false;
            }
    }
}