package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class MaxHeap {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                if (!maxHeap.isEmpty()) {
                    sb.append(maxHeap.poll()).append("\n");
                }
                else {
                    sb.append("0\n");
                }
            }
            else {
                maxHeap.add(n);
            }
        }

        System.out.print(sb);
    }
}
