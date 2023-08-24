/*
 * 1. 문제 요약
 * 2×n 직사각형을 1×2, 2×1과 2×2 타일로 채우는 총 경우의 수
 *
 * 2. 아이디어
 * 패턴을 먼저 찾는다.
 * n = 1 : 1
 * n = 2 : 3
 * n = 3 : 5
 * n = 4 : 11
 * 점화식 dp[n] = dp[n-1] + 2 * dp[n-2]
 *
 * 3. 어려움 및 해결방식
 * 메모이제이션 : bottom-up
 * 없음
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] dp = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Integer N = Integer.parseInt(in.readLine());

        dp[1] = 1;
        dp[2] = 3;

        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10007;
        }
        System.out.println(dp[N]);
    }
}