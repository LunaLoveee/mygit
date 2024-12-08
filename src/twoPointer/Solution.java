package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int []arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int x1 = 0, x2 = 0, min = 0, max = n - 1;
        int result = Integer.MAX_VALUE;

        while (min < max) {
            int temp = arr[min] + arr[max];
            if (result > Math.abs(temp)) {
                x1 = arr[min];
                x2 = arr[max];

                result = Math.abs(temp);

                if (temp == 0) {
                    break;
                }
            }

            if (temp < 0) {
                min++;
            }
            else {
                max--;
            }
        }

        System.out.print(x1 + " " + x2);
    }
}
