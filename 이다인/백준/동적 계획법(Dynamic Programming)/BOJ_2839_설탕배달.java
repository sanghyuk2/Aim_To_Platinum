/*
 * 1. 문제 요약
 * n킬로그램을 3킬로그램 봉지와 5킬로그램 봉지로 나눌 때, 봉지의 최소 개수
 * 
 * 2. 아이디어
 * dp 배열에 각 무게 당 최소 봉지 수 저장
 * 최소 봉지 수 = 현재 무게 - 3, 현재 무게 - 5일 경우의 최소 봉지 수 + 1 중 작은 값
 * 배열의 값이 10000인 경우 정확하게 n킬로그램을 만들 수 없다는 것
 * 
 * 3. 어려움 및 해결방식
 * dp[i - 3]과 dp[i - 5]가 모두 10000인 경우를 고려하지 않아 dp[i]에 10001이 저장되는 경우가 발생
 * 현재 무게 - 3, 현재 무게 - 5일 경우 모두 최소 봉지 수가 10000이면 현재 무게도 만들 수 없기 때문에 그대로 continue
 */
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2839_설탕배달 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] dp = new int[n + 1];
        Arrays.fill(dp, 10000);
        dp[3] = 1;
        if (n >= 5) {
            dp[5] = 1;
        }

        for (int i = 6; i <= n; i++) {
            if (dp[i - 3] == 10000 && dp[i - 5] == 10000) {
                continue;
            }
            dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
        }

        if (dp[n] == 10000) {
            System.out.println(-1);
            return;
        }
        System.out.println(dp[n]);
    }
}
