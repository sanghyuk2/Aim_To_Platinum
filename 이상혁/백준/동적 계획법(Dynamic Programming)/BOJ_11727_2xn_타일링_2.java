/*
1. 문제 요약
- 2×n 직사각형을 1×2, 2×1과 2×2 타일로 채우는 방법의 수를 구하는 프로그램을 작성

2. 아이디어
- 1. 왼쪽부터 i - 1 까지 타일이 덮여있다면, 2 X 1 타일을 채우는 하나의 경우밖에 없다
- 2. 왼쪽부터 i - 2 까지 타일이 덮여있따면, 1 X 2 타일을 2개를 채우는 방법과 2 X 2 타일을 덮는 두 가지 방법이 있다
    - 2 X 1 타일 두 개를 덮는 경우를 제한 이유는,  1의 경우에서 이미 고려되었다
점화식 : dp[i] = dp[i-1] + dp[i-2]*2

3. 어려움 및 해결방식
- 어려움) dp[i] = dp[i-1] + 1을 이해하는데 어려움을 겪음
- 해결방식) dp[i]는 i를 만들기 위한 수의 조합, 그리고 dp[1]은 0임을 이해함
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[1001];

        dp[1] = 1;
        dp[2] = 3;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]*2) % 10007;
        }

        System.out.println(dp[n]);
    }
}
