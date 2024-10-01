package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RobotCleaner {
    static int []dx = {-1, 0, 1, 0};
    static int []dy = {0, 1, 0, -1};
    static int [][]matrix;
    static int n, m, d;

    public static void main(String []args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.print(find(r, c));
    }

    public static int find(int r, int c) {
        int count = 0;

        while (true) {
            if (matrix[r][c] == 0) { // 현재 칸이 청소가 안되어 있으면 청소
                matrix[r][c] = -1;
                count++;
                System.out.println("(r : " + r + ", c : " + c + ")");
            }

            if (checkClean(r, c)) { // 4칸 중 청소가 모두 안되어있으면
                boolean flag = true;

                switch (d) {
                    case 0:
                        if (matrix[r + 1][c] != 1) {
                            r++;
                            flag = false;
                        }
                        break;
                    case 1:
                        if (matrix[r][c - 1] != 1) {
                            c--;
                            flag = false;
                        }
                        break;
                    case 2:
                        if (matrix[r - 1][c] != 1) {
                            r--;
                            flag = false;
                        }
                        break;
                    case 3:
                        if (matrix[r][c + 1] != 1) {
                            c++;
                            flag = false;
                        }
                        break;
                }

                if (flag) {
                    break;
                }
            }
            else {
                d = (d + 3) % 4;

                switch (d) {
                    case 0:
                        if (matrix[r - 1][c] == 0) {
                            r--;
                        }
                        break;
                    case 1:
                        if (matrix[r][c + 1] == 0) {
                            c++;
                        }
                        break;
                    case 2:
                        if (matrix[r + 1][c] == 0) {
                            r++;
                        }
                        break;
                    case 3:
                        if (matrix[r][c - 1] == 0) {
                            c--;
                        }
                        break;
                }
            }

        }

        return count;
    }

    public static boolean checkClean(int r, int c) {
        for (int i = 0; i < dx.length; i++) {
            int newX = r + dx[i];
            int newY = c + dy[i];

            if (matrix[newX][newY] == 0) {
                return false;
            }
        }

        return true;
    }

}
