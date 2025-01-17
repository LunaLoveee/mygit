package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GuitarStrings {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int []set = new int[m];
        int []each = new int[m];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            set[i] = Integer.parseInt(st.nextToken());
            each[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(set);
        Arrays.sort(each);

        int min = Integer.MAX_VALUE;

        min = Math.min(set[0] * (n / 6 + 1), each[0] * n);
        min = Math.min(min, set[0] * (n / 6) + (n % 6) * each[0]);

        System.out.println(min);
    }
}
