package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Change {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int count = 0;
        n = 1000 - n;

        if (n >= 500) {
            count += n / 500;
            n = n % 500;
        }
        if (n >= 100) {
            count += n / 100;
            n = n % 100;
        }
        if (n >= 50) {
            count += n / 50;
            n = n % 50;
        }
        if (n >= 10) {
            count += n / 10;
            n = n % 10;
        }
        if (n >= 5) {
            count += n / 5;
            n = n % 5;
        }
        if (n >= 1) {
            count += n;
        }

        System.out.print(count);
    }
}
