package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RobotCleaner2 {
    static class Robot {
        int x, y, direction, count;
        public Robot(int x, int y, int direction, int count) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.count = count;
        }
    }
    static int [][]matrix; // 청소가 된 칸 -> 2
    static int []dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int currentX = Integer.parseInt(st.nextToken());
        int currentY = Integer.parseInt(st.nextToken());
        int currentDirection = Integer.parseInt(st.nextToken());

        Robot robot = new Robot(currentX, currentY, currentDirection, 0);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            if (matrix[robot.x][robot.y] == 0) {
                matrix[robot.x][robot.y] = 2;
                robot.count++;
            }

            if (checkAround(robot)) { // 청소되지 않은 칸이 없음 상하좌우가 모두 1임
                if (!checkReverse(robot)) { // 바라보는 방향을 유지한 채로 한 칸 후진할 수 있는지
                    break;
                }
            }
            else {
                robot.direction = (robot.direction + 3) % 4;
                checkForward(robot);
            }
        }

        System.out.println(robot.count);
    }

    public static void checkForward(Robot robot) {
        switch (robot.direction) {
            case 0:
                if (matrix[robot.x - 1][robot.y] == 0) {
                    robot.x--;
                }
                break;
            case 1:
                if (matrix[robot.x][robot.y + 1] == 0) {
                    robot.y++;
                }
                break;
            case 2:
                if (matrix[robot.x + 1][robot.y] == 0) {
                    robot.x++;
                }
                break;
            case 3:
                if (matrix[robot.x][robot.y - 1] == 0) {
                    robot.y--;
                }
                break;
        }
    }

    public static boolean checkReverse(Robot robot) {
        switch (robot.direction) {
            case 0:
                if (matrix[robot.x + 1][robot.y] != 1) {
                    robot.x++;
                    return true;
                }
                break;
            case 1:
                if (matrix[robot.x][robot.y - 1] != 1) {
                    robot.y--;
                    return true;
                }
                break;
            case 2:
                if (matrix[robot.x - 1][robot.y] != 1) {
                    robot.x--;
                    return true;
                }
                break;
            case 3:
                if (matrix[robot.x][robot.y + 1] != 1) {
                    robot.y++;
                    return true;
                }
                break;
        }

        return false;
    }

    public static boolean checkAround(Robot robot) {
        for (int i = 0; i < 4; i++) {
            int newX = robot.x + dx[i];
            int newY = robot.y + dy[i];

            if (matrix[newX][newY] == 0) {
                return false;
            }
        }
        return true;
    }

}
