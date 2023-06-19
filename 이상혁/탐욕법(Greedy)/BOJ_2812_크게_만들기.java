/*
1. 문제 요약
- N자리 숫자가 주어졌을 때, 여기서 숫자 K개를 지워서 얻을 수 있는 가장 큰 수를 구하는 프로그램을 작성하시오
- 예시
    입력 : 
    4 2
    1924
    
    출력 : 
    94
    
    입력 : 
    7 3
    1231234
    
    출력 : 
    3234
    
    입력 :
    10 4
    4177252841
    
    출력 :
    775841
    
2. 아이디어(문제 접근법)
- 앞 뒤로 숫자를 빼야하는데, Stack이나 Queue의 경우 FIFO(First In First Out), LILO(Last In Last Out) 이기에 원하는대로 구현이 안된다
- 이에 Stack과 Queue의 성질을 가지고 있는 자료구조 Deque를 사용하여 구현하였다
1. 입력받은 숫자의 앞자리 숫자부터 Deque에 집어넣는다
2. 다음 숫자와 Deque에 있는 숫자를 비교해서 만일 다음 숫자가 더 크다면 K를 감소시키고 Deque의 숫자를 대체한다
3. 만일 K가 0이면 남은 숫자를 Deque에 다 집어 넣는다
4. 만일 숫자를 Deque에 다 집어넣었는데도 불구하고 K가 0이 아니라면, 마지막으로 Deque에 들어간 숫자부터 남은 K만큼 뺀다
5. Deque의 숫자를 출력한다

3. 어려움 및 해결방식
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String number = br.readLine();
        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            char current = number.charAt(i);

            while (K > 0 && !deque.isEmpty() && deque.peekLast() < current) {
                deque.pollLast();
                K--;
            }

            deque.addLast(current);
        }

        while (K > 0) {
            deque.pollLast();
            K--;
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }

        System.out.println(sb.toString());
    }
}
