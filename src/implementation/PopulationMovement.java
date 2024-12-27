package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PopulationMovement {
    static class Data {
        int x, y;
        public Data(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static List<Data> list;
    static boolean [][]visited;
    static int [][]matrix, temp;
    static int []dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static int N, left, right;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        left = Integer.parseInt(st.nextToken());
        right = Integer.parseInt(st.nextToken());
        matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int [][]prev = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                prev[i][j] = matrix[i][j];
            }
        }
        int answer = 0;
        while (true) {
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    System.out.print(matrix[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
            /*
                matrix의 크기만큼 돌기(방문한 곳은 방문 x)
                bfs로 돌기 돌면서 좌표값을 list에 넣고 개수를 리턴
                다 돌았으면 list에 있는 모든 좌표에 대해 계산
             */
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        list = new ArrayList<>();
                        openBoarders(i, j);
                        //System.out.println("list.size : " + list.size());
                        calcAverage();
                    }
                }
            }

            if (isMove(prev)) {
                for (int i = 0; i < N; i++) {
                    System.arraycopy(matrix[i], 0, prev[i], 0, N);
                }
                answer++;
            }
            else {
                break;
            }
        }

        System.out.println(answer);
    }

//    public static void openBoarders(int x, int y) {
//        for (int i = 0; i < 4; i++) {
//            int newX = x + dx[i];
//            int newY = y + dy[i];
//
//            if (checkArray(newX, newY) && !visited[newX][newY] &&
//                    checkRange(Math.abs(matrix[newX][newY] - matrix[x][y]))) {
//                visited[newX][newY] = true;
//                openBoarders(newX, newY);
//                visited[newX][newY] = false;
//            }
//        }
//    }

    public static void openBoarders(int x, int y) {
        Queue<Data> q = new LinkedList<>();
        q.add(new Data(x, y));
        visited[x][y] = true;
        list.add(new Data(x, y));

        while (!q.isEmpty()) {
            Data data = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = data.x + dx[i];
                int ny = data.y + dy[i];

                if (checkArray(nx, ny) && !visited[nx][ny] &&
                        checkRange(Math.abs(matrix[nx][ny] - matrix[data.x][data.y]))) {
                    //System.out.println("nx, ny : " + nx + " " + ny);
                    q.add(new Data(nx, ny));
                    visited[nx][ny] = true;
                    list.add(new Data(nx, ny));
                }
            }
        }
    }

    public static void calcAverage() {
        int avg = 0;

        for (Data data : list) {
            avg += matrix[data.x][data.y];
        }

        avg /= list.size();

        for (Data data : list) {
            matrix[data.x][data.y] = avg;
        }
    }

    public static boolean isMove(int[][] prev) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (prev[i][j] != matrix[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkRange(int value) {
        return value >= left && value <= right;
    }

    public static boolean checkArray(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}
