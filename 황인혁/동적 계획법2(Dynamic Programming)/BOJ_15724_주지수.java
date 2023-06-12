/*
1. 문제요약
영토의 크기가 주어지고 그 안에는 주민의 수가 있고 알고 싶은 특정지역의 크기가 주어 진다. 그 후 특정지역안에 살고 있는 주민의 수를 출력
입력 :  N M(영토의 크기)
         영토의 주민의 수
          K(특정지역의 개수)
          알고 싶은 특정지역의 크기(y1, x1, y2, x2) ⇒ 모서리의 좌표
출력 : 주어진 특정 지역의 범위내에 살고 있는 사람의 수의 합
2. 아이디어(문제 해결방식)
~~주어진 영토의 크기를 가진 배열을 만들고 그 안에 주민의 수를 넣고 특정 지역의 범위를 입력 받는다. 이때 주어진 앞에 두 값은 1 1로 동일 하기 때문에 3번째 4번째 값을 받아서 for문을 통해 해당 구역의 값을 누적한다.~~
3. 어려움 및 해결방식
그냥 for문을 사용해서 문제를 푸니 역시나 시간 초과가 났다. 그래서 주민의 수를 입력 받을 때 누적 합 배열을 만들어서 문제를 풀었다.
 */

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