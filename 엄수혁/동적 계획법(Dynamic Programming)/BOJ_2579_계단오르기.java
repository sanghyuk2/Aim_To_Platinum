/*
 * 1. 문제 요약
 * 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
 * 연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
 * 마지막 도착 계단은 반드시 밟아야 한다.
 *
 * 2. 아이디어
 * 최소 3층 이상부터 별개의 경우의 수가 생기기 때문에
 * 계단이 1, 2층일 경우는 별도의 예외처리를 진행해준다.
 * 계단이 3층 이상인 경우 아래와 같은 점화식을 세운다.
 * 1. 전칸을 밟고 마지막칸을 밟는 경우
 * floor(n-1) + floor(n)
 *
 * 2. 전전칸을 밟고 마지막칸을 밟는 경우
 * floor(n-2) + floor(n)
 *
 * 3. 어려움 및 해결방식
 * 한계단씩 연속해서 올라갈 수 있는 횟수는 2번
 * 따라서 해당 로직을 추가
 * 점화식 1번의 경우 : n-3 + n-1 + n
 *
 * 최종 점화식 : Math.max(dp[n-3] + floor[n-1] + n, floor[n-2] + floor[n]
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Integer floorCnt = Integer.parseInt(in.readLine());

        int[] dp = new int[floorCnt + 1];
        int[] values = new int[floorCnt + 1];

        for (int i = 1; i <= floorCnt; i++) {
            values[i] = Integer.parseInt(in.readLine());
        }

        dp[0] = 0;
        dp[1] = values[1];

        if(floorCnt >= 2){
            dp[1] = values[1] + values[2];
        }

        for (int i = 3; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 3] + values[i - 1] + values[i], dp[i - 2] + values[i]);
        }
        System.out.println(dp[floorCnt]);
    }
}