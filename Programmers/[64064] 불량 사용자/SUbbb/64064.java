import java.util.*;
import java.util.regex.Pattern;

class Ban {
    HashSet<HashSet<String>> banSet;
    ArrayList<ArrayList<String>> bannedIdList;

    public Ban() {
        banSet = new HashSet<>();
        bannedIdList = new ArrayList<>();
    }

    public void makeSet(String banned_id, String[] user_id) {
        // 정규표현식 사용을 위해 변환
        String regex = banned_id.replace('*', '.');
        ArrayList<String> tmpList = new ArrayList<>();

        for (String user : user_id) {
            boolean isMatch = Pattern.matches(regex, user);
            if (isMatch) tmpList.add(user);
        }
        bannedIdList.add(tmpList);
    }

    public void findBadUser(HashSet<String> set, int size) {
        if (size == bannedIdList.size()) {
            banSet.add(new HashSet<>(set));
            return;
        }

        // list번째 제재 아이디를 set에 추가
        for (String id : bannedIdList.get(size)) {
            if (set.contains(id)) continue;
            set.add(id);
            findBadUser(set, size + 1);
            set.remove(id);
        }
    }

    public int getCount() {
        return banSet.size();
    }
}

class Solution {
    public int solution(String[] user_id, String[] banned_id) {
        Ban b = new Ban();

        for (String ban : banned_id)
            b.makeSet(ban, user_id);

        b.findBadUser(new HashSet<>(), 0);

        return b.getCount();
    }
}