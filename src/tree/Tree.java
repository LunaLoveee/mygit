package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tree {
    static ArrayList<Integer> []list;
    static boolean []visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        visited = new boolean[n];

        int root = 0, removeTarget;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(st.nextToken());
            if (x == -1) {
                root = i;
            }
            else {
                list[x].add(i);
            }
        }
        removeTarget = Integer.parseInt(br.readLine());

        if (removeTarget == root) {
            System.out.print("0");
        }
        else {
            System.out.print(bfs(root, removeTarget));
        }
    }

    public static int bfs(int root, int removeTarget) {
        Queue<Integer> q = new LinkedList<>();
        visited[root] = true;
        q.add(root);
        int count = 0;

        while (!q.isEmpty()) {
            int parent = q.poll();
            int childSize = 0;

            for (int child : list[parent]) {
                if (!visited[child] && child != removeTarget) {
                    childSize++;
                    q.add(child);
                    visited[child] = true;
                }
            }

            if (childSize == 0) {
                count++;
            }
        }

        return count;
    }

}
