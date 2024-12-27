package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ExplosionString {
    static class Data{
        int start, end;
        public Data(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String explosion = br.readLine();

        String substring = act(str, explosion);

        if (!substring.isEmpty()) {
            System.out.print(substring);
        }
        else {
            System.out.println("FRULA");
        }

    }

    public static String act(String str, String exp){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));

            if (sb.length() >= exp.length()) {
                if (sb.substring(sb.length() - exp.length()).equals(exp)) {
                    sb.delete(sb.length() - exp.length(), sb.length());
                }
            }
        }

        return sb.toString();
    }

}
