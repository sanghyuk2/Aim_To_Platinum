/*
1. 문제이해
N자리의 숫자에 K개의 숫자를 지워서 가장 큰 수를 출력
입력 : N(자릿수) K(지워야 되는 개수)
          주어진 수
출력 : 가장 큰 수
2. 아이디어
들어오는 순서로 크기를  비교하며 K개를 제외한 수를 스택에 집어넣은 후 출력한다.
현재 스택에 있는 값이 들어올 값보다 크거나 같으면 push 작으면 pop
3. 어려움 및 해결방식
예제는 다 통과 하도록 만들었는데 계속 70%에서 통과하지 못했다.
값이 똑같은 경우 delCnt가 중복되는 숫자가 삭제되지 않아서 통과하지 못하는 경우를 찾고
for문을 빠져나와 뒤에 중복되는 숫자부분을 제거해주는 처리를 했다.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2812_크게_만들기{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        String number = br.readLine();
        Stack<Character> stack = new Stack<>();
        int delCnt = 0;

        for (int i = 0; i < N; i++) {
            char num = number.charAt(i);

            while (!stack.isEmpty() && delCnt < K && stack.peek() < num) {
                stack.pop();
                delCnt++;
            }

            stack.push(num);
        }

        while (delCnt < K) {
            stack.pop();
            delCnt++;
        }

        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }

        System.out.println(sb);

        br.close();
    }