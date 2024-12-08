package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TravelSangeun {
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            answer = 0;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            boolean []visited = new boolean[n + 1];

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            dfs(graph, visited, 1);
            sb.append(answer).append("\n");
        }

        System.out.print(sb);
    }

    public static void dfs(ArrayList<ArrayList<Integer>> graph, boolean []visited,
                          int start) {
        visited[start] = true;

        for (int i : graph.get(start)) {
            if (!visited[i]) {
                answer++;
                dfs(graph, visited, i);
            }
        }
    }
}
