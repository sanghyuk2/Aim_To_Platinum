package aim_to_platinum.week04_DP2.b5557;

/*
1. 문제 요약
- 제한시간 1초
- 0~9 범위의 숫자 3~100 개가 주어지고
    오른쪽 끝의 숫자의 앞에 = 를 붙여 답으로,
    그 외의 숫자들 사이에 '+' 나 '-' 를 붙여 올바른 등식을 만들어야 한다
- '+' 혹은 '-' 만 사용하므로 왼쪽에서 오른쪽 순으로만 확인하면 된다
- 중간에 값이 0~20 범위를 벗어나면 올바르지 않은 공식으로 취급받는다

2. 아이디어 (문제 접근법)
[아이디어-1]
- 태블릿 그림을 통해 풀었다
- arr[N] 에 입력받은 숫자들 기록
- result = arr[N-1] -> {...} = arr[N-1]
- int[][] dp = new int[N-1][21]
    - for(i = 1~N-2)
        for(j = 0~20)
            반복문 적용 시,
            i => arr[i] 를 사용하여
            j => 를 만들 수 있는지 순회하는 것
- (1) dp[0][arr[0]] = 1 로 적용해놓고 시작한다 (어차피 처음은 무조건 하나)
- (2) 이후 for(i = 1~N-2)
            for(j = 0~20)
    - if(dp[i-1][j] == 1) 이면 다음을 실행한다
    - 0 <= j-arr[i], j+arr[i] <= 20 인지 체크
    - 문제 없는 경우 dp[i][j-arr[i]] 와 dp[i][j+arr[i]] 에 += dp[i-1][j]
- (3) 이를 반복
- 이차원 배열 DP[i][j] 의 값은 arr[i] 를 사용했을 때 j 를 만들 수 있는 경우의 수를 의미하므로
    - DP[N-2][arr[N-1]] 를 출력하면 된다

3. 어려움 및 해결
- 딱 봐도 DFS 로 접근하면 시간초과가 날듯 하여 다른 방법을 생각해 보았다
    (최대 99 개의 숫자들 사이에 '+' 혹은 '-' 를 붙여야 하므로 대충 계산해도 2^98)
[아이디어-1] -> 답이 int 의 범위를 벗어나는 일이 생겨서 Long 자료형으로 변경했다
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5557_1학년 {

    static int N;
    static int[] arr;
    static long[][] DP;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        DP = new long[N-1][21];

        stk = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        result = arr[N-1];

        DP[0][arr[0]] = 1;
        for(int i=1; i<=N-2; i++){
            for(int j=0; j<=20; j++){
                if(DP[i-1][j] != 0){
                    int plus = j+arr[i];
                    int minus = j-arr[i];
                    if(0<=plus && plus<=20){
                        DP[i][plus] += DP[i-1][j];
                    }
                    if(0<=minus && minus<=20){
                        DP[i][minus] += DP[i-1][j];
                    }
                }
            }
        }

//        for(int i=0; i<N-1; i++){
//            for(int j=0; j<=20; j++){
//                System.out.print(DP[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println();

        System.out.println(DP[N-2][arr[N-1]]);
    }
}
