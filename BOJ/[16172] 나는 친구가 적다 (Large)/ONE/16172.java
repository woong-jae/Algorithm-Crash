import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String textbook = br.readLine();
        String keyword = br.readLine();

        Pattern pattern = Pattern.compile("\\D");
        Matcher matcher = pattern.matcher(textbook);
        StringBuilder sb = new StringBuilder();

        while (matcher.find())
            sb.append(matcher.group(0));

        System.out.println(KMP(sb.toString(), keyword));
    }

    private static int[] makeTable(String s) {
        int n = s.length(), idx = 0;
        int[] table = new int[n];

        for (int i = 1; i < n; i++) {
            // 일치하지 않는다면 이전 일치 인덱스 돌아가고 같은 부분이 나올떄까지 반복
            while (idx > 0 && s.charAt(i) != s.charAt(idx))
                idx = table[idx - 1];
            // 일치하면 idx +1 하고 현재위치의 테이블에 idx값을 넣는다
            if (s.charAt(i) == s.charAt(idx))
                table[i] = ++idx;
        }
        return table;
    }

    private static int KMP(String textbook, String keyword) {
        int[] table = makeTable(textbook);
        int n1 = textbook.length(), n2 = keyword.length();
        int idx = 0;

        for (int i = 0; i < n1; i++) {
            // 다를 때
            while (idx > 0 && textbook.charAt(i) != keyword.charAt(idx))
                idx = table[idx - 1];
            // 같을 때
            if (textbook.charAt(i) == keyword.charAt(idx)) {
                // 끝까지 일치 했을 때
                if (idx == n2 - 1)
                    return 1;
                else
                    idx++;
            }
        }
        return 0;
    }
}