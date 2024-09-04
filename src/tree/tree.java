package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.zone.ZoneOffsetTransitionRule;
import java.util.StringTokenizer;

public class tree {
    static int []tree;
    static boolean []visited;
    static int N, root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        tree = new int[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            if(x == -1){
                tree[0] = i;
                root = tree[0];
            }
            else{
                tree[i] = x;
            }
        }

        int removeNode = Integer.parseInt(br.readLine());

        removeNode(removeNode);
        countleafs(root);
    }

    public static void removeNode(int target){
        tree[target] = -2; // -2는 삭제된 노드

        for(int i : tree){
            if(target == tree[i]){
                removeNode(i);
            }
        }
    }

    public static void countleafs(int start){
        // todo make countleafs
    }
}
