import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20300_서강근육맨 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] list = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            list[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(list);

        long m = 0;
        if (n % 2 == 1) {
            m = list[n - 1];
            n -= 1;
        }

        for (int i = 0; i < n; i++) {
            long temp = list[i] + list[n - i - 1];
            if (temp > m) {
                m = temp;
            }
        }
        
        System.out.println(m);

    }
}