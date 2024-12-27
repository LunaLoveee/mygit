package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Palindrome {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String str = br.readLine();
            sb.append(find(str.toCharArray())).append('\n');
        }

        System.out.println(sb);
    }

    /*
        찬수를 한 번도 안쓰면 0, 한 번 쓰면 1, 찬스를 사용했는데도 회문이 아니면 2
        왼쪽을 지웠을 때 와 오른쪽을 지웠을 떄를 구분해줘야 함
     */
    public static int find(char []temp) {
        return Math.min(act(temp, 0), act(temp, 1));
    }

    public static int act(char []temp, int state) {
        int left = 0, right = temp.length - 1;
        boolean chanceFlag = false;

        while (left < right) {
            if (temp[left] != temp[right]) {
                if (chanceFlag) { // 찬스를 한 번 사용한 상태임
                    return 2;
                }
                else {
                    if (state == 0) { // 왼쪽 지우기
                        left++;
                    }
                    else {
                        right--;
                    }
                    chanceFlag = true;
                }
            }
            else {
                left++;
                right--;
            }
        }

        return !chanceFlag ? 0 : 1;
    }

}
