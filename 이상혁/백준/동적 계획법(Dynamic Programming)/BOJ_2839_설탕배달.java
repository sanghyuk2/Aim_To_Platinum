/*
 * 1. 문제 요약
 * N 킬로그램의 설탕을 5 킬로그램과 3킬로그램의 봉지를 최대한 적게 사용하여 배달하는 문제이다.
 * 배달하는 봉지의 최소 개수를 출력하고, 정확하에 N 킬로그램을 만들 수 없다면 -1을 출력하라.
 *
 * 2. 아이디어
 * 5의 배수가 될 때 까지 3씩 차감하였다.
 *
 * 3. 어려움 및 해결방식
 * 어려움) `최소한` 이라는 단어에 집착하여 계속하여 5로 먼저 나누려고 하였다.
 * 해결) 3을 계속하여 차감함으로써 5의 배수가 되었을 때 N을 5로 나눈 값을 최종 답안에 더해주었다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private final static int FIVE_KG = 5;
    private final static int THREE_KG = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        while (N % FIVE_KG != 0) {
            if (N < 0) {
                answer = -1;
                break;
            }

            N -= THREE_KG;
            answer++;
        }

        if (N % FIVE_KG == 0) {
            answer += N / FIVE_KG;
        }

        System.out.println(answer);

    }
}
