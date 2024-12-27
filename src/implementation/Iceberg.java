package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Iceberg {
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static boolean [][]visited;
    static int []dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static int [][]matrix, tmp;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;

        while (true) {
            if (allZero()) {
                answer = 0;
                break;
            }

            tmp = new int[N][M];
            visited = new boolean[N][M];
            if (!isMultiPiece()) {
                answer++;
            }
            else {
                break;
            }
            melt();
        }

        System.out.println(answer);
    }

    public static void melt() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 0) {
                    for (int k = 0; k < 4; k++) {
                        int newX = i + dx[k];
                        int newY = j + dy[k];

                        if (checkRange(newX, newY) && matrix[newX][newY] != 0) {
                            tmp[newX][newY]--;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] += tmp[i][j];
                if (matrix[i][j] < 0) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static boolean isMultiPiece() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && matrix[i][j] != 0) {
                    bfs(i, j);
                    count++;
                }
            }
        }
        return count > 1;
    }

    public static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];

                if (checkRange(newX, newY) && !visited[newX][newY] && matrix[newX][newY] != 0) {
                    q.add(new Point(newX, newY));
                    visited[newX][newY] = true;
                }
            }
        }
    }

    public static boolean allZero() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkRange(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }

}
