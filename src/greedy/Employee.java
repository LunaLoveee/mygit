package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Employee {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        int [][]data;

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            data = new int[n][2];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                data[i][0] = Integer.parseInt(st.nextToken());
                data[i][1] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(data, (a, b) -> Integer.compare(a[0], b[0]));

            int result = 1;
            int upperbound = data[0][1]; // 상한선

            for (int i = 1; i < n; i++) {
                if (data[i][1] < upperbound) {
                    result++;
                    upperbound = data[i][1];
                }
            }

            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }
}
