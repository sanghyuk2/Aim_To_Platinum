/*
1. 문제요약
준서가 버틸수 있는 무게에서 준서가 가질 수 있는 물건 가치의 최대 합을 출력하는 문제
입력 : N(물건의 개수) K(버틸수 있는 무게)
          W(물건의 무게) V(물건의 가치)
출력 : 배낭에 넣을 수 있는 물건의 가치합의 최댓값
2. 아이디어
각 물건들의 가치를 저장하고 버틸수 있는 무게를 기준점으로 잡아서 dp 배열을 만들고 idx값을 증가시키면서 각 무게마다 최대 가치를 저장한 후 출력한다.
3. 어려움 및 해결방식 X
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BOJ_12865_평범한배낭{

    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[][] dp = new int[N+1][K+1];

        int[] weight = new int[N+1];
        int[] value = new int[N+1];
        for(int i = 1; i <= N; i++) {
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= K; j++) {
                if(weight[i] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]] + value[i]);
                }
            }
        }

        System.out.println(dp[N][K]);

    }

}