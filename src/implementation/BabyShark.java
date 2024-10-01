package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BabyShark {
    static class Point {
        int x, y;
        int distance = 0, size;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        Point(int x, int y, int distance, int size) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.size = size;
        }

        public void setDistance (int distance) {
            this.distance = distance;
        }

        public void setXY (int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setSize (int size) {
            this.size = size;
        }

        public int getSize () {
            return size;
        }
    }
    static Point babyShark;
    static ArrayList<Point> list;
    static int N, result = 0;
    static int [][]matrix;
    static boolean[][] visited;
    static int []dx = {-1, 0, 1, 0};
    static int []dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        matrix = new int[N][N];

        int r = 0, c = 0, count = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int x = Integer.parseInt(st.nextToken());
                matrix[i][j] = x;

                if (x == 9) {
                    r = i;
                    c = j;
                }
            }
        }

        babyShark = new Point(r, c, 0, 2);
        // 1. 먹을 수 있는 물고기가 없다(나를 제외한 모든 물고기가 나보다 크기가 큼)
        // -> 종료
        // 현재 먹을 수 있는 물고기의 위치를 bfs로 찾아서 arrayList에 넣어줌
        // 2. 먹을 수 있는 물고기가 1마리임
        // -> 그 물고기를 먹으러 감
        // 3. 먹을 수 있는 물고기가 2마리 이상임
        // -> 거리가 가까운 곳으로 감 -> 거리각 같으면 가장 위쪽, 왼쪽에 있는 물고기
        while (true) {
            visited = new boolean[N][N];
            list = new ArrayList<>();

            bfs(babyShark.x, babyShark.y);

            if (list.isEmpty()) { // 1. 먹을 수 있는 물고기가 없다(나를 제외한 모든 물고기가 나보다 크기가 큼)
                break;
            }

            if (list.size() == 1) {
                // 2. 먹을 수 있는 물고기가 1마리임
                // -> 그 물고기를 먹으러 감
                visited = new boolean[N][N];
                result += findDistanceByBfs(babyShark, list.get(0));

                Point p = list.get(0);
                //result += p.distance;

                matrix[babyShark.x][babyShark.y] = 0;
                babyShark.setXY(p.x, p.y);
                matrix[p.x][p.y] = 9;

                count++;

                if (count == babyShark.size) {
                    babyShark.size++;
                    count = 0;
                }
            }
            else {
                visited = new boolean[N][N];

                // 3. 먹을 수 있는 물고기가 2마리 이상임
                // -> 거리가 가까운 곳으로 감 -> 거리각 같으면 가장 위쪽, 왼쪽에 있는 물고기
                for (Point point : list) {
                    point.setDistance(findDistanceByBfs(babyShark, point));
                }

                Collections.sort(list, new Comparator<Point>() {
                    @Override
                    public int compare(Point p1, Point p2) {
                        if (p1.distance == p2.distance) {
                            if (p1.x == p2.x) {
                                return p1.y - p2.y;
                            }
                            return p1.x - p2.x;
                        }
                        return p1.distance - p2.distance;
                    }
                });
                // 먹고 자리 바꾸기
                result += findDistanceByBfs(babyShark, list.get(0));
                Point p = list.get(0);
                matrix[babyShark.x][babyShark.y] = 0;
                babyShark.setXY(p.x, p.y);
                matrix[p.x][p.y] = 9;

                count++;

                if (count == babyShark.size) {
                    babyShark.size++;
                    count = 0;
                }
            }
        }

        System.out.println(result);
    }

    public static int findDistanceByBfs(Point p1, Point p2) {
        visited = new boolean[N][N];
        int distance = 0;
        Queue<Point> q = new LinkedList<>();
        visited[p1.x][p1.y] = true;
        q.add(new Point(p1.x, p1.y, 0));

        while (!q.isEmpty()) {
            Point curr = q.poll();

            for (int i = 0; i < 4; i++) {
                int newX = curr.x + dx[i];
                int newY = curr.y + dy[i];

                if (checkState(newX, newY) && !visited[newX][newY] && babyShark.size >= matrix[newX][newY]) {
                    if (p2.x == newX && p2.y == newY) {
                        return curr.distance + 1;
                    }
                    visited[newX][newY] = true;
                    q.add(new Point(newX, newY, curr.distance + 1));
                }
            }
        }
        return distance;
    }

    public static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x,y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];

                if (checkState(newX, newY) && !visited[newX][newY] && babyShark.size >= matrix[newX][newY]) {
                    if (babyShark.size > matrix[newX][newY] && matrix[newX][newY] != 0) { // 먹을 수 있는 물고기야
                        list.add(new Point(newX,newY));
                    }
                    visited[newX][newY] = true;
                    q.add(new Point(newX,newY));
                }
            }
        }
    }

    public static boolean checkState(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}
