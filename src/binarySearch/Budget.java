package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Budget {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int []arr = new int[n];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        int m = Integer.parseInt(br.readLine());

        while (min <= max) {
            int middle = min + (max - min) / 2;
            long budget = 0;
            for (int i = 0; i < n; i++) {
                if (middle < arr[i]) {
                    budget += middle;
                }
                else {
                    budget += arr[i];
                }
            }
            if (budget <= m) {
                min = middle + 1;
            }
            else {
                max = middle - 1;
            }
        }

        System.out.print(max);
    }
}
