package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DeliveryChicken {
    static class Node{
        int x, y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, result = Integer.MAX_VALUE;
    static int [][]array;
    static ArrayList<Node> chickenList = new ArrayList<>();
    static ArrayList<Node> houseList = new ArrayList<>();
    static boolean []visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        array = new int[N][N];


        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if(x == 1){
                    houseList.add(new Node(i, j));
                }
                else if(x == 2){
                    chickenList.add(new Node(i, j));
                }
                array[i][j] = x;
            }
        }
        visited = new boolean[chickenList.size()];
        backTracking(0, 0);

        System.out.println(result);
    }

    public static void backTracking(int depth, int start) {
        if(depth == M){ // 치킨의 위치를 모두 고름 -> 도시의 치킨 거리의 최솟값을 구함
            result = Math.min(result, findDistance());
            return;
        }

        for(int i = start; i < chickenList.size(); i++)  {
            if(!visited[i]) {
                visited[i] = true;
                backTracking(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    public static int findDistance() { // 치킨 거리의 합을 리턴
        int temp_sum = 0;

        for(int i = 0; i < houseList.size(); i++) { // 각각의 집에 대해서
            int sum = Integer.MAX_VALUE;
            for(int j = 0; j < chickenList.size(); j++) { // 각각의 치킨집과의 최단거리를 구함
                if(visited[j]){ // 선택된 치킨집에 한해서
                    int houseX = houseList.get(i).x;
                    int houseY = houseList.get(i).y;
                    int chickenX = chickenList.get(j).x;
                    int chickenY = chickenList.get(j).y;
                    int dist = Math.abs(houseX - chickenX) + Math.abs(houseY - chickenY);
                    sum = Math.min(sum, dist);
                }
            }
            temp_sum += sum;
        }

        return temp_sum;
    }
}
