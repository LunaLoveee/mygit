package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BabyShark2 {
    static class Shark {
        Point point;
        int size, sizeBuff, count;
        public Shark(Point point, int size, int sizeBuff, int count) {
            this.point = point;
            this.size = size;
            this.sizeBuff = sizeBuff;
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
    static class Info {
        Point point;
        int distance;
        public Info(Point point, int distance) {
            this.point = point;
            this.distance = distance;
        }
    }
    static class MiniShark {
        Point point;
        int count;
        public MiniShark(Point point, int count) {
            this.point = point;
            this.count = count;
        }
    }
    // 먹을 수 있는 물고기(해당 물고기의 좌표, 현재 상어와 물고기와의 거리차)
    static int [][]matrix;
    static int []dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];

        int startX = 0, startY = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (matrix[i][j] == 9) {
                    startX = i;
                    startY = j;
                }
            }
        }

        Shark shark = new Shark(new Point(startX, startY), 2, 0, 0);

        while (true) {
            PriorityQueue<Info> pq = calcDistance(shark);
            
            if (!pq.isEmpty()) { // 거리가 가장 가까운 물고기 먹으러 감
                Info info = pq.poll();
                shark.count += info.distance;
                matrix[shark.point.x][shark.point.y] = 0;
                matrix[info.point.x][info.point.y] = 9;
                shark.point = info.point;
                shark.sizeBuff++;
                if (shark.sizeBuff == shark.size) { // 그냥 사이즈가 커지는게 아님
                    shark.size++;
                    shark.sizeBuff = 0;
                }
            }
            else {
                break;
            }
        }

        System.out.print(shark.count);
    }

    public static PriorityQueue<Info> calcDistance(Shark shark) {
        PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.distance == o2.distance) {
                if (o1.point.x == o2.point.x) {
                    return o1.point.y - o2.point.y;
                }
                return o1.point.x - o2.point.x;
            }
            return o1.distance - o2.distance;
        });

        /*
            자신의 사이즈보다 작은 물고기들을 queue에 넣음,
            queue에서 하나씩 뺌(목표지점)
            자신과 사이즈가 같은 물고기는 자니갈 수 있음
            자신보다 사이즈가 큰 물고기는 지나갈 수 없음
         */
        Queue<Point> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] != 0 && matrix[i][j] != 9 && matrix[i][j] < shark.size) {
                    q.add(new Point(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            Point p = q.poll();
            int tmp = bfs(shark, p);
            if (tmp != -1) { // 목표에 도달 하지 못함
                pq.add(new Info(p, tmp)); // 이 부분 수정요함
            }
        }

        return pq;
    }

    public static int bfs(Shark shark, Point target) {
        /*
            shark와 target의 거리를 리턴
         */
        Queue<MiniShark> q = new LinkedList<>();
        boolean [][]visited = new boolean[N][N];
        q.add(new MiniShark(shark.point, 0));
        visited[shark.point.x][shark.point.y] = true;

        while (!q.isEmpty()) {
            MiniShark mini = q.poll();

            if (mini.point.x == target.x && mini.point.y == target.y) {
                return mini.count;
            }

            for (int i = 0; i < 4; i++) {
                int newX = mini.point.x + dx[i];
                int newY = mini.point.y + dy[i];

                if (checkRange(newX, newY) && !visited[newX][newY] &&
                        matrix[newX][newY] <= shark.size) {
                    q.add(new MiniShark(new Point(newX, newY), mini.count + 1));
                    visited[newX][newY] = true;
                }
            }
        }

        return -1;
    }

    public static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}
