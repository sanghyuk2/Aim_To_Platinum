/*
1. 문제 요약
    - 행렬을 2x2 크기의 정사각형으로 나눈다
    - 각 정사각형에서 2번째로 큰 수만 추출
    - 과정을 반복하여 정사각형의 크기를 1x1로 만들었을 때 값 출력

2. 아이디어(문제접근법)
    - 최소단위의 정사각형의 크기가 2x2라는 점에 집중하여 문제를 접근함
    - 크기가 2x2이기에 4개의 값을 저장하는 배열을 작성하여 2번째로 큰 수 추출 시도

3. 어려움 및 해결방식
    - 어려움) 4개의 값이 최소단위라는 걸 알아차리는 게 어려웠음
    - 해결방식) 하나하나 계산해보며 깨달았음
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[][] board;
    private final static int SMALLEST = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        board = new int[N][N];

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(pooling(0, 0, N));
    }

    private static int pooling(int y, int x, int size) {
        int[] arr;
        if (size == 2) {
            arr = new int[SMALLEST];
            int idx = 0;
            for (int i = y; i < y + 2; i++) {
                for (int j = x; j < x + 2; j++) {
                    arr[idx++] = board[i][j];
                }
            }

            Arrays.sort(arr);
            return arr[2];
        }else {
            arr = new int[SMALLEST];

            size /= 2;

            arr[0] = pooling(y, x, size);
            arr[1] = pooling(y + size, x, size);
            arr[2] = pooling(y, x + size, size);
            arr[3] = pooling(y + size, x + size, size);

            Arrays.sort(arr);
            return arr[2];
        }
    }
}