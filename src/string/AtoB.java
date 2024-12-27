package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class AtoB {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        StringBuilder b = new StringBuilder(br.readLine());

        while (a.length() < b.length()) {
            if (b.charAt(b.length() - 1) == 'A') {
                b.deleteCharAt(b.length() - 1);
            }
            else {
                b.deleteCharAt(b.length() - 1);
                b.reverse();
            }
        }

        if (a.equals(b.toString())) {
            System.out.println("1");
        }
        else {
            System.out.println("0");
        }
    }

}
