import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * 1. 문제 요약
 * 주어진 N자리 수열에서 숫자 K개를 지워서 얻을 수 있는 가장 큰 수
 *
 * 2. 아이디어
 * 데크를 통해 문자 비교열을 해나가며 k--를 통해 현재 지워진 숫자를 확인
 * 이 과정 중 못 지워져서 K가 남은 경우
 * 그 만큼 데크의 마지막 부분에서 요소를 삭제한다.
 * */
public class B_2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());
        Deque<Character> deque = new LinkedList<>();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[] values = in.readLine().toCharArray();

        for (int i = 0; i < values.length; i++) {
            while (K > 0 && !deque.isEmpty() && deque.getLast() < values[i]) {
                deque.removeLast();
                K--;
            }
            deque.add(values[i]);
        }

        while(K > 0) {
            deque.removeLast();
            K--;
        }

        while(!deque.isEmpty()){
            sb.append(deque.poll());
        }

        System.out.println(sb.toString());
    }
}
