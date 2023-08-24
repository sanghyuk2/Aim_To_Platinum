package aim_to_platinum.week05_greedy.b2812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
1. 문제 요약
- N과 K (1 ≤ K < N ≤ 500,000) 를 입력받는다
- N 자리 숫자 input 을 입력받으면 그 중 K 만큼의 숫자를 지운다
    - 이 때 얻을 수 있는 가장 큰 수를 구하면 되는 문제이다

2. 아이디어 (문제 접근법)
[아이디어-1]
- input 을 String 으로 입력받은 후에 char[] sorted =  Arrays.sort 로 정렬한다
    이를 통해 입력받은 숫자들이 오름차순으로 나열된다
    이후 앞에서부터 (작은 숫자들부터) K 번째까지 문자들을 순회하며 해당 문자들을 삭제한다
    같은 문자의 경우 input 에서 더 앞에 있는 문자를 지운다

[아이디어-2]
- input 을 애초에 크기 N-K의 배열 arr 에 입력받아보자
- int count = K 로 할당하고
    int pointer = 0 으로 할당하여
    count = 0 이 될 때까지 앞자리수를 최대한 큰 숫자로 세팅할 수 있도록 다음과 같이 반복한다

    1. 현재 포인터가 가리키는 수가 없으면 (0이면) 입력받은 수를 넣는다
    2. 현재 포인터가 가리키는 수와 입력받은 수를 비교하여
        2-1. 포인터가 가리키는 수가 더 크고, count>0 이면 다음 수를 입력받는다
        2-2. count<=0 이면 입력받은 수를 다음 자리에 넣는다
        2-3. 입력받은 수가 더 크고, count>0 이면 그 숫자로 교체한다

    만들다보니 배열보단 자료구조를 활용하는 것이 더 구현이 쉬울 것 같다고 생각했다
    따라서 큐를 사용하기로 했다

[아이디어-3]
- '[아이디어-2]의 어려움 및 해결' 의 해결방법 적용, 아래를 (0 < i < N-1) 만큼 반복한다
    - greedy 가 비어있지 않다면 반복하여 greedy.pop() 과 현재 입력받은 수를 비교
        - 아래를 greedy 가 isEmpty 하지 않고 count >= 0 인 동안 반복한다
            - greedy.pop() 가 더 크다면 다시 greedy.pop()을 push() 하고, 반복을 해제
            - 입력받은 수가 더 크다면 greedy.pop()을 삭제 (이미 pop 되어있으니 그냥 패스)
                - count--
    - 입력받은 수를 push() 한다
    - 만약 count <= 0 이라면
        - 뒤에 있는 남은 모든 수를 greedy 에 붙인다
        - 이후 반복을 해제

- greedy 의 값을 StringBuffer sb 에 옳겨 담는다
- sb 를 앞에서부터 N-K 크기만큼 잘라서 출력한다





3. 어려움 및 해결
[아이디어-1] -> 예제입력 3번에서 틀린 값이 출력될 것이다
            10 4 /n 4177252841 를 입력 받았을 때
            775841이 나와야 하지만 , 477584가 나온다
            그렇다면 무조건 앞자리 수를 최대로 맞추는 것에 초점을 둬보자
[아이디어-2] -> 예제입력 3번에서 틀린 값이 출력 되었다
            10 4 /n 4177252841 를 입력 받았을 때
            775841이 나와야 하지만 , 772841이 나온다
            앞자리 수를 온전히 최대로 맞추는 것에 실패한 것인데,
            이는 뒤에서 지금 삭제하는 값보다 더 작은 숫자를 입력받을 수 있는 경우를 간과한 결과이다
            따라서 이미 입력받은 수와 현재 입력받는 수를 비교하는 대신 현재 입력받는 수와 비교하여
                -> 이미 입력받은 수를 삭제하는 방식으로 진행해 보려고 한다
 */
public class BOJ_2812_크게_만들기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        char[] input = br.readLine().toCharArray();

        Stack<Character> greedy = new Stack<>();

        int count = K;

        for(int i=0; i<N; i++){
            if(!greedy.isEmpty()){
                while(!greedy.isEmpty() && count>0){
                    if(greedy.peek() < input[i]){
                        greedy.pop();
                        count--;
                    }else{
                        break;
                    }
                }
            }
            greedy.push(input[i]);

            if(count == 0) {
                for(int j=i+1; j<N; j++){
                    greedy.add(input[j]);
                }
                break;
            }
        }

        StringBuffer sb = new StringBuffer();

        int size = greedy.size();
        for(int i=0; i<size; i++){
            char pop = greedy.pop();
            sb.insert(0, pop);
        }
        sb.setLength(N-K);

        System.out.println(sb);
    }
}
