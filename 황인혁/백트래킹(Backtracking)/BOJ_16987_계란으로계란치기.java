import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bk_16987 {
    static int N;
    static int[][] eggs;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }

        dfs(0);

        System.out.println(max);
    }

    public static void dfs(int idx) {
        if (idx == N) {
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                if (eggs[i][0] <= 0) {
                    cnt++;
                }
            }
            max = Math.max(max, cnt);
            return;
        }

        if (eggs[idx][0] <= 0) {
            dfs(idx + 1);
            return;
        }

        boolean isBroken = false;
        for (int i = 0; i < N; i++) {
            if (idx == i || eggs[i][0] <= 0) {
                continue;
            }
            isBroken = true;
            eggs[idx][0] -= eggs[i][1];
            eggs[i][0] -= eggs[idx][1];

            dfs(idx + 1);

            eggs[idx][0] += eggs[i][1];
            eggs[i][0] += eggs[idx][1];
        }

        if (!isBroken) {
            dfs(idx + 1);
        }
    }
}