import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        while(N >= 2){
            divide(0, 0, N);
            N/=2;
        }

        System.out.println(board[0][0]);
    }

    public static void divide(int x, int y, int size) {
        if (size == 2) {
            int[] arr = new int[4];
            int idx = 0;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    arr[idx++] = board[x + i][y + j];
                }
            }

            Arrays.sort(arr);
            board[x / 2][y / 2] = arr[2];
        } else {
            // quad tree
            divide(x, y, size / 2);
            divide(x, y + (size / 2), size / 2);
            divide(x + (size / 2), y, size / 2);
            divide(x + (size / 2), y + (size / 2), size / 2);
        }
    }
}