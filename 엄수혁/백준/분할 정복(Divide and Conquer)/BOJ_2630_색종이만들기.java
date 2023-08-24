public class Main {
    static int[][] board;
    static int white = 0;
    static int black = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0, 0, N);
        System.out.println(white);
        System.out.println(black);
    }

    public static void divide(int x, int y, int N) {
        boolean flag = true;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[x][y] != board[x+i][y+j]) {
                    flag = false;
                    break;
                }
            }
            if (!flag) break;
        }

        if (flag) {
            if (board[x][y] == 1) {
                black++;
            } else {
                white++;
            }
        } else {
            // 1사분면
            divide(x, y, N / 2);
            // 2사분면
            divide(x + (N / 2), y, N / 2);
            // 3사분면
            divide(x, y + (N / 2), N / 2);
            // 4사분면
            divide(x + (N / 2), y + (N / 2), N / 2);
        }
    }
}