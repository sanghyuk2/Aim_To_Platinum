/*
1. 문제 요약
- 정수 A를 B로 바꾸려고 한다. 가능한 연산은 다음과 같은 두 가지이다.
    - 2를 곱한다.
    - 1을 수의 가장 오른쪽에 추가한다.
- A를 B로 바꾸는데 필요한 연산의 최솟값을 구해보자.

2. 아이디어(문제 접근법)
- 2를 곱해서 1은 절대 나올 수 없다
- 정수 B를 2로 나누면서 마지막 자리수가 1인 경우 제거한다
- A가 나올때까지 위 과정을 반복한다

3. 어려움 및 해결방식
- 어려움) 반례 찾기
- 해결방법) 1 111 이 입력값으로 주어졌을 때 3이 나오는 반례를 통해 원하는 답을 찾음
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int cnt = 0;

        while (true) {
            if (B == A) {
                cnt++;
                break;
            }

            if (B < A) {
                cnt = -1;
                break;
            }

            if (B % 10 == 1) {
                B = B / 10;
            }else if(B % 2 == 0) {
                B = B / 2;
            }else {
                cnt = -1;
                break;
            }
            cnt++;
        }

        System.out.println(cnt);
    }
}