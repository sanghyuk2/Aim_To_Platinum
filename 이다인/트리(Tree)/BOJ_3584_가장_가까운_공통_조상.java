import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3584_가장_가까운_공통_조상 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] tree = new int[n + 1];
            boolean[] visited = new boolean[n + 1];
            int a, b;

            for (int j = 0; j < n - 1; j++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                tree[b] = a;
            }

            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            while (a != 0) {
                visited[a] = true;
                a = tree[a];
            }

            while (!visited[b]) {
                b = tree[b];
            }
            System.out.println(b);
        }
    }
}