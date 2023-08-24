package aim_to_platinum.week04_DP2.b15724;

/*
1. 문제 요약
- 제한시간 2초
- N x M 사이즈의 단위구역 내에 각 값을 입력받고
    - 이어서 시작행, 시작열, 끝행, 끝열을 입력받는다
해당 범위 내의 사람들의 수의 합을 구하는 문제이다

2. 아이디어 (문제 접근법)
[아이디어-1]
- int answer 를 만들어 범위 내의 합을 저장한다
- for 시작행-1 ~ 끝행-1
    for 시작열-1 ~ 끝열-1
        answer += 해당 위치 값
[아이디어-2]
- 시간을 줄이는 방법
    1. (0, 0) 에서 시작하는 범위의 합을 그때그때 기록해둔다
        -> 순회 시간을 줄이기 위함
        -> sumArr[x][y]를 새로 입력받기 위해서는
            입력받는 값 + sunArr[x][y-1] + sumArr[x-1][y] - sumArr[x-1][y-1]
    2. DP 과정이 인구 수를 궁금해하는 직사각형 범위가 주어질 때가 아니라
                단위 구역 내에 살고 있는 사람 수를 입력받을 때 적용된다

이후 board 가 필요 없다고 판단하여 삭제
                    메모리        시간
board 가 있을 때 -> 123328 KB	864 ms
board 가 없을 때 -> 120120 KB	952 ms

3. 어려움 및 해결
[아이디어-1] -> 시간초과 발생
            -> 영토의 크기는 (1 ≤ N, M ≤ 1,024) 으로 잘 체감이 안되었는데
            -> 다시 확인하니 직사각형 범위의 개수 K(1 ≤ K ≤ 100,000) 였다
            -> 시간을 줄일 수 있는 방법을 고안해봄 ([아이디어-2]로)
[아이디어-2] -> sumArr[0][0]을 입력받을 때 ArrayIndexOutOfBoundsException 발생 가능하므로
            sumArr 의 크기를 int[N+1][M+1] 로 입력받아 sum[1][1]부터 입력받는다
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15724_주지수 {
    static int[][] sumArr;

    static StringBuffer sb = new StringBuffer();

    static int N;
    static int M;

    public static int dp(int startRow, int startColumn, int endRow, int endColumn){
        return sumArr[endRow][endColumn] + sumArr[startRow-1][startColumn-1] - sumArr[endRow][startColumn-1] - sumArr[startRow-1][endColumn];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        sumArr = new int[N+1][M+1];

        for(int i=1; i<=N; i++){
            stk = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++){
                sumArr[i][j] = Integer.parseInt(stk.nextToken()) + sumArr[i-1][j] + sumArr[i][j-1] - sumArr[i-1][j-1];
            }
        }

        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            stk = new StringTokenizer(br.readLine());
            sb.append(dp(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())) + "\n");
        }
        if(sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb);
    }
}
