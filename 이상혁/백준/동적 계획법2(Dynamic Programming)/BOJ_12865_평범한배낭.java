/*
1. 문제 요약

- 무게 W와 가치 V를 가지는 물건 N개가 있다
- 가방에는 최대 K 만큼의 무게를 넣을 수 있다
- 가방에 넣을 수 있는 물건들의 가치의 최대값을 구해보자

2. 아이디어(문제 접근법)

- 물건의 무게 W가 3, 가치 V가 6인 물건을 가지고 dp 테이블을 그리면 아래와 같다

무게  0   1   2   3   4   5   6   7   8   9   10
비용  0   0   0   6   6   6   12  12  12  18  18

- 무게가 i 일때 물건의 가치 최대값은 i - 물건의 무게 번째의 최대 무게 + 물건의 가치 이다.
- 즉, dp[i] = dp[i - weight] + value 이다

3. 어려움 및 해결 방식

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] dp = new int[100001];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            for (int j = K; j - weight >= 0; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight] + value);
            }
        }

        System.out.println(dp[K]);
    }
}