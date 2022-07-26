import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int S = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            if (S == 0) break;

            int maxIdx = findMax(i, i + S);

            for (int j = maxIdx; j > i; j--) {
                swap(j - 1, j);
                S--;
            }
        }

        for (int n : numbers)
            System.out.print(n + " ");
    }

    private static int findMax(int s, int e) {
        int maxIdx = 0;
        int max = 0;

        for (int i = s; i <= e && i < numbers.length; i++) {
            if (max < numbers[i]) {
                maxIdx = i;
                max = numbers[i];
            }
        }

        return maxIdx;
    }

    private static void swap(int i1, int i2) {
        int tmp = numbers[i1];
        numbers[i1] = numbers[i2];
        numbers[i2] = tmp;
    }
}