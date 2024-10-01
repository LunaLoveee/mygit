package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class AC { // 5430
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            Deque<Integer> list;
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String x = br.readLine();
            list = parsing(x);
            if(list.isEmpty()){

            }
            boolean reverseFlag = false;
            boolean errorFlag = false;

            for(char c : p.toCharArray()) {
                if(c == 'R'){
                    reverseFlag = !reverseFlag;
                }
                else if(c == 'D'){
                    if(list.isEmpty()){
                        sb.append("error");
                        errorFlag = true;
                        break;
                    }
                    else if(reverseFlag){
                        list.removeLast();
                    }
                    else if(!reverseFlag){
                        list.removeFirst();
                    }
                }
            }

            if(!errorFlag){
                if(list.isEmpty()){
                    sb.append("[]");
                }
                else{
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append("[");

                    if(reverseFlag){
                        while(!list.isEmpty()){
                            sb1.append(list.pollLast()).append(',');
                        }
                    }
                    else{
                        while(!list.isEmpty()){
                            sb1.append(list.pollFirst()).append(',');
                        }
                    }

                    sb1.delete(sb1.length() - 1, sb1.length());
                    sb1.append("]");
                    sb.append(sb1.toString());
                }
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static Deque<Integer> parsing(String s) {
        Deque<Integer> list = new LinkedList<>();
        String []nums = s.substring(1, s.length() - 1).split(",");

        for(String str : nums){
            if(!str.isEmpty()){
                list.add(Integer.parseInt(str));
            }
        }

        return list;
    }

}
