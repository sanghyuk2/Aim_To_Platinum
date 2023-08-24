/*
1. 문제 요약
- 줄 지어진 숫자 중 마지막 두 숫자 사이에 `=`를 넣고, 나머지 숫자 사이에는 '+' 또는 '-'를 넣어 올바른 등식을 만들려고 한다
- 학교에서 음수를 배우지 않았고, 20을 넘는 수는 모른다. 즉, 왼쪽부터 계산할 때, 중간에 나오는 수가 모드 0이상 20 이하이어야 한다.
- 숫자가 주어졌을 때, 상근이가 만들 수 있는 올바른 등식의 수를 구하는 프로그램을 작성하시오.

2. 아이디어(문제접근법)

- dp 테이블을 그리면 아래와 같다

        1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20
8       0   0   0   0   0   0   0   1   0   0   0   0   0   0   0   0   0   0   0   0
8,3     0   0   0   0   1   0   0   0   0   0   1   0   0   0   0   0   0   0   0   0
8,3,2   0   0   1   0   0   0   1   0   1   0   0   0   1   0   0   0   0   0   0   0


dp[i][j]는 i번째 숫자까지 사용하여 j를 만들 수 있는 경우의 수

dp[i][j] = dp[i-1][j + numbers[i]] or dp[i-1][j - numbers[i]]

3. 어려움 및 해결 방식
- 어려움) 2차원 배열을 생각하는게 어려웠음
- 해결방식) 각 단계 별, 진행 가능한 방향(+, -)가 두군데인걸 확인하여, 2차원 배열을 생각해냄
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Reflection {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int target = numbers[N - 1];
        long[][] dp = new long[N - 1][21];

        dp[0][numbers[0]] = 1;

        for (int i = 1; i < N - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i - 1][j] > 0) {
                    int sum = j + numbers[i];
                    int subtract = j - numbers[i];

                    if (sum >= 0 && sum <= 20) {
                        dp[i][sum] += dp[i - 1][j];
                    }
                    if (subtract >= 0 && subtract <= 20) {
                        dp[i][subtract] += dp[i - 1][j];
                    }
                }
            }
        }

        System.out.println(dp[N - 2][target]);

    }
}