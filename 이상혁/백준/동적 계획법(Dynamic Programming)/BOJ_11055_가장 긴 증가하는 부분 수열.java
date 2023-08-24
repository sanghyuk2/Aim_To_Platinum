/*
1. 문제 요약

수열 A가 주어졌을 때, 그 수열의 증가하는 부분 수열 중에서 합이 가장 큰 것을 구하는 프로그램을 작성하시오.

예를 들어, 수열 A = {1, 100, 2, 50, 60, 3, 5, 6, 7, 8} 인 경우에 합이 가장 큰 증가하는 부분 수열은 A = {'1', 100,'2','50','60', 3, 5, 6, 7, 8} 이고, 합은 113이다.

2. 아이디어(문제 접근법)

인덱스에 따른 수열 및 dp 테이블을 아래와 같이 작성해본다

1   2   3   4   5   6   7   8   9   10
1   100 2   50  60  3   5   6   7   8
1   101 3   53  113 6   11  17  24  32

점화식을 찾으면 현재 dp[i]의 값은 A[i] +dp[j]임을 확인할 수 있다(여기서 j는 A[j] < A[i] 임을 의미한다)

이 점화식을 사용하여 문제를 해결한다

3. 어려움 및 해결방식

어려움) dp테이블을 초기화 할 필요가 없다 생각했는데, 만일 현재 인덱스의 수열 값인 A[i]가 이전의 어떤 값보다 작다면 dp[i]는 A[i]의 값을 가져야하는데 0이 되어버린다.

해결방법) dp[i] = A[i]
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
        dp = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            0;dp[i] = A[i];
            for (int j =  j < i; j++) {
                if (A[j] < A[i]) {
                    dp[i] = Math.max(dp[i], A[i] + dp[j]);
                }
            }
        }

        int max = Arrays.stream(dp).max().getAsInt();
        System.out.println(max);
    }
}