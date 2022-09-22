import java.io.*;

class Main {
    // 인덱스 i부터 인덱스 j까지의 문자열이 팰린드롬인지 여부를 저장할 배열입니다.
    static boolean[][] palindrome;
    // 인덱스 0부터 인덱스 i까지의 팰린드롬 수의 개수를 저장할 배열입니다.
    static int[] d;
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String string = br.readLine();
        size = string.length();
        palindrome = new boolean[size + 1][size + 1];
        d = new int[size + 1];

        // 팰린드롬 여부를 저장합니다.
        setPalindrome(string);

        // 최소 분할 개수를 구하고 출력합니다.
        System.out.println(getMin());
    }

    private static void setPalindrome(String string) {
        // 길이가 1인 문자열에 대해 팰린드롬인지 저장합니다.
        for (int i = 1; i <= size; i++)
            palindrome[i][i] = true;

        // 길이가 2인 문자열에 대해 팰린드롬인지 저장합니다.
        for (int i = 1; i < size; i++)
            if (string.charAt(i - 1) == string.charAt(i))
                palindrome[i][i + 1] = true;

        // 길이가 3 이상인 문자열에 대해 팰린드롬인지 저장합니다.
        for (int dist = 3; dist <= size; dist++) {
            for (int start = 1; start <= size - dist + 1; start++) {
                int end = start + dist - 1;
                boolean middle = palindrome[start + 1][end - 1];

                // 양 끝을 제외한 문자가 팰린드롬이고, 양 끝 문자가 같다면 이 또한 팰린드롬 수입니다.
                if (middle)
                    palindrome[start][end] = string.charAt(start - 1) == string.charAt(end - 1);
            }
        }
    }

    private static int getMin() {
        for(int i = 1; i <= size; i++) {
            for(int j = 1; j <= i; j++) {
                // 해당 범위에 속하는 문자열이 팰린드롬이고
                // 바로 앞 인덱스까지의 팰린드롬 수 + 1보다 현재 범위까지의 팰린드롬 수가 크거나, 아직 해당 문자열을 탐색하지 않은 경우이면
                if(palindrome[j][i] && (d[i] > d[j - 1] + 1 || d[i] == 0)) {
                    // 바로 이전 인덱스까지의 팰린드롬 수 + 1로 갱신합니다. 최소 분할 수를 찾아야 하기 때문입니다.
                    d[i] = d[j - 1] + 1;
                }
            }
        }

        return d[size];
    }
}