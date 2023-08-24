/*
* 1. 문제 요약
* n가지 종류의 동전을 이용하여 목표 target을 만드는데 드는 최소 개수를 구하시오
*
* 2. 아이디어
* dp테이블을 각 코인별로 점진적으로 그린다.
*
* 3. 어려움 및 해결방식
* 메모이제이션 : bottom-top
* 없음
*/
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        int[] dp = new int[target+1];

        for(int i = 0; i < coins.length; i++){
            coins[i] = Integer.parseInt(in.readLine());
        }

        Arrays.fill(dp, 10001);


        dp[0] = 0;
        for(int i = 0; i < N; i++){
            for(int j = coins[i]; j < dp.length; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }

        System.out.println(dp[target] == 10001 ? "-1" : dp[target]);
    }
}