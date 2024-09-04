package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SumOfNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        long sum = 0, i = 1;

        while (sum < n) {
            sum += i++;
        }

        System.out.println(sum == n ? i - 1 : i - 2);
    }
}
