package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PrinterQueue {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            int []array = new int[n];

            for (int j = 0; j < n; j++) {
                int x = Integer.parseInt(st.nextToken());
                array[j] = x;
                pq.add(x);
            }

            int count = 0;

            while (!pq.isEmpty()) {
                for (int j = 0; j < array.length; j++) {
                    if (pq.peek() == array[j]) {
                        pq.poll();
                        count++;

                        if (j == m) {
                            sb.append(count + "\n");
                            pq.clear();
                            break;
                        }
                    }
                }
                if (pq.isEmpty()) break;
            }
        }
        System.out.println(sb);
    }
}
