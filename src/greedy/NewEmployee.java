package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NewEmployee {
    static int [][]array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());


        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            array = new int[n][2];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                array[i][0] = Integer.parseInt(st.nextToken());;
                array[i][1] = Integer.parseInt(st.nextToken());;
            }
            Arrays.sort(array, (a, b) -> Integer.compare(a[0], b[0]));

            int maxSelected = 1;
            int minInterviewRank = array[0][1];

            for (int i = 1; i < n; i++) {
                if (array[i][1] < minInterviewRank) {
                    maxSelected++;
                    minInterviewRank = array[i][1];
                }
            }

            sb.append(maxSelected + "\n");
        }

        System.out.print(sb);
    }
}
