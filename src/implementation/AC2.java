package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class AC2 {
    static Deque<Integer> dq;
    static int direction;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            dq = new LinkedList<>();
            direction = 1;
            char []function = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());
            String s = br.readLine();
            int []arr = stringToIntArray(s, n);

//            if (arr == null) {
//                sb.append("error\n");
//                continue;
//            }

            for (int i = 0; i < n; i++) {
                dq.add(arr[i]);
            }

            boolean flag = true;
            for (char c : function) {
                if(!find(c)) {
                    sb.append("error\n");
                    flag = false;
                    break;
                }
            }

            if (!flag) {
                continue;
            }

            sb.append("[");
            if (direction == 1) {
                while (!dq.isEmpty()) {
                    sb.append(dq.removeFirst());
                    if (!dq.isEmpty()) sb.append(',');
                }
            }
            else {
                while (!dq.isEmpty()) {
                    sb.append(dq.removeLast());
                    if (!dq.isEmpty()) sb.append(',');
                }
            }
            sb.append("]").append("\n");
        }

        System.out.println(sb);
    }

    public static boolean find(char c) {
        switch (c) {
            case 'R':
                direction = -direction;
                break;
            case 'D':
                if (dq.isEmpty()) {
                    return false;
                }
                if (direction == 1) {
                    dq.removeFirst();
                }
                else {
                    dq.removeLast();
                }
                break;
        }

        return true;
    }

    public static int[] stringToIntArray(String s, int n) {
        if (n == 0)
            return new int[0];

        String []newString = s.substring(1, s.length() - 1).split(",");
        int []temp = new int[n];

        for (int i = 0; i < newString.length; i++) {
            temp[i] = Integer.parseInt(newString[i]);
        }

        return temp;
    }

}
