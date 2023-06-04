/*
 * 1. 문제 요약
 * target을 1,2,3의 조합으로 만들어 낼 수 있는 총 경우의 수
 *
 * 2. 아이디어
 * 규칙성이 있을거라 판단하여 패턴을 먼저 찾음
 * target = 1 : 1
 * target = 2 : 2
 * target = 3 : 4
 * target = 4 : 7
 * 점화식 : dp[n] = dp[n-1] + dp[n-2] + dp[n-3];
 *
 * 3. 어려움 및 해결방식
 * 아이디어와 동일하다
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] dp = new int[11];

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Integer tc = Integer.parseInt(in.readLine());

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        for(int i = 0; i < tc; i++){
            sb.append(dp[Integer.parseInt(in.readLine())]).append("\n");
        }
        System.out.println(sb.toString());
    }
}