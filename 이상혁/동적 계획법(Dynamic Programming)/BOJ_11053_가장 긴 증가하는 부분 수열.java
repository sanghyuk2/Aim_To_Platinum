/*
1. 문제 요약

- 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
- 예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {`10`, `20`, 10, `30`, 20, `50`} 이고, 길이는 4이다.

2. 아이디어(문제 접근법)

- 최장 부분 수열을 구하는 문제이다
- A 수열을 배열로 나타내었을 때 현재 수 A[i]가 이전 수 A[j]보다 큰 경우 dp[i]에는 dp[j]의 경우에 1을 더하면 된다

3. 어려움 및 해결방식

- 어려움) 계속해서 값이 1이 작은 수가 나왔다
- 해결방법) 현재 저장하는 수의 길이가 1임을 까먹고 있었다. 이에 dp 값을 1로 초기화 해주니 해결되었다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] A;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N];
        int max = 1;
        Arrays.fill(dp, max);

        for(int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        max = Arrays.stream(dp).max().getAsInt();
        System.out.println(max);

    }
}