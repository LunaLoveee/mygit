package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfPartialSequence { // 1182
    static int []array;
    static int N, M, result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        array = new int [N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        backTracking(0, 0);

        if(M == 0){
            System.out.println(result - 1);
        }
        else{
            System.out.print(result);
        }
    }

    static void backTracking(int depth, int sum){
        if(depth == N){
            if(sum == M){
                result++;
            }
            return;
        }

        backTracking(depth + 1, sum + array[depth]);
        backTracking(depth + 1, sum);
    }
}
