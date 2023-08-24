import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N + 1][M + 1];  // 1-based indexing 사용

        // 그리드 숫자 입력 받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N + 1][M + 1];  // dp 배열 초기화

        // 첫 번째 행 초기화
        for (int j = 1; j <= M; j++) {
            dp[1][j] = dp[1][j - 1] + grid[1][j];
        }

        // 첫 번째 열 초기화
        for (int i = 2; i <= N; i++) {
            dp[i][1] = dp[i - 1][1] + grid[i][1];
        }

        // 다이나믹 프로그래밍으로 누적 합 계산
        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= M; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + grid[i][j];
            }
        }

        // 쿼리 처리
        int K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int sum = dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1];
            System.out.println(sum);
        }

    }
}