import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2812_크게_만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String num = br.readLine();
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);

            while (!stack.isEmpty() && c > stack.peek() && k > 0) {
                stack.pop();
                k -= 1;
            }
            stack.add(c);
        }

        if (k != 0) {
            for (int i = 0; i < k; i++) {
                stack.pop();
            }
        }

        StringBuilder sb = new StringBuilder();
        stack.forEach(c -> sb.append(c));
        System.out.println(sb.toString());
    }
}