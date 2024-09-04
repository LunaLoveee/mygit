package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LostParenthesis {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String []subString = br.readLine().split("-");

        int result = sum(subString[0]);

        for (int i = 1; i < subString.length; i++) {
            result -= sum(subString[i]);
        }

        System.out.println(result);
    }

    public static int sum(String str) {
        int sum = 0;
        String []numbers = str.split("\\+");

        for (String number : numbers) {
            sum += Integer.parseInt(number);
        }

        return sum;
    }
}
