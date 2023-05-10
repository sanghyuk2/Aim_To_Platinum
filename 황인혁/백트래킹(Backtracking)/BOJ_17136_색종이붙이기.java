/*
1. 문제 이해
10X10 크기의 판에 1의 값을 1×1, 2×2, 3×3, 4×4, 5×5의 크기의 색종이들로 덮는데 필요한 색종이의 최소 개수를 구하는 문제
입력 : 10X10 크기의 보드 판
출력 : 색종이의 개수
조건 : 각 색종이는 5개씩 보유하고 있다
         판에 1이 존재하고 모든 1을 덮는것이 불가능한 경우 -1 출력
         (모든 값이 0인 경우는 0출력)
2. 아이디어
- 10X10 크기의 판에서 (0, 0)부터 시작하여 1인 값을 찾는다.
- 해당 위치에서 1x1, 2x2, 3x3, 4x4, 5x5 크기의 색종이를 순서대로 덮을 수 있는지 확인
- 덮을 수 있다면 해당 색종이를 덮은 후 다음 1인 값을 찾는다.
- 덮을 수 없다면 다음 크기의 색종이를 사용하여 덮을 수 있는지 확인
- 모든 위치에 색종이를 덮었다면 색종이 사용 개수를 갱신
- 더 이상 색종이를 덮을 수 없거나 색종이 개수가 현재 최소값보다 크면 백트래킹을 종료
3. 사용 변수
- int[][] board : 10x10 크기의 판을 나타내는 2차원 배열
- int[] confetti : 색종이 개수(각 5개씩)를 저장하는 배열
- int cnt : 현재까지 사용한 색종이 개수를 저장하는 변수
- int min_cnt : 최소 색종이 사용 개수를 저장하는 변수
4. 자료 구조
bf(int row, int col)
5. 문제점/해결책
- 처음에 int[] used 배열을 사용해서 사용한 색종이를 체크하려고 했다. 각 색종이의 개수는 5개로 정해져 있다.
  int[] confetti 배열을 사용해서 사용 개수를 체크하며 경우의 수를 계산했다.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17136_색종이붙이기 {
    private static final int N = 10;
    private static int[][] board; // 판의 크기
    private static int[] confetti = {0, 5, 5, 5, 5, 5}; // 각 크기의 색종이 개수(5개씩)
    private static int cnt; // 색종이 사용 개수
    private static int min_cnt = Integer.MAX_VALUE; // 최소 색종이 사용 개수

    public static void main(String[] args) throws IOException {
        board = new int[N][N];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bf(0, 0);

        if (min_cnt != Integer.MAX_VALUE) {
            System.out.println(min_cnt);
        } else {
            System.out.println(-1);
        }
    }

    public static void bf(int row, int col) {
        if (row == 10) { //마지막 행일 경우 종료
            min_cnt = Math.min(cnt, min_cnt);
            return;
        }
        if (col == 10) { //마지막 열일 경우 다음 행으로
            bf(row + 1, 0);
            return;
        }
        if (min_cnt <= cnt) {//현재 경우가 최소값보다 크거나 같다면 더 이상 탐색하지 않고 종료
            return;
        }

        if (board[row][col] == 1) {
            for (int i = 5; i >= 1; i--) { // i++가 아닌 i--로 수정
                if (confetti[i] > 0 && isCover(row, col, i)) { //색종이가 남아있고 커버가 가능하면
                    cover(row, col, i); //색종이로 커버
                    confetti[i]--;
                    cnt++;
                    bf(row, col + 1);
                    cnt--;
                    confetti[i]++;
                    uncover(row, col, i); //커버한 색종이 다시 떼기
                }
            }
        } else {
            bf(row, col + 1);
        }
    }

    private static boolean isCover(int row ,int col, int size) {
        if (row + size > N || col + size > N) { // 색종이가 판을 벗어나면
            return false;
        }
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (board[i][j] != 1) { // 색종이로 커버할 수 없는 칸이 있으면
                    return false;
                }
            }
        }
        return true;
    }

    private static void cover(int r, int c, int size) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                board[i][j] = 0; // 색종이로 커버
            }
        }
    }

    private static void uncover(int r, int c, int size) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                board[i][j] = 1; // 커버한 색종이를 제거하여 복구
            }
        }
    }
}