import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            list.add(Integer.parseInt(st.nextToken()));

        int S = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int max = list.get(i);
            int maxIndex = i;

            for (int j = i + 1; j < N && j <= i + S; j++)
                if (max < list.get(j)) {
                    max = list.get(j);
                    maxIndex = j;
                }

            for (int j = maxIndex; j > i; j--)
                Collections.swap(list, j - 1, j);

            S -= maxIndex - i;

            if (S <= 0)
                break;
        }

        for(int i = 0; i < N; i++)
            System.out.print(list.get(i) + " ");
    }
}