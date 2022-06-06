import java.util.*;

class Dic {
    Map<String, Integer> dic;
    ArrayList<Integer> index;
    int max = 27;
    String[] alpha = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public Dic() {
        dic = new HashMap<>();
        index = new ArrayList<>();
        initDic();
    }

    // 사전 초기화
    private void initDic() {
        for (int i = 0; i < 26; i++)
            dic.put(alpha[i], i + 1);
    }

    public int[] compress(String[] msg) {
        for (int i = 0; i < msg.length; i++)
            i = findWord(msg, i);

        return index.stream().mapToInt(i -> i).toArray();
    }

    // i번째부터 사전에 포함되지 않은 단어를 찾는다.
    private int findWord(String[] msg, int i) {
        StringBuilder w = new StringBuilder(msg[i]);
        int idx = -1;

        // 사전에 포함되지 않는 단어를 찾을 때까지 문자를 계속 붙임
        while (dic.containsKey(w.toString())) {
            idx = dic.get(w.toString());
            i++;
            if (i == msg.length) break;
            w.append(msg[i]);
        }
        // 붙인 단어를 사전에 추가하고, 이미 찾은 색인을 배열에 추가
        dic.put(w.toString(), max++);
        index.add(idx);

        // i++ 된 이후로 반복문을 나오기 때문에 -1한 값을 반환
        return i - 1;
    }
}

class Main {
    public static void main(String[] args) {
        String msg = "TOBEORNOTTOBEORTOBEORNOT";

        Dic d = new Dic();

        System.out.println(Arrays.toString(d.compress(msg.split(""))));
    }
}