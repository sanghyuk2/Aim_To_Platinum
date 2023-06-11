/*
1. 문제요약
첫째 줄에 수열의 길이가 주어지고, 다음 줄에는 수열을 이루는 수들이 주어진다. 주어진 수를 팰린드롬(앞 뒤가 똑같은 수)으로 만들기 위해 최소 몇개의 수를 집어넣어야 하는지 출력
입력 : N(수열의 길이)
         수열
출력 : 수열을 팰린드롬으로 만들기 위해 추가할 수의 최소 개수
2. 아이디어
수열의 앞과 뒤에서 수를 비교하면서 수가 같다면 포인터를 이동하고 수가 다르다면, 수를 추가하는 과정을 거치며 정답을 도출한다.
3. 어려움 및 해결방식
재귀법을 사용해서 문제를 풀려고 했다. 시간 초과가 나서 중복을 피하기 위해 dp[][] 배열을 추가해서 값을 저장하면서 문제를 해결했다.
 */

import java.util.Scanner;

public class BOJ_1695_팰린드롬만들기 {
    static int[] seq;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        seq = new int[N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            seq[i] = sc.nextInt();
        }

        int answer = palindrome(0, N - 1);

        System.out.println(answer);
    }

    public static int palindrome(int left, int right) {
        if (left >= right) {
            return 0;
        }

        if (dp[left][right] != 0) {
            return dp[left][right];
        }

        if (seq[left] == seq[right]) {
            dp[left][right] = palindrome(left + 1, right - 1);
        } else {
            dp[left][right] = 1 + Math.min(palindrome(left + 1, right), palindrome(left, right - 1));
        }

        return dp[left][right];
    }
}