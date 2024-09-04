package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CutLanCable { // 1654
    static int K, N;
    static int []array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        array = new int[K];
        long max = 0;

        for(int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(br.readLine());
            if(max < array[i]) {
                max = array[i];
            }
        }
        long min = 1;
        long result = 0;

        while(min <= max) {
            long middle = min + (max - min) / 2;
            long count = 0;

            for(int i : array) {
                count += i / middle;
            }

            if(count >= N) { // 좀 더 크게 잘라도 됨
                min = middle + 1;
                result = middle;
            }
            else{
                max = middle - 1;
            }
        }

        System.out.print(result);
    }
}
