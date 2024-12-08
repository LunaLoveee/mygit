package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class WordMath {
    static Integer []alpabet = new Integer[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

//        for (Integer i : alpabet) {
//            i = 0;
//        }
        Arrays.fill(alpabet, 0);
        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            for (int j = 0; j < s.length(); j++) {
                alpabet[s.charAt(j) - 'A'] += (int)Math.pow(10, s.length() - 1 - j);
            }
        }

        Arrays.sort(alpabet, Comparator.reverseOrder());

        int cur = 9, sum = 0;

        for (int i = 0; i < alpabet.length; i++) {
            if (alpabet[i] != 0) {
                sum += alpabet[i] * cur--;
            }
        }

        System.out.println(sum);
    }
}
