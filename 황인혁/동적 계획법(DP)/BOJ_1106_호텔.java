/*
1. 문제요약
목표고객 수를 달성하기 위해 주어진 도시의 개수애서 홍보 할 때 필요한 최소 홍보 비용을 구하는 문제
입력 : C(목표고객수) N(도시의 개수)
        cost(도시별 홍보비용) cust(도시별 홍보시 예상고객)
         N개의 도시정보가 이어짐
출력 : 목표고객을 채우기 위한 최소 홍보비용
조건 :
2. 아이디어(문제접근법)
목표 고객수 달성을 위한 최소 홍보 비용 저장배열(int[] dp)을 만들고 각 도시를 순회하며 최소 홍보 비용을 저장하고 도시에서 cust명의 고객을 확보하기 위해 필요한 비용인 cost를 추가해준다.
마지막으로 목표 고객수(dp[C]) 출력 하면 끝
3. 어려움 및 해결방식
”적어도 C명, 이 값은 100보다 작거나 같은 자연수이다.” 이라는 문구를 발견하지 못해서 오래 걸렸다. 목표 고객이 C명인 것으로 이해해서 오래 걸렸다.
⇒ 배열의 크기를 C + 101로 늘려서 여러가지의 경우의 수를 찾아냈다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_1106_호텔{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] dp = new int[C + 101];
        Arrays.fill(dp, 100000);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st1.nextToken());
            int cust = Integer.parseInt(st1.nextToken());
            for (int j = cust; j < C + 101; j++) {
                dp[j] = Math.min(dp[j], dp[j - cust] + cost);
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = C; i < C + 101; i++) {
            result = Math.min(result, dp[i]);
        }
        System.out.println(result);
    }
}