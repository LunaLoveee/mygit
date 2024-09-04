package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class InstallRouter {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int []array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(array);

        long max = array[n - 1] + array[0], min = 1, result = 0;

        while (min <= max) {
            long middle = min + (max - min) / 2;

            if (canInstall(array, c, middle)) {
                result = middle;
                min = middle + 1;
            }
            else {
                max = middle - 1;
            }
        }

        System.out.print(result);
    }

    public static boolean canInstall(int[] array, int c, long middle) {
        // 처음 집에는 반드시 설치
        long count = 1;
        long current_install = array[0]; // 최근 설치한 위치

        for (int i = 1; i < array.length; i++) {
            if (array[i] - current_install >= middle) {
                count++;
                current_install = array[i];
            }
        }

        return count >= c;
    }
}
