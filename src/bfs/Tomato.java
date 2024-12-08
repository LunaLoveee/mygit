package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tomato {
    static class Point {
        int x, y, count;
        Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    static int [][]matrix;
    static int []dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.print(bfs());
    }

    public static int bfs() {
        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 1) {
                    q.add(new Point(i, j, 0));
                }
            }
        }

        int max = -1;
        while (!q.isEmpty()) {
            Point p = q.poll();
            max = Math.max(p.count, max);
            for (int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];

                if (checkRange(newX, newY) && matrix[newX][newY] == 0) {
                    matrix[newX][newY] = 1;
                    q.add(new Point(newX, newY, p.count + 1));
                }
            }
        }
        return checkComplete() ? max : -1;
    }

    public static boolean checkComplete() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
