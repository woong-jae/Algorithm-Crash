import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); br.close();
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        System.out.println(pow(A, B, C));
    }

    private static long pow(long A, long exp, long C) {
        // 지수가 1인 경우 return
        if (exp == 1)
            return A % C;

        long temp = pow(A, exp / 2, C);

        // 현재 지수가 홀수 A^(exponent / 2) * A^(exponent / 2) * A
        if (exp % 2 == 1)
            return (temp * temp % C) * A % C;
        return temp * temp % C;
    }
}