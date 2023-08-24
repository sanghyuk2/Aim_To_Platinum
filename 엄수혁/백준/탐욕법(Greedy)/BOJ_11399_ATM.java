import java.util.Arrays;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

/*
 * 1. 문제 요약
 * 모든 사람이 돈을 인출하는데 걸리는 시간 구하기
 *
 * 2. 아이디어
 * 정렬 후 값들을 Reducing한다.
 * */
public class B_11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] board = new int[N];
        int res = 0;

        for (int i = 0; i < board.length; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(board);

        System.out.println(Arrays.toString(board));
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < i + 1; j++) {
                res += board[j];
            }
        }

        System.out.println(res);
    }
}
