import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOExcetpion

public class Main {
    static int[][] dp;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int limit = Integer.parseInt(st.nextToken());

        dp = new int[N+1][limit+1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(in.readLine());

            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            for(int j = 1; j <= limit; j++) {
                // index is weight;
                if(j < weight){
                    dp[i][j] = dp[i-1][j];
                    continue;
                }
                // what is max Value();
                dp[i][j] = Math.max(value + dp[i-1][j-weight], dp[i-1][j]);
            }
        }
        System.out.println(dp[N][limit]);
    }
}