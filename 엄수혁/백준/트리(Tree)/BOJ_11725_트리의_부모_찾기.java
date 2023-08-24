package beakjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11725_트리의_부모_찾기 {
    static List<List<Integer>> tree = new ArrayList<>();
    static boolean[] isVisited;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        Integer N = Integer.parseInt(in.readLine());
        isVisited = new boolean[N + 1];
        dp = new int[N+1];

        // Node를 관리할 트리 준비
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            tree.get(parent).add(child);
            tree.get(child).add(parent);
        }

        dfs(1);
        for (int i=2; i < dp.length; i++) {
            System.out.println(dp[i]);
        }
    }

    public static void dfs(int idx) {
        isVisited[idx] = true;
        for (int i : tree.get(idx)) {
            if (!isVisited[i]) {
                dp[i] = idx;
                dfs(i);
            }
        }
    }
}
