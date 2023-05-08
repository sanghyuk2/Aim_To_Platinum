import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10971_외판원순회2 {
    static int N;
    static int[][] route;
    static boolean[] visited;
    static int routeMin = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        route = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                route[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            visited[i] = true;
            dfs(i, i, 0, 0);
        }

        System.out.println(routeMin);
    }

    public static void dfs(int start, int now, int sum, int cnt) {

        if (cnt == N - 1) {
            if (route[now][start] != 0) {
                sum += route[now][start];
                routeMin = Math.min(sum, routeMin);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i] && route[now][i] > 0) {
                visited[i] = true;
                dfs(start, i, sum + route[now][i], cnt+1);//이부분 후위연산자는 왜 안되지?
                visited[i] = false;
            }
        }
    }
}