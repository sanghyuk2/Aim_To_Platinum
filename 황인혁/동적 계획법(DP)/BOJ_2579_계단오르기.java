/*
1. 문제요약
입력 : n(계단의 수)
         이어서 계단들의 점수
출력 : dp[n](계단을 오를때 최대값)
조건
- 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
- 연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
- 마지막 도착 계단은 반드시 밟아야 한다.(꼭 첫 번째 계단을 밟을 필요는 없다.)
2. 아이디어(문제접근법)
첫 번째 계단을 밟으면 다음에 밟을 수 있는 계단은 다음 계단 또는 다다음 계단이니 둘 중에 큰 값을 저장하는 방식으로 배열에 저장해서 마지막 계단을 밟을 때의 값을 출력한다.
3. 어려움 및 해결방식 X
 */

import java.util.Scanner;

public class BOJ_2579_계단오르기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int [300+1];
        int[] dp = new int [300+1];

        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }

        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];
        dp[3] = Math.max(arr[1], arr[2]) + arr[3];

        for (int i = 4; i <= n ; i++) {
            dp[i] = Math.max(dp[i-3] + arr[i-1], dp[i-2]) + arr[i];
        }

        System.out.println(dp[n]);
    }
}