package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class JewelrySteal {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int [][] jewelry = new int[n][2];
        int []backpacks = new int[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            jewelry[i][0] = Integer.parseInt(st.nextToken());
            jewelry[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < k; i++) {
            backpacks[i] = Integer.parseInt(br.readLine());
       }

        Arrays.sort(jewelry, new Comparator<int []> () {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        Arrays.sort(backpacks);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long answer = 0;
        int index = 0;

        for (int backpack : backpacks) {
            while (index < n && backpack >= jewelry[index][0]) {
                pq.add(jewelry[index][1]);
                index++;
            }

            if (!pq.isEmpty()) { // 가장 큰 보석을 넣음
                answer += pq.poll();
            }
        }

        System.out.print(answer);
    }

}
