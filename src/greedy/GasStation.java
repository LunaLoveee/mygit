package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GasStation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Long []distance = new Long[n - 1];
        Long []price = new Long[n];


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            distance[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            price[i] = Long.parseLong(st.nextToken());
        }

        Long min = price[0];
        Long sum = 0L;

        for (int i = 0; i < n - 1; i++) {
            if(price[i] < min) {
                min = price[i];
            }
            sum += min * distance[i];
        }

        System.out.println(sum);
    }
}
