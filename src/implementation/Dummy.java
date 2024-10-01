package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Dummy {
    static class Info {
        int time;
        char d;
        public Info(int time, char d) {
            this.time = time;
            this.d = d;
        }
    }
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Snake {
        int size, direction;
        Point point;

        public Snake(int size, int direction, Point point) {
            this.size = size;
            this.direction = direction; // 0 -> north, 1 -> east, 2 -> south, 3 -> west
            this.point = point;
        }
    }
    static int []dx = {-1, 0, 1, 0};
    static int []dy = {0, 1, 0, -1};
    static int [][]matrix;
    static int result = 0;
    static Snake snake = new Snake(1, 1, new Point(1, 1));
    //static Queue<Point> appleQueue = new LinkedList<>();
    static Queue<Info> infoQueue = new LinkedList<>();
    //static Queue<Point> snakePointQueue = new LinkedList<>();
    static Deque<Point> snakePointQueue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        matrix = new int[n + 2][n + 2];
        matrix[1][1] = 9;
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = 1;
            matrix[0][i] = 1;
            matrix[matrix.length - 1][i] = 1;
            matrix[i][matrix.length - 1] = 1;
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            //appleQueue.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            matrix[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2;
        }

        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());

            infoQueue.add(new Info(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
        }

//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix.length; j++) {
//                System.out.print(matrix[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
        snakePointQueue.addLast(new Point(1, 1));
        while (true) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            //System.out.println(result);
            if (!infoQueue.isEmpty() && infoQueue.peek().time == result) {
                //System.out.println("Size : " + infoQueue.size() + "  Time : " + infoQueue.peek().time);
                char c = infoQueue.poll().d;
                //System.out.println(c);
                switch (c) {
                    case 'D':
                        snake.direction = (snake.direction + 1) % 4;
                        break;
                    case 'L':
                        snake.direction = (snake.direction + 3) % 4;
                        break;
                }
            }
            //System.out.println("Before : " + snake.point.x + " " + snake.point.y + " " + result);
            Point p = new Point(snake.point.x, snake.point.y); // 원래 위치
//            snake.point.x += (dx[snake.direction] + 3) % 4;
//            snake.point.y += (dy[snake.direction] + 3) % 4;
            snake.point.x += dx[snake.direction];
            snake.point.y += dy[snake.direction];

            //System.out.println("After : " + snake.point.x + " " + snake.point.y);
            if (isWall() || isSelf()) {
                break;
            }

            snakePointQueue.addLast(new Point(snake.point.x, snake.point.y));
            if (isApple()) {
                matrix[snake.point.x][snake.point.y] = 9;
                snake.size++;
            }

            else {
                //matrix[p.x][p.y] = 0;
                matrix[snake.point.x][snake.point.y] = 9;
                if (!snakePointQueue.isEmpty()) {
//                    Point tail = snakePointQueue.pollLast();
//                    System.out.println(tail.x + " " + tail.y);
                    Point tail = snakePointQueue.pollFirst();
                    matrix[tail.x][tail.y] = 0;
                }
            }
            result++;
        }

        System.out.println(result + 1);
    }

    public static boolean isSelf() {
        return matrix[snake.point.x][snake.point.y] == 9;
    }

    public static boolean isWall() {
        return matrix[snake.point.x][snake.point.y] == 1;
    }

    public static boolean isApple() {
//        if (!appleQueue.isEmpty() && appleQueue.peek().x == snake.point.x &&
//                appleQueue.peek().y == snake.point.y) {
//            appleQueue.poll();
//            return true;
//        }
//        return false;
        return matrix[snake.point.x][snake.point.y] == 2;
    }

}
