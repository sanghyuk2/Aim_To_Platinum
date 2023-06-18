/*
1. 문제이해
헬스장에서 운동을 할 때 최대 2개의 운동기구를 사용할 수 있을 때 주어진 헬스기구를 전부 사용한다고 했을 때 하루 최소 근손실정도를 출력하는 문제
입력 : N(운동기구 개수)
          N개 기구별 근손실 정도
출력 : M(최소 근손실)
조건 : 기구를 모두 사용해야 한다.
           하루 최대 2개를 사용할 수 있다.
2. 아이디어
기구의 개수가 홀수인 경우와 짝수인 경우를 생각한다.
홀수인 경우는 마지막에 한 개의 기구를 단독으로 사용해야 된다.
주어진 근손실 정도를 정렬하고 문제를 푼다. (작은 값과 큰 값의 합이 제일 최소 결과 값이다.)
3. 어려움 및 해결방식
입력으로 주어지는 값은 범위를 잘 확인하자.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20300_서강근육맨{
    static long[] lossArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        lossArr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lossArr[i] = Long.parseLong(st.nextToken());
        }

        long min_loss = lossMin(N, lossArr);
        System.out.println(min_loss);
    }

    public static long lossMin(int N, long[] lossArr) {
        Arrays.sort(lossArr);
        long minLoss = 0;
        int left = 0;
        int right = N - 1;

        if (N % 2 == 1) {
            minLoss = lossArr[right];
            right--;
        }

        while (left < right) {
            long sumLoss = lossArr[left] + lossArr[right];
            minLoss = Math.max(minLoss, sumLoss);
            left++;
            right--;
        }

        return minLoss;
    }
}