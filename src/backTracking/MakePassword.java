package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MakePassword {
    static StringBuilder sb = new StringBuilder();
    static int L, C;
    static char []charList;
    static char []tempCharList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        charList = new char[C];
        tempCharList = new char[L];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            charList[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(charList);
        backTracking(0, 0);

        System.out.print(sb);
    }

    public static void backTracking(int depth, int start) {
        if(depth == L) {
            if(isValid()){
                for(char c : tempCharList) {
                    sb.append(c);
                }
                sb.append('\n');
            }
            return;
        }

        for(int i = start; i < C; i++) {
            tempCharList[depth] = charList[i];
            backTracking(depth + 1, i + 1);
        }
    }

    public static boolean isValid() {
        int vowelCount = 0;
        int consonantCount = 0;

        for(char c : tempCharList) {
            if(isVowel(c)){
                vowelCount++;
            }
            else{
                consonantCount++;
            }
        }

        return vowelCount > 0 && consonantCount > 1;
    }

    public static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
