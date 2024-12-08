package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LDS { // 미완성
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int []arr = new int[n + 1];
        int []dp1 = new int[n + 1];
        int []dp2 = new int[n + 1];
        int []dp3 = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp1[i] = 1;
            dp2[i] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
//                if (arr[i] < arr[j]) {
//                    dp1[i] = Math.max(dp1[i], dp1[j] + 1);
//                }
                if (arr[i] > arr[j]) {
                    dp2[i] = Math.max(dp2[i], dp2[j] + 1);
                }
            }
        }

        for (int i = n; i >= 1; i--) {
            for (int j = i - 1; j > 1; j--) {
                if (arr[i] < arr[j]) {
                    dp1[i] = Math.max(dp1[i], dp1[j] + 1);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            dp3[i] = dp1[i] + dp2[i];
        }

        for (int i : dp3) {
            max = Math.max(max, i);
        }

        System.out.println(max - 1);
    }
}
