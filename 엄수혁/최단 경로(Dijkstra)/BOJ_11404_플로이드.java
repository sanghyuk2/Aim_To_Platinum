import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1. 문제 이해
 * 각 버스는 최대 한번만 이용이 가능하며 A에서 B로 이동하는데 드는 최소 비용 표지판을 만들어라
 *
 * 2. 아이디어
 * 플로이드-워샬 알고리즘의 풀이
 * 전체를 탐색하면서 map[i][k]와 map[j][k]가 연결될 수 있을 때
 * 즉, k를 거쳐서 연결될 수 있으면 map[i][j]의 값을 map[i][k] + map[k][j]의 의 값으로 교체한다.
 * 여기서, 기존에 있던 map[i][j]의 값이 새로운 map[i][k] + map[k][j]의 값 보다 클 경우 map[i][j]의 값을 다시 갱신한다.
 * */
public class BOJ_11404_플로이드 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int INF = 1000000000;

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());
        int[][] route = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    route[i][j] = 0;
                    continue;
                }
                route[i][j] = INF;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            // 출발 도시, 도착 도시, 비용
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            route[a][b] = Math.min(route[a][b], cost);
        }

        for (int k = 0; k < N; k++) { // 경유
            for (int i = 0; i < N; i++) { // 출발
                for (int j = 0; j < N; j++) { // 도착
                    if(route[i][j] > route[i][k] + route[k][j]){
                        route[i][j] = route[i][k] + route[k][j];
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++){
                if(route[i][j] == INF) route[i][j] = 0;
            }
            System.out.println(Arrays.toString(route[i]));
        }
    }
}
