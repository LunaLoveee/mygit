package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
    1. 한 번의 명령(회전)에 대한 변화를 list에 list에 몇 번 바퀴를 어느 방향으로 돌릴지 저장해놓음 ->
    2. recursive하게 왼쪽관 오른쪽 끝까지 확인
        2-1. 이 때 현재위치(target)과 왼쪽 or 오른쪽의 인덱스
        2 or 6을 비교 후 다르면 해당 방향으로 진행(범위를 벗어나기 전까지)
    3. list에 담아두었던 대로 회전을 진행
 */
public class Cogwheel {
    static class Data {
        int n, direction;
        public Data(int n, int direction) {
            this.n = n;
            this.direction = direction;
        }
    }
    static List<Data> list;
    static int [][]wheels;
    static boolean []visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        wheels = new int[4][8];

        for (int i = 0; i < wheels.length; i++) {
            String str = br.readLine();
            for (int j = 0; j < wheels[i].length; j++) {
                wheels[i][j] = Character.getNumericValue(str.charAt(j));
            }
        }

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());
            int clockwise = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            visited = new boolean[wheels.length];

            list.add(new Data(target - 1, clockwise));
            recur(target - 1, clockwise);

            rotate();
        }

        int sum = 0;
        for (int i = 0; i < wheels.length; i++) {
            if (wheels[i][0] == 1) {
                sum += (int)Math.pow(2, i);
            }
        }

        System.out.println(sum);
    }

    public static void rotate() {
        for (Data data : list) {
            if (data.direction == -1) {
                rotateLeft(wheels[data.n]);
            }
            else {
                rotateRight(wheels[data.n]);
            }
        }
    }

    public static void rotateLeft(int []wheel) {
        int temp = wheel[0];
        System.arraycopy(wheel, 1, wheel, 0, wheel.length - 1);
        wheel[wheel.length - 1] = temp;
    }

    public static void rotateRight(int []wheel) {
        int temp = wheel[wheel.length - 1];
        System.arraycopy(wheel, 0, wheel, 1, wheel.length - 1);
        wheel[0] = temp;
    }

    public static void recur(int target, int clockwise) {
        visited[target] = true;
        int left = target - 1;
        int right = target + 1;

        if (checkRange(left) && !visited[left]) {
            if (wheels[target][6] != wheels[left][2]) {
                list.add(new Data(left, -clockwise));
                recur(left, -clockwise);
            }
        }

        if (checkRange(right) && !visited[right]) {
            if (wheels[target][2] != wheels[right][6]) {
                list.add(new Data(right, -clockwise));
                recur(right, -clockwise);
            }
        }

    }

    public static boolean checkRange(int x) {
        return x >= 0 && x < wheels.length;
    }

}
