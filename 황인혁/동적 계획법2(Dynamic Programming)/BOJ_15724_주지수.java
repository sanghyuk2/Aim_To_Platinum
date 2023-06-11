import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15724_주지수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1= new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st1.nextToken());
        int M = Integer.parseInt(st1.nextToken());

        int[][] area = new int[N][M];
        int[][] sum = new int[N][M]; // 누적 합 배열 추가

        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                area[i][j] = Integer.parseInt(st2.nextToken());
                if (i == 0 && j == 0) {
                    sum[i][j] = area[i][j];
                } else if (i == 0) {
                    sum[i][j] = sum[i][j - 1] + area[i][j];
                } else if (j == 0) {
                    sum[i][j] = sum[i - 1][j] + area[i][j];
                } else {
                    sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + area[i][j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st3 = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st3.nextToken()) - 1;
            int x1 = Integer.parseInt(st3.nextToken()) - 1;
            int y2 = Integer.parseInt(st3.nextToken()) - 1;
            int x2 = Integer.parseInt(st3.nextToken()) - 1;

            int result = sum[y2][x2];
            if (y1 > 0) {
                result -= sum[y1 - 1][x2];
            }
            if (x1 > 0) {
                result -= sum[y2][x1 - 1];
            }
            if (y1 > 0 && x1 > 0) {
                result += sum[y1 - 1][x1 - 1];
            }

            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
}