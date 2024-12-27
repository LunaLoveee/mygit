package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Scale {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int []scaleWeights = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            scaleWeights[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(scaleWeights);

        int answer = 1;
        for (int scaleWeight : scaleWeights) {
            if (scaleWeight > answer) {
                break;
            }
            answer += scaleWeight;
        }

        System.out.print(answer);
    }
}
