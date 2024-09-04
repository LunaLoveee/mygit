package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Lottery {
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer> list = new ArrayList<>();
    static int []array;
    static boolean []visited;
    static int N;

    public static void main(String[] args) throws IOException { // 6603
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if(N == 0) {
                break;
            }

            visited = new boolean[N];
            array = new int[N];

            for(int i = 0; i < N; i++) {
                array[i] = Integer.parseInt(st.nextToken());
            }

            backTracking(0, 0);
            sb.append("\n");
        }

        System.out.print(sb);
    }

    public static void backTracking(int depth, int start){
        if(depth == 6){
            for(int i = 0; i < visited.length; i++) {
                if(visited[i]){
                    sb.append(array[i]).append(" ");
                }
            }
            sb.append("\n");
            return;
        }

        for(int i = start; i < N; i++){
            if(!visited[i]){
                visited[i] = true;
                backTracking(depth+1, i + 1);
                visited[i] = false;
            }

        }
    }
}
