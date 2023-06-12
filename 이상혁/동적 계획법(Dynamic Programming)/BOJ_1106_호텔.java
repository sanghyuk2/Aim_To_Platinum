/*
1. 문제 요약
- 홍보를 할 수 있는 도시와 도시별로 드는 비용, 그 때 몇 명의 호텔 고객이 늘어나는지에 대한 정보를 이용하는 문제
    - 예시 : 어떤 도시에서 9원을 들여서 홍보하면 3명의 고객이 늘어난다
- 다음과 같은 조건을 만족한다
    - 조건 : 9원을 들여서 3명의 고객, 18원을 들여서 6명의 고객, 27원을 들여서 9명의 고객을 늘어나게 할 수 있지만, 3원을 들여서 홍보해서 1명의 고객, 12원을 들여서 4명의 고객을 늘어나게 할 수는 없다

2. 아이디어

비용 : 고객 = 3 : 1을 예시로 dp 테이블을 그리면 다음과 같다

고객  0   1   2   3   4   5   6   7   8   9   10
비용  0   3   6   9   12  15  18  21  24  27  30

- 즉, dp[i] = dp[i-현재 도시 고객] + 현재 도시 고객만큼 늘어날 때 생기는 홍보비용
- 정확히 C명을 유치하는 문제가 아니기에, C + 100명까지 유치할 수 있다고 생각하고 dp 테이블의 사이즈를 C + 100의 길이로 생성
- dp 테이블을 완성시킨 후 C부터 C + 100까지의 값 중 최솟값을 찾는다

3. 어려움 및 해결방식
- 어려움 : 변수가 두 개 주어지니, dp 테이블 작성에 어려움을 겪음
- 해결방식 : 비용\고객 dp 테이블을 작성하여 해결함

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int C;
    private static int N;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        dp = new int[C + 101];
        Arrays.fill(dp, 100000);

        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int cost = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());

            for (int j = customer; j < C + 101; j++) {
                dp[j] = Math.min(dp[j], dp[j - customer] + cost);
            }
        }


        int res = Integer.MAX_VALUE;
        for (int k = C; k < C + 101; k++) {
            res = Math.min(dp[k], res);
        }
        System.out.println(res);
    }
}