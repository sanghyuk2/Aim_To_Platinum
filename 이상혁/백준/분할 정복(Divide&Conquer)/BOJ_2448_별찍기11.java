/*
1. 문제 요약
    - 입력값이 12일때 아래와 같은 그림을 그린다.
    - 항상 3×2^k수인 N을 입력값으로 받아 아래와 같은 패턴을 그리는 그림을 그려라
                       *
                      * *
                     *****
                    *     *
                   * *   * *
                  ***** *****
                 *           *
                * *         * *
               *****       *****
              *     *     *     *
             * *   * *   * *   * *
            ***** ***** ***** *****

2. 아이디어(문제접근법)
    - 1번째부터 3번째 줄까지의 패턴이 반복된다.
    - 반복되는 패턴을 재귀적으로 탐색하여 그림을 완성한다.

3. 어려움 및 해결방식
    - 어려움) 공백을 어떻게 처리해야할지 의문을 가졌음
    - 해결방안) 공백을 처리하려 하지 말고 도형을 옮기는 걸 선택하여 어려움을 해결하였음
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static char[][] pattern;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        pattern = new char[N][2 * N - 1];

        for (char[] row : pattern) {
            Arrays.fill(row, ' ');
        }

        drawPattern(0, N - 1, N);

        for (char[] row : pattern) {
            System.out.println(row);
        }
    }

    private static void drawPattern(int row, int col, int height) {
        if (height == 3) {
            pattern[row][col] = '*';
            pattern[row + 1][col - 1] = pattern[row + 1][col + 1] = '*';
            pattern[row + 2][col - 2] = pattern[row + 2][col - 1] = pattern[row + 2][col] = pattern[row + 2][col + 1] = pattern[row + 2][col + 2] = '*';
            return;
        }

        int newHeight = height / 2;
        drawPattern(row, col, newHeight);
        drawPattern(row + newHeight, col - newHeight, newHeight);
        drawPattern(row + newHeight, col + newHeight, newHeight);
    }
}
