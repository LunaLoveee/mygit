package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Snake1 {
    static class Snake { // 0 : 북, 1 : 동, 2 : 남, 3 : 서
        int x, y, direction, count;
        public Snake(int x, int y, int direction, int count) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.count = count;
        }
    }
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Direction {
        int x;
        char d;
        public Direction(int x, char d) {
            this.x = x;
            this.d = d;
        }
    }
    static Deque<Point> route = new LinkedList<>();
    static Queue<Direction> directionQueue = new LinkedList<>();
    static int [][]matrix;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int apples = Integer.parseInt(br.readLine());
        matrix = new int[N][N];

        for (int i = 0; i < apples; i++) {
            st = new StringTokenizer(br.readLine());
            int tmpX = Integer.parseInt(st.nextToken()) - 1;
            int tmpY = Integer.parseInt(st.nextToken()) - 1;

            matrix[tmpX][tmpY] = 1;
        }

        int d = Integer.parseInt(br.readLine());
        for (int i = 0; i < d; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);
            directionQueue.add(new Direction(time, direction));
        }

        Snake snake = new Snake(0, 0, 1, 1);
        int time = 0;
        matrix[0][0] = 2; // 2는 자기자신
        route.add(new Point(0, 0));

        while (true) {
            if (checkSpring(snake)) {
                snake.count++;
            }
            else {
                break;
            }

            time++;
            if (!directionQueue.isEmpty() && directionQueue.peek().x == time) {
                char c = directionQueue.poll().d;

                switch (c) {
                    case 'D':
                        snake.direction = (snake.direction + 1) % 4;
                        break;
                    case 'L':
                        snake.direction = (snake.direction + 3) % 4;
                        break;
                }
            }
        }

        System.out.println(snake.count);
    }

    public static boolean checkSpring(Snake snake) { // 벽이나 자신의 몸이면 false
        int newX = snake.x, newY = snake.y;

        switch (snake.direction) {
            case 0:
                newX -= 1;
                if (checkRange(newX, newY) && matrix[newX][newY] != 2) {
                    if (isBlank(newX, newY)) {
                        Point p = route.removeLast();
                        matrix[p.x][p.y] = 0;
                    }
                    snake.x = newX;
                    matrix[newX][newY] = 2;
                    route.addFirst(new Point(newX, newY));

                    return true;
                }
                return false;
            case 1:
                newY += 1;
                if (checkRange(newX, newY) && matrix[newX][newY] != 2) {
                    if (isBlank(newX, newY)) {
                        Point p = route.removeLast();
                        matrix[p.x][p.y] = 0;
                    }
                    snake.y = newY;
                    matrix[newX][newY] = 2;
                    route.addFirst(new Point(newX, newY));

                    return true;
                }
                return false;
            case 2:
                newX += 1;
                if (checkRange(newX, newY) && matrix[newX][newY] != 2) {
                    if (isBlank(newX, newY)) {
                        Point p = route.removeLast();
                        matrix[p.x][p.y] = 0;
                    }
                    snake.x = newX;
                    matrix[newX][newY] = 2;
                    route.addFirst(new Point(newX, newY));

                    return true;
                }
                return false;
            case 3:
                newY -= 1;
                if (checkRange(newX, newY) && matrix[newX][newY] != 2) {
                    if (isBlank(newX, newY)) {

                        Point p = route.removeLast();
                        matrix[p.x][p.y] = 0;
                    }
                    snake.y = newY;
                    matrix[newX][newY] = 2;
                    route.addFirst(new Point(newX, newY));

                    return true;
                }
                return false;
        }

        return false;
    }

    public static boolean isBlank(int x, int y) {
        return matrix[x][y] == 0;
    }

    public static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}
