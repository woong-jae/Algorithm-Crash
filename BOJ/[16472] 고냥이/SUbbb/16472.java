import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Cat {
    int maxLength = 0;
    public Cat() {}

    public void translate(int N, String str) {
        Map<Character, Integer> wordIndexMap = new HashMap<>();
        int left = 0;
        int right = 0;
        int prev = left;

        while(left <= right) {
            // 범위 벗어나면 종료, 기본적으로 right가 먼저 움직임
            if (right >= str.length()) break;

            char rightCh = str.charAt(right);

            if ((left != right || right == 0) && prev == left)
                wordIndexMap.put(rightCh, wordIndexMap.getOrDefault(rightCh, 0) + 1);

            // 아직 N개 이하인 경우, 계속 length를 늘림
            if (wordIndexMap.size() <= N) {
                maxLength = Math.max(maxLength, right - left + 1);
                right++;
                prev = left;
            } else {
                // N개가 채워져 기존의 종류를 하나 제외하는 경우
                char leftCh = str.charAt(left);
                int num = wordIndexMap.get(leftCh) - 1;
                if(num == 0)
                    wordIndexMap.remove(leftCh);
                else
                    wordIndexMap.put(leftCh, num);
                left++;
            }
        }
    }

    public int getMaxLength() {
        return maxLength;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        Cat cat = new Cat();

        cat.translate(N, str);

        System.out.println(cat.getMaxLength());
    }
}