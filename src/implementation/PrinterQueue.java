package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PrinterQueue {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++) {
            Queue<Integer> q = new LinkedList<>();
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            for(int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                q.add(Integer.parseInt(st.nextToken()));
            }

            while(!q.isEmpty()) {
                int x = q.poll();

                for(int j : q){
                    if(x < j){
                        q.add(x);
                        break;
                    }
                }

            }
        }
    }
}
