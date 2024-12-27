package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    backtracking -> cctv의 개수를 모두 설치했으면 사각지대의 크기를 계산함
    1. cctv를 어떻게 설치?
        객체 5개를 만든다.
    2. cctv를 네방향으로 돌림 -> 다음 cctv가 없을 때까지
 */
public class Supervise {
    static class Cctv { // 어디에 몇 번 cctv가 있는지
        Point point;
        int number;
        public Cctv(Point point, int number) {
            this.point = point;
            this.number = number;
        }
    }
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static Queue<Cctv> cctvList;
    static boolean [][]visited;
    static int [][]matrix;
    static int N, M, answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];
        //temp = new int[N][M];
        cctvList = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (matrix[i][j] > 0 && matrix[i][j] < 6) {
                    cctvList.add(new Cctv(new Point(i, j), matrix[i][j]));
                }
            }
        }
        //copyArray();
        backtracking();

        System.out.println(answer);
    }

    public static void backtracking() {
        if (cctvList.isEmpty()) {
            calcBlindSpot();
            return;
        }

        Cctv cctv = cctvList.poll();
        visited = new boolean[N][M];

        switch (cctv.number) {
            case 1:
                for (int i = 0; i < 4; i++) {
                    List<Point> list = new ArrayList<>();
                    supervise(list, cctv.point, i);
                    backtracking();
                }
                break;

        }
    }

    public static void supervise(List<Point> list, Point p, int direction) {
        int curX = p.x, curY = p.y;

        switch (direction) {
            case 0:
                while (curX > 0) {
                    int newX = curX - 1;
                    if (!visited[newX][curY]) {
                        list.add(new Point(newX, curY));
                        visited[newX][curY] = true;
                    }
                }
                break;
            case 1:
                while (curY < M - 1) {
                    int newY = curY + 1;
                    if (!visited[curX][newY]) {
                        list.add(new Point(curX, newY));
                        visited[curX][newY] = true;
                    }
                }
                break;
            case 2:
                while (curX < N - 1) {
                    int newX = curX + 1;
                    if (!visited[newX][curY]) {
                        list.add(new Point(newX, curY));
                        visited[newX][curY] = true;
                    }
                }
                break;
            case 3:
                while (curY > 0) {
                    int newY = curY - 1;
                    if (!visited[curX][newY]) {
                        list.add(new Point(curX, newY));
                        visited[curX][newY] = true;
                    }
                }
        }

    }

    public static void calcBlindSpot() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 0) {
                    count++;
                }
            }
        }
        answer = Math.min(answer, count);
    }

//    public static void copyArray() {
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                temp[i][j] = matrix[i][j];
//            }
//        }
//    }

}
