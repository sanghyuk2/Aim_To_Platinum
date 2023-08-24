import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1956_운동 {
    static final int INF = 1000000000;
    static int v, e;
    static int[][] graph;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        graph = new int[v + 1][v + 1];
        
        for (int i = 0; i < v + 1; i++) {
            for (int j = 0; j < v + 1; j++) {
                graph[i][j] = INF;
            }
        }

        for (int i = 1; i < e + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a][b] = c;
        }

        System.out.println(floyd());
    }

    static int floyd() {
        for (int k = 1; k < v + 1; k++) {
            for (int a = 1; a < v + 1; a++) {
                for (int b = 1; b < v + 1; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }

        int min_cycle = INF;
        for (int i = 1; i < v + 1; i++) {
            min_cycle = Math.min(min_cycle, graph[i][i]);
        }

        return min_cycle == INF ? -1 : min_cycle;
    }
}