package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SortCard {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }
        int result = 0;

        while (pq.size() >= 2) {
            int x = pq.poll();
            int y = pq.poll();

            result += x + y;
            System.out.println(x + " " + y);
            pq.add(x + y);
        }

        System.out.print(result);
    }
}
