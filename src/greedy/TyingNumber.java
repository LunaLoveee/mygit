package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TyingNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();
        long sum = 0L;

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x > 1) {
                positive.add(x);
            }
            else if (x == 1) {
                sum++;
            }
            else {
                negative.add(x);
            }
        }

        Collections.sort(positive, Collections.reverseOrder());
        Collections.sort(negative);

        for (int i = 0; i < positive.size() - 1; i += 2) {
            sum += (long)positive.get(i) * positive.get(i + 1);
        }
        if (positive.size() % 2 == 1) {
            sum += positive.get(positive.size() - 1);
        }

        for (int i = 0; i < negative.size() - 1; i += 2) {
            sum += (long)negative.get(i) * negative.get(i + 1);
        }
        if (negative.size() % 2 == 1) {
            sum += negative.get(negative.size() - 1);
        }

        System.out.println(sum);
    }
}
