package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class JewelTheif {
    static class Jewel implements Comparable<Jewel> {
        int weight, value;
        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
        @Override
        public int compareTo(Jewel o) {
            return this.weight - o.weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // value를 기준으로 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        Jewel []jewels = new Jewel[n];
        int []knapsacks = new int[k]; // knapsack size

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            //pq.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            jewels[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < k; i++) {
            knapsacks[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(knapsacks); // 오름차순 정렬
        Arrays.sort(jewels);

        int idx = 0;
        long sum = 0L;

        for (int i = 0; i < k; i++) {
            while (idx < n && jewels[idx].weight <= knapsacks[i]) {
                pq.add(jewels[idx].value);
                idx++;
            }
            if (!pq.isEmpty()) {
                sum += pq.poll();
            }
        }

        System.out.print(sum);
    }
}
