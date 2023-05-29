/*
1. 문제 요약
    - 0과 1로만 이루어진 2차원 배열을 압축하는 문제
    - 범위 내 영상이 모두 0이라면 압축 결과는 "0", 1이라면 "1"
    - 범위 내 영상이 모두 같은 숫자가 아니라면 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래 순으로 압축결과 나타냄
        - 0(왼쪽 위)0(오른쪽 위)1(왼쪽 아래)1(오른쪽 아래)

2. 아이디어(문제접근법)
    - 범위 내 존재하는 최소단위(1x1)의 정사각형이 모두 같은 숫자인 경우와 아닌 경우로 나누어 계산
        - 만일 같다면 압축을 시킨다
        - 만일 다르다면 재귀함수를 호출하여 과정을 반복한다

3. 어려움 및 해결방식
    - 어려움) 괄호 지정방식
    - 해결방식) 위치를 이동하며 범위 내 최소단위의 정사각형의 숫자가 달라지는 장소를 경계로 "("를 호출하며, 압축이 끝남에 따라 ")"를 호출하여 마무리한다

    - 어려움2) 순서 지정 시 순서를 무시하고 수행했다 맞지않는 결과 도출함
    - 해결방식) 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래 순이라는 문제를 다시 한 번 읽고 순서 정리하여 올바른 결과 도출함
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        quadTree(N, 0, 0);

        System.out.println(sb.toString());

    }

    static void quadTree(int N, int y, int x) {
        for (int i = y; i < y + N; i++) {
            for (int j = x; j < x + N; j++) {
                sb.append("(");
                if (board[i][j] != board[y][x]) {
                    quadTree(N / 2, y, x);
                    quadTree(N / 2, y, x + N / 2);
                    quadTree(N / 2, y + N / 2, x);
                    quadTree(N / 2, y + N / 2, x + N / 2);
                    return;
                }
                sb.append(")");
            }

        }
        sb.append(board[y][x]);



    }
}