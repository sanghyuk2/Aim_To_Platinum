/*
1. 문제요약
계단에는 일정 값들이 주어진다.
이러한 계단을 다음과 같은 규칙을 따르면서 밟는다:

1) 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
2) 연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
3) 마지막 도착 계단은 반드시 밟아야 한다.

마지막 계단에 도달하였을 때, 얻을 수 있는 최댓값을 계산하시요.

2. 아이디어(문제접근법)
- 가장 중요한 점:
    1) 총 점수의 최댓값을 구하는 문제
    2) 계단을 몇 번 밟던지 상관없음

- N 번째의 계단을 밟는 경우는 다음과 같다:
    1) N - 3 을 밟고 N - 1 을 밟는 경우
    2) N - 2를 밟는 경우
즉 N - 3 까지의 최댓값 + N - 1번째 계단값과, N - 2 까지의 최댓값을 비교하면 된다.

3. 어려움 및 해결방식
어려움) 아이디어 자체를 접근하지 못했음.
해결법) 문제를 반복하여 읽어, 중요 포인트(2번 문항 - 가장 중요한 점)를 파악하여 접근함.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] stairs = new int[N + 1];
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        //1번 계단까지의 최댓값
        dp[1] = stairs[1];
        //2번 계단까지의 최댓값
        if (N >= 2) {
            dp[2] = stairs[1] + stairs[2];
        }

        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + stairs[i - 1]) + stairs[i];
        }

        System.out.println(dp[N]);
    }
}