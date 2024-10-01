package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tetromino {
    static int [][]matrix;
    static boolean [][]visited;
    static int []dx = {-1, 0, -1, 0};
    static int []dy = {0, -1, 0, 1};
    static int max = Integer.MIN_VALUE;
    static int N, M;

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

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j, matrix[i][j], 1);
                visited[i][j] = false;
            }
        }

        System.out.println(max);
    }

    public static void dfs(int x, int y, int sum, int count) {
        visited[x][y] = true;

        if (count == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < dx.length; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (checkState(newX, newY) && !visited[newX][newY]) {

                if (count == 2) { // ㅗ모양 체크
                    visited[newX][newY] = true;
                    dfs(x, y, sum + matrix[newX][newY], count + 1);
                    visited[newX][newY] = false;
                }
                visited[newX][newY] = true;
                dfs(newX, newY, sum + matrix[newX][newY], count + 1);
                visited[newX][newY] = false;
            }
        }
    }

    public static boolean checkState(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}
