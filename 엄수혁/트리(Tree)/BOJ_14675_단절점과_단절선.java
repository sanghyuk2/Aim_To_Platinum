package beakjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14675_단절점과_단절선 {
    static int[] dp;

    public static void main(String[] args) throws IOException {
        StringBuilder br = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        dp = new int[N + 1];

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            dp[Integer.parseInt(st.nextToken())]++;
            dp[Integer.parseInt(st.nextToken())]++;
        }

        int M = Integer.parseInt(in.readLine());

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if (t == 2) {
                br.append("yes\n");
                continue;
            }

            br.append(dp[k] > 1 ? "yes\n" : "no\n");
        }

        System.out.println(br.toString());
    }
}