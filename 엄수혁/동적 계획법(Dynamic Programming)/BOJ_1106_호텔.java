/*
* 1. 문제 요약
* 각 도시에는 무한 명의 잠재적인 고객이 있다. 이때, 호텔의 고객을 적어도 C명 늘이기 위해 형택이가 투자해야 하는 돈의 최솟값을 구하는 프로그램을 작성
*
* 2. 아이디어
* 원하는 잠재고객의 수를 정확하게 맞출 필요가 없음, 즉 원하는 잠재고객 이상이기만 하면 된다.
* 잠재 고객의 수는 1<= k <= 100이므로 dp테이블의 size = target + 101
* 도시별 순서대로 특정 고객의 수를 유치하는데 드는 가장 낮은 비용을 책정한다.
* 도시별 cost : 비용 / people : 유치고객
* 점화식 : dp[i] = Math.min(dp[i], cost + dp[i - people])
*
* 3. 어려움 및 해결방식
* 메모이제이션 : bottom-top
* 잘못된 문제로 인한 어려움
* 도시별 비용당 고객 유치비를 따져 솔팅 후 bottom-top 방식으로 구현했었다.
*/

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in))){
            StringTokenizer st = new StringTokenizer(in.readLine());

            Integer customerC = Integer.parseInt(st.nextToken());
            Integer cityC = Integer.parseInt(st.nextToken());

            int[] dp = new int[customerC + 101];
            Arrays.fill(dp, 10000);
            dp[0] = 0;

            for (int i = 0; i < cityC; i++) {
                st = new StringTokenizer(in.readLine());

                int cost = Integer.parseInt(st.nextToken());
                int people = Integer.parseInt(st.nextToken());

                for (int j = people; j < customerC + 101; j++) {
                    dp[j] = Math.min(dp[j], cost + dp[j - people]);
                }
            }

            int res = Integer.MAX_VALUE;
            for (int i = customerC; i < customerC + 101; i++) {
                res = Math.min(dp[i], res);
            }

            System.out.println(res);
        }
    }
}