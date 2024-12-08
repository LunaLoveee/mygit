package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    1. 벽을 3번 세운다. dfs
    2. 벽이 3번 세워진 상태에서 bfs로 확인한다. 바이러스 전파
    3. 안전구역의 개수를 센다.
 */
public class Laboratory {
    static class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int [][]matrix;
    static boolean [][]visited;
    static int []dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static int N, M, answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        System.out.println(answer);
    }

    public static void bfs() { //
        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 2) {
                    q.add(new Point(i, j));
                }
            }
        }

        int [][]temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = matrix[i][j];
            }
        }

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];

                if (checkRange(newX, newY) && temp[newX][newY] == 0) {
                    temp[newX][newY] = 2;
                    q.add(new Point(newX, newY));
                }
            }
        }

        checkSafetyRange(temp);
    }

    public static void dfs(int depth) { // 벽을 3개 세우고 bfs호출
        if (depth == 3) {
            bfs();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][j] = 1;
                    dfs(depth + 1);
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void checkSafetyRange(int [][]temp) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (temp[i][j] == 0) {
                    count++;
                }
            }
        }
        answer = Math.max(answer, count);
    }

    public static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
