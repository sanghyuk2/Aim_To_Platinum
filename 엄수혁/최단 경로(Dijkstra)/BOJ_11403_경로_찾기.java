import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 문제 이해
 * i에서 j로 가는 길이 존재하면 1, 존재하지 않으면 0k
 *
 * 2. 아이디어
 * 단일 방향으로 흐르기 때문에, 반복문을 통한 route가 있는지 여부를 파악한다.
 *
 * 3. 어려움 및 해결 방식
 * 없음
 * */

public class BOJ_11403_경로_찾기 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        int[][] route = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                route[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (route[i][k] == 1 && route[k][j] == 1) {
                        route[i][j] = 1;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(route[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}
