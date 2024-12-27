package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FineDust {
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int [][]matrix;
    static int []dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static int N, M, T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];
        Point clockwise = null, counterClockwise;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (matrix[i][j] == -1) {
                    clockwise = new Point(i, j);
                }
            }
        }
        counterClockwise = new Point(clockwise.x - 1, clockwise.y);

        while (T-- > 0) {
            diffusion();
            purification(clockwise, counterClockwise);
        }

        System.out.print(countFineDust());
    }

    public static void purification(Point clockwise, Point counterClockwise) {
        int x = counterClockwise.x, y = counterClockwise.y;
        if (x > 1) {
            int i = x - 2;
            while (i-- > 0) {
                matrix[i + 1][y] = matrix[i][y];
            }
        }

    }

    public static void act(int x, int y) {
        int value = matrix[x][y], count = 0;
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (checkRange(newX, newY)) {
                matrix[newX][newY] += value / 5;
                count++;
            }
        }
        matrix[x][y] -= ((matrix[x][y] / 5) * count);
    }

    public static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static void diffusion() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] > 0) {
                    act(i, j);
                }
            }
        }
    }

    public static int countFineDust() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] != -1) {
                    count++;
                }
            }
        }
        return count;
    }

}
