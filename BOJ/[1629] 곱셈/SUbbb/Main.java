import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] numbers = br.readLine().split(" ");

        long A = Long.parseLong(numbers[0]);
        long B = Long.parseLong(numbers[1]);
        long C = Long.parseLong(numbers[2]);

        long result = 1;
        while (B > 0) {
            if (B % 2 == 1) {
                result = result * A % C;
            }
            A = (A % C) * (A % C) % C;
            B /= 2;
        }

        System.out.println(result);
    }
}