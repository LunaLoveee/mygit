package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tetromino {
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

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, matrix[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(answer);
    }

    public static void dfs(int x, int y, int depth, int sum) {
        if (depth == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (checkRange(newX, newY) && !visited[newX][newY]) {
                if (depth == 2) {
                    visited[newX][newY] = true;
                    dfs(x, y, depth + 1, sum + matrix[newX][newY]);
                    visited[newX][newY] = false;
                }
                visited[newX][newY] = true;
                dfs(newX, newY, depth + 1, sum + matrix[newX][newY]);
                visited[newX][newY] = false;
            }
        }
    }

    public static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}
