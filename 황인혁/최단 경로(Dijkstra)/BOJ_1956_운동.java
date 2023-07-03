/*
1. 문제이해
V개의 마을과 E개의 도로로 구성되어 있는 도시에서 도로길이의 합이 최소가 되는 사이클을 구해서 최소 도로길이의 합을 구하는 문제
입력 : V(마을의 수) E(도로의 수)
           //E개의 도로가 주어짐
          a(출발) b(도착) c(거리)
2. 아이디어
플로이드-와샬 알고리즘을 사용하여 모든 노드에서 모든 노드로의 최단 경로를 구한다. (출발지로 돌아오는 사이클을 구현해준다!)
3. 어려움 및 해결방식
충분히 큰값으로 초기화 해줘야 node가 많을 때를 대비해서 min값이 최신화가 된다.
 */



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1956_운동 {
    static int v;
    static int e;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        dist = new int[v + 1][v + 1];

        for (int i = 1; i <= v; i++) {
            Arrays.fill(dist[i], 10000000);
        }

        for (int i = 1; i <= e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dist[a][b] = c;
        }

        int ans = findRoad();

        if (ans < 10000000) {
            System.out.println(ans);
        } else {
            System.out.println(-1);
        }
    }

    public static int findRoad() {
        int min = 10000000;

        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        for (int i = 1; i <= v; i++) {
            min = Math.min(min, dist[i][i]);
        }

        return min;
    }
}