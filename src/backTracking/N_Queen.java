package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N_Queen {
    static int N, result = 0;
    static int []arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        backTracking(0);

        System.out.println(result);
    }

    public static void backTracking(int depth) {
        if(depth == N) {
            result++;
            return;
        }

        for(int i = 0; i < N; i++) {
            arr[depth] = i;

            if(promising(depth)){
                backTracking(depth + 1);
            }
        }
    }

    public static boolean promising(int row) {
        for(int col = 0; col < row; col++) {
            if(arr[row] == arr[col]){
                return false;
            }
            else if(Math.abs(row - col) == Math.abs(arr[row] - arr[col])){
                return false;
            }
        }
        return true;
    }
}
