package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TyingNumber2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> positiveList = new ArrayList<>();
        List<Integer> negativeList = new ArrayList<>();
        int zeroCount = 0;
        int oneCount = 0;

        for (int i = 0; i < n; i++) {
            int temp = Integer.parseInt(br.readLine());

            if (temp == 1) {
                oneCount++;
            }
            else if (temp > 0) {
                positiveList.add(temp);
            }
            else if (temp == 0) {
                zeroCount++;
            }
            else {
                negativeList.add(temp);
            }
        }

        Collections.sort(positiveList, Collections.reverseOrder());
        Collections.sort(negativeList);

        int answer = 0;

        for (int i = 0; i < positiveList.size(); i += 2) {
            if (i + 1 < positiveList.size()) {
                answer += positiveList.get(i) * positiveList.get(i + 1);
            }
            else {
                answer += positiveList.get(i);
            }
        }

        for (int i = 0; i < negativeList.size(); i += 2) {
            if (i + 1 < negativeList.size()) {
                answer += negativeList.get(i) * negativeList.get(i + 1);
            }
            else if (zeroCount == 0) {
                answer += negativeList.get(i);
            }
        }

        answer += oneCount;

        System.out.print(answer);
    }

}
