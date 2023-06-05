/*
1. 문제요약
입력 : n(동전의 개수), k(가치, 동전의 조합으로 만들어야 하는 값)
         이어서 각 동전의 가치가 주어짐
출력 : k를 만들기 위해 사용한 동전의 최소 개수(출력이 불가능한 경우는 -1을 출력한다)
조건 : 사용한 동전의 구성이 같고, 순서가 다른 경우는 같은 경우이다.
2. 아이디어(문제접근법)
각 동전들의 가치를 배열에 저장해서 각 가치를 만들기 위한 최소 동전의 개수를 저장할 배열을 만들어서 배열에 idx마다 +1을 하며 값을 저장한다.
3. 어려움 및 해결방식
 */

import java.util.Scanner;

public class bk_2294 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        int[] dp = new int[k + 1];
        Arrays.fill(dp, k +1);
        dp[0] = 0;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                if(coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        System.out.println(dp[k] >  k ? -1 : dp[k] );
    }
}