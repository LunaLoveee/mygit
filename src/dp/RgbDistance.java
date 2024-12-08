package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RgbDistance {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int [][]arr = new int[n][n];
        int [][]dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 3; i++) {
            dp[0][i] = arr[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0:
                        dp[i][j] = Math.min(dp[i - 1][j + 1], dp[i - 1][j + 2]) + arr[i][j];
                        break;
                    case 1:
                        dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j + 1]) + arr[i][j];
                        break;
                    case 2:
                        dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j - 2]) + arr[i][j];
                        break;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            min = Math.min(min, dp[n - 1][i]);
        }

        System.out.println(min);
    }
}
