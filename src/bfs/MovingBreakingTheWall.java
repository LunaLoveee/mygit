package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MovingBreakingTheWall {
    static class Point {
        int x, y, count;
        boolean breakState;
        public Point(int x, int y, int count, boolean breakState) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.breakState = breakState;
        }
    }
    static boolean [][][]visited;
    static int []dx = { -1, 0, 1, 0 };
    static int []dy = { 0, 1, 0, -1 };
    static int [][]matrix;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            char []charArray = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = charArray[j] - '0';
            }
        }

        System.out.print(bfs(0, 0));
    }

    public static int bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y, 1, false));
        visited[x][y][0] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (checkFinal(p.x, p.y)) {
                return p.count;
            }
            for (int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];

                if (!checkState(newX, newY)) {
                    continue;
                }


                if (p.breakState) { // 벽을 부순 상태
                    if (!visited[newX][newY][1] && matrix[newX][newY] == 0) {
                        visited[newX][newY][1] = true;
                        q.add(new Point(newX, newY, p.count + 1, true));
                    }
                }
                else { // 벽을 부수지 않음
                    if (matrix[newX][newY] == 1) {
                        visited[newX][newY][1] = true;
                        q.add(new Point(newX, newY, p.count + 1, true));
                    }
                    else if (!visited[newX][newY][0]){
                        visited[newX][newY][0] = true;
                        q.add(new Point(newX, newY, p.count + 1, true));
                    }
                }
            }
        }

        return -1;
    }

    public static boolean checkState(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static boolean checkFinal(int x, int y) {
        return x == N - 1 && y == M - 1;
    }

}
