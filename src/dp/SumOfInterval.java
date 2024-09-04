package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfInterval { // 시간복잡도 약간 느림 더 좋은 방향으로 개선 가능 ->
    // 행마다 합을 구하지 말고 전체 배열에서 합을 구하기, 행마다 구하면
    // 마지막에 행끼리 계산해야하기 때문에 시간을 많이 잡아먹음
    static StringBuilder sb = new StringBuilder();
    static int [][]basic;
    static int [][]dp;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        basic = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                basic[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        calcDP();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            sb.append(findSum(x1, y1, x2, y2)).append("\n");
        }

        System.out.print(sb);
    }

    public static void calcDP() {
        for (int i = 1; i <= N; i++) {
            dp[i][1] = basic[i][1];
            for (int j = 2; j <= N; j++) {
                dp[i][j] = dp[i][j - 1] + basic[i][j];
            }
        }
    }

    public static int findSum(int x1, int y1, int x2, int y2) {
        int sum = 0;

        for (int i = x1; i <= x2; i++) {
            sum += dp[i][y2] - dp[i][y1 - 1];
        }

        return sum;
    }
}
