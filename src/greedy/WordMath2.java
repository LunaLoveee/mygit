package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class WordMath2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int []alphabet = new int[26];
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String temp = br.readLine();
            for (int j = 0; j < temp.length(); j++) {
                alphabet[temp.charAt(j) - 'A'] += (int)Math.pow(10, temp.length() - j - 1);
            }
        }

        Arrays.sort(alphabet);

        long answer = 0, value = 9;
        for (int i = alphabet.length - 1; i > 0; i--) {
            if (alphabet[i] != 0) {
                answer += alphabet[i] * value--;
            }
        }

        System.out.print(answer);
    }

}
