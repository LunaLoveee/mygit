package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TwoSolution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int []array = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);

        int min = 0;
        int max = array.length - 1;
        int result = Integer.MAX_VALUE;
        int x1 = 0, x2 = 0;

        while (min < max) {
            int temp = array[min] + array[max];

            if (result > Math.abs(temp)) {
                result = Math.abs(temp);
                x1 = array[min];
                x2 = array[max];

                if (temp == 0) {
                    break;
                }
            }

            if (temp > 0) {
                max--;
            }
            else {
                min++;
            }
        }

        System.out.print(x1 + " " + x2);

    }
}
