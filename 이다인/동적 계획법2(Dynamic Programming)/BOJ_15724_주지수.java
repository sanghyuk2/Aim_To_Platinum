import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15724_주지수 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] territory = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                territory[i][j] = Integer.parseInt(st.nextToken()) + territory[i - 1][j] + territory[i][j - 1] - territory[i - 1][j - 1];
            }
        }

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i ++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int result = territory[x2][y2] - territory[x2][y1 - 1] - territory[x1 - 1][y2] + territory[x1 - 1][y1 - 1];
            System.out.println(result);
        }

    }
}