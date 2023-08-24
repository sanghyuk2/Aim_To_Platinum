/*
1. 문제요약

- 정수가 주어지면, 그 수의 정수 제곱근을 구하는 프로그램을 작성하시오.
- 첫째 줄에 q2 ≥ n인 가장 작은 음이 아닌 정수 q를 출력한다.

2. 아이디어(문제접근법)

- 아이디어1) Math.sqrt()를 사용하면 쉽게 구할 수 있다
- 아이디어2) 이진 탐색을 사용하여 조건에 맞는 가장 작은 수를 찾는다

3. 어려움 및 해결 방식

- 어려움) 아이디어2에서 `Math.pow()` 의 반환값이 double 형이다. 이에 명시적 형변환을 통해 long으로 변경을 해 주니 아래와 같은 반례가 생긴다
    - `입력` : 999999998000000002
    - `출력` : 999999999
    - `나의 출력물` : 1000000000
- 해결방법) 명시적 형 변환을 통해 누락된 값을 놓치지 않기 위해 (long) 을 제거하였다
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        long start = 0;
        long end = n;
        long result = 0;

        while (start <= end) {
            long mid = (start + end) / 2;

            if (n <= Math.pow(mid, 2)) {
                result = mid;
                end = mid - 1;
            }else {
                start = mid + 1;
            }
        }

        System.out.print(result);
    }
}