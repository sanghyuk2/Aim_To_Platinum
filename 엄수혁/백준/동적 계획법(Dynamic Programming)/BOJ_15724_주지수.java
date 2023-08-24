import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        dp = new int[N+1][M+1];

        for(int i = 0; i < N; i++) {
            dp[i] = Arrays.stream(in.readLine().spilt(" ")).mapToInt(Integer::parseInt).toArray(int[]::new);
        }

        int pointC = Integer.parseInt(in.readLine());

        for(int i = 0; i < pointC; i++){
            int[] point = Arrays.stream(in.readLine().spilt(" ")).mapToInt(Integer::parseInt).toArray(int[]::new);
            int sum = 0;

            for(int y = point[1]; y <= point[3]; y++){
                sum += dp[point[2]][y] - dp[point[0] - 1];
            }
            sb.append(sum).append("\n");
        }
        sb.toString();
    }
}