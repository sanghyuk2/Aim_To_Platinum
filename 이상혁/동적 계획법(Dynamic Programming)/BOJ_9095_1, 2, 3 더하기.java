/*
1. 문제 요약

정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 7가지가 있다. 합을 나타낼 때는 수를 1개 이상 사용해야 한다.

- 1+1+1+1
- 1+1+2
- 1+2+1
- 2+1+1
- 2+2
- 1+3
- 3+1

정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.

2. 아이디어(문제 접근법)

- 하나씩 다 계산해보자
- DP의 가장 큰 특징은 문제를 작은 문제로 쪼갤 수 있어야 한다. 하지만 현재 내가 알고 있는 수는 1,2,3 뿐이다.
- 이를 이용하여 문제를 풀어보자.
- 4의 경우 1 + 3, 2 + 2, 3 + 1 이다.
- 이를 풀어서 적으면, 1 + dp[3], 2 + dp[2], 3 + dp[1] 이다. 즉, 4 + 2 + 1 이다.
- 5의 경우도 마찬가지이다. 1 + 4, 2 + 3, 3 + 2, 즉, 7 + 4 + 2 이다.

3. 어려움 및 해결방식

- X
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        dp = new int[11];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i < 11; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            System.out.println(dp[n]);
        }

    }
}