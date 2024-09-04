package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CutTree { // 2805
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int []array = new int[n];
        long max = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, array[i]);
        }

        long min = 1;
        long result = 0;

        while (min <= max) {
            long middle = min + (max - min) / 2;

            long sum = 0;

            for (int i : array) {
                if (i - middle > 0) {
                    sum += i - middle;
                }
            }

            if (sum >= m) {
                min = middle + 1;
                result = middle;
            }
            else {
                max = middle - 1;
            }
        }

        System.out.println(result);
    }
}
