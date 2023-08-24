
/*
 * 1. 문제 요약
 * 설탕공장에서 만드는 설탕은 봉지에 담겨져 있다. 봉지는 3킬로그램 봉지와 5킬로그램 봉지가 있다.
 * 상근이는 귀찮기 때문에, 최대한 적은 봉지를 들고 가려고 한다.
 * 원하는 무게를 정확하게 가져가는데 있어, 최소한으로 필요한 봉투 수를 구해라.
 *
 * 2. 아이디어
 * 5로 나누어떨어지는 숫자가 나올 떄까지 반복을 통해 target - 3을 수행한다.
 * N < 0이 되는 target의 무게를 만들 수 없다고 판단하여 바로 -1을 반환
 *
 * 3. 어려움 및 해결방식
 * 없음
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int res = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        while (N > 0) {
            if(N % 5 == 0){
                res += N/5;
                break;
            }

            N -= 3;
            res++;
        }
        System.out.println(N < 0 ? -1 : res);
    }
}
