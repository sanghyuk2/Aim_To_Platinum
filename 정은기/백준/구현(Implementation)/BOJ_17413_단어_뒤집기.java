package aim_to_platinum.week11_implementation.b17413;


/*
1. 문제 요약
- 문자열 S를 입력받았을 때, 다음의 규칙을 지켜서 뒤집으면 된다
    - 단어 단위로 뒤집는다
    - '<' 와 '>'로 둘러싸인 경우 단어가 아님 태그로 인식해, 뒤집지 않는다


2. 아이디어 (문제 접근법)
[아이디어-1]
- Stack 을 사용하면 간단하게 풀 수 있을 것 같다.
- 문자열을 순회할 때 다음을 고려한다
    - '<' 문자열을 읽는 경우 StringBuilder 에 append 하고 (flag = true)
      '>' 문자열을 읽을 때까지 (flag == true 면) Stack 에 put 하지 않고 바로 StringBuilder 에 append 한다.
      '>' 문자열을 읽으면 StringBuilder 에 append 하고 (flag = false)
    - ' ' 문자열을 읽는 경우 StringBuilder 에 append 하고
      현재 Stack 을 pop 하며 StringBuilder 에 append


3. 어려움 및 해결
-
*/


import java.io.*;
import java.util.*;

public class BOJ_17413_단어_뒤집기 {

    static String reverseString(String input){
        boolean flag = false;
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        char[] array = input.toCharArray();
        for(int i = 0; i < input.length(); i++){
            char now = array[i];

            if(flag == false){
                if(now == '<'){
                    while(!stack.isEmpty()){
                        sb.append(stack.pop());
                    }
                    sb.append(now);
                    flag = true;
                }else if(now == ' '){
                    while(!stack.isEmpty()){
                        sb.append(stack.pop());
                    }
                    sb.append(now);
                }else{
                    stack.push(now);
                }
            }else{
                if(now == '>'){
                    sb.append(now);
                    flag = false;
                }else{
                    sb.append(now);
                }
            }
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = br.readLine();

        String answer = reverseString(S);

        bw.write(answer);
        bw.flush();

        bw.close();
        br.close();
    }
}
