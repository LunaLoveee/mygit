package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Cheese {
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static boolean [][]visited;
    static int [][]matrix;
    static int []dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static int N, M, time = 0, count = 0;
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

        while (true) {
            int currentCheese = countCheese();
            if (currentCheese == 0) {
                break;
            }
            count = currentCheese;

            List<Point> list = new ArrayList<>();
            visited = new boolean[N][M];

            bfs(list, 0, 0);
            melt(list);
            time++;
        }

        System.out.print(time + "\n" + count);
    }

    public static void melt(List<Point> list) {
        for (Point p : list) {
            matrix[p.x][p.y] = 0;
        }
    }

    public static void bfs(List<Point> list, int x, int y) {
        Queue<Point> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new Point(x, y));

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (checkRange(nx, ny) && !visited[nx][ny]) {
                    if (matrix[nx][ny] == 1) {
                        list.add(new Point(nx, ny));
                    }
                    else {
                        q.add(new Point(nx, ny));
                    }
                    visited[nx][ny] = true;
                }
            }
        }
    }

    public static int countCheese() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}
