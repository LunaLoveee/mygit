package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExcplosionString {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String explosion = br.readLine();
        StringBuilder sb = new StringBuilder();

        for (char c : str.toCharArray()) {
            sb.append(c);

            if (sb.length() >= explosion.length()) {
                if (sb.substring(sb.length() - explosion.length()).equals(explosion)) {
                    sb.delete(sb.length() - explosion.length(), sb.length());
                }
            }
        }

        if (sb.length() == 0) {
            System.out.println("FRULA");
        }
        else {
            System.out.println(sb.toString());
        }
    }
}
