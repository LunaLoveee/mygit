package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaxDiff {
    static int N, result = Integer.MIN_VALUE;
    static int []array, temp_array;
    static boolean []visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N];
        temp_array = new int[N];
        visited = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        backTracking(0);

        System.out.println(result);
    }

    public static void backTracking(int depth){
        if(depth == N){
            int max = 0;

            for(int i = 0; i < N - 1; i++){
                max += Math.abs(temp_array[i] - temp_array[i + 1]);
            }

            result = Math.max(result, max);
            return;
        }

        for(int i = 0; i < N; i++) {
            if(!visited[i]){
                temp_array[depth] = array[i];
                visited[i] = true;
                backTracking(depth+1);
                visited[i] = false;
            }
        }
    }
}
