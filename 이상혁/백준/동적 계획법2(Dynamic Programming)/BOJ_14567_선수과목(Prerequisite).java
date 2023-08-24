import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer> parents[] = new ArrayList[N + 1];

        int dp[] = new int [N+1];

        for (int i = 1; i <= N; i++) {
            parents[i] = new ArrayList<>();
            parents[i].add(0);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            parents[to].add(from);
        }

        for(int i = 1 ; i <= N ; i++) {
            for(int j : parents[i])
                dp[i] = Math.max(dp[i], dp[j]+1);
            System.out.print(dp[i]+" ");
        }
    }
}