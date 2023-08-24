/*
1. 문제 요약
n 종류의 동전과, 동전 가치의 합이 k원이 되게 하는 최소 동전 개수를 구하는 문제이다.

2. 아이디어
dp[15]의 값을 만들기 위해 사용되는 최소 동전 개수

                1   2   3   4   5   6   7   8   9   10  11  12  13  14  15
coin[1] : 1     1   2   3   4   5   6   7   8   9   10  11  12  13  14  15
coin[2] : 5     1   2   3   4   1   2   3   4   5   2   3   4   5   6   3
coin[3] : 12    1   2   3   4   1   2   3   4   5   2   3   1   2   3   4

dp[1], dp[5], dp[12]의 경우 현재 index - 동전의 value + 1
즉, 이전에 사용한 동전의 value 로 얻은 최소 동전 갯수를 다음 동전의 value로 덧씌우는 작업을 거친다.

3. 어려움 및 해결방식
어려움) 점화식 작성하는 방식에 어려움을 겪었다
해결법) 날로 먹으려 하지 말고, 하나 하나 다 적어보면서 경험해보자.

 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n + 1];
        int[] dp = new int[k + 1];
        Arrays.fill(dp, 100001);

        for (int i = 1; i < n + 1; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = coins[i]; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }

        System.out.println(dp[k] == 100001 ? -1 : dp[k]);
    }
}