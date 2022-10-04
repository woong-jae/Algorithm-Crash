import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        boolean[] people = new boolean[N + 1];
        List<Integer>[] parties = new List[M];

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++)
            people[Integer.parseInt(st.nextToken())] = true;

        for (int i = 0; i < M; i++) {
            parties[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            for (int j = 0; j < n; j++)
                parties[i].add(Integer.parseInt(st.nextToken()));
        }
        br.close();

        // M번 반복하여 파티에서 진실을 아는 사람들을 계속 갱신해준다
        // M번을 반복하는 이유 -> 파티의 순서는 중요하지 않고 나중에 알게된 사람이 이전 파티에도 참여했을 수 있기 때문
        for (int i = 0; i < M; i++)
            for (int j = 0; j < M; j++)
                if (parties[j].stream().anyMatch(index -> people[index]))
                    parties[j].forEach(index -> people[index] = true);

        System.out.println(Arrays.stream(parties)
                .filter(party -> canLie(people, party))
                .count());
    }

    private static boolean canLie(boolean[] people, List<Integer> party) {
        for (int n : party)
            if (people[n])
                return false;
        return true;
    }
}