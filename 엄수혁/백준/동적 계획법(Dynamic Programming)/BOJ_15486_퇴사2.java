/*
* 1. 문제 요약
*  N+1일째 되는 날 퇴사를 하기 위해서, 남은 N일 동안 최대한 많은 상담을 통해 얻을 수 있는 최대 수익을 구하는 프로그램을 작성
* T[i] : 상담을 하는데 걸리는 기간
* P[i] : 받을 수 있는 금액
* 유의 조건 : 상담이 끝나는 날이 퇴사 이후라면 그 상담은 할 수 없다.
*
* 2. 아이디어
* dp[1]은 1일전까지의 최대 수익으로 0원이다 => dp[1] = 0
* 1일날 상담을 하게 된다면 dp[1 + T[1]]의 최대 수익은 10원이다 => dp[4] = 10
*
* dp[2]는 2일전까지의 최대 수익으로 0원이다 => dp[2] = 0
* 2일날 상담을 하게 된다면 dp[2 + T[2]]의 최대 수익은 10원이다 => dp[7] = 20
*
* dp[3]는 3일전까지의 최대 수익으로 0원이다 => dp[3] = 0
* 3일날 상담을 하게 된다면 dp[3 + T[3]]의 최대 수익은 10원이다
* => 현재 dp[4] = 10이기 때문에 갱신 x
*
* dp[4]는 4일전까지의 최대 수익으로 현재 10원이다 => dp[4] = 10
* 4일날 상담을 하게 된다면 dp[4 + T[4]]의 최대 수익은?
* => 기존의 dp[5]와 dp[4]까지의 최대 수익 + 4일날 상담하는 비용
* => dp[5] = Math.max(0, 10 + P[4]) = 30
*
* 3. 어려움 및 해결방식
* 메모이제이션 : bottom_up
* 없음
*/

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(in.readLine());

        int[] dp = new int[N+2];
        int[] T = new int[N+2];
        int[] P = new int[N+2];

        for(int i=1; i <= N; i++) {
            st = new StringTokenizer(in.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int max = Integer.MIN_VALUE;
        for (int i=1; i < T.length; i++) {
            max = Math.max(max, dp[i]);
            int day = i + T[i];

            if(day < dp.length){
                dp[day] = Math.max(dp[day], max + P[i]);
            }
        }
        System.out.println(Arrays.stream(dp).max().orElse(0));
    }
}