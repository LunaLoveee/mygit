package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StartAndLink { // 14889
    static int N, diff = Integer.MAX_VALUE;
    static int [][]array;
    static boolean []visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        array = new int[N][N];
        visited = new boolean[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        backTracking(0, 0);

        System.out.print(diff);
    }

    public static void backTracking(int depth, int sum){
        if(depth == N / 2) {
            /*
             diff를 구하는 로직
             */
            findDiff();
            return;
        }

        for(int i = sum; i < N; i++){
            if(!visited[i]){
                visited[i] = true;
                backTracking(depth + 1, 1 + i);
                visited[i] = false;
            }
        }
    }

    public static void findDiff(){
        int start = 0;
        int link = 0;

        /* 4일 떼
        (1, 2) | (3, 4)
        (1, 3) | (2, 4)
        (1, 4) | (2, 3)
         */
        for(int i = 0; i < N - 1; i++){
            for(int j = i + 1; j < N; j++){
                if(visited[i] && visited[j]){
                    start += array[i][j];
                    start += array[j][i];
                }
                else if(!visited[i] && !visited[j]){
                    link += array[i][j];
                    link += array[j][i];
                }
            }
        }

        diff = Math.min(diff, Math.abs(link - start));
    }
}
