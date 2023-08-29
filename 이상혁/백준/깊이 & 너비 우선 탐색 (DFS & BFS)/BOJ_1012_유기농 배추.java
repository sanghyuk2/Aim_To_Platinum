/*
1. 문제요약

차세대 영농인 한나는 강원도 고랭지에서 유기농 배추를 재배하기로 하였다. 농약을 쓰지 않고 배추를 재배하려면 배추를 해충으로부터 보호하는 것이 중요하기 때문에, 한나는 해충 방지에 효과적인 배추흰지렁이를 구입하기로 결심한다. 이 지렁이는 배추근처에 서식하며 해충을 잡아 먹음으로써 배추를 보호한다. 특히, 어떤 배추에 배추흰지렁이가 한 마리라도 살고 있으면 이 지렁이는 인접한 다른 배추로 이동할 수 있어, 그 배추들 역시 해충으로부터 보호받을 수 있다. 한 배추의 상하좌우 네 방향에 다른 배추가 위치한 경우에 서로 인접해있는 것이다.
한나가 배추를 재배하는 땅은 고르지 못해서 배추를 군데군데 심어 놓았다. 배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 되므로 서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지 알 수 있다. 예를 들어 배추밭이 아래와 같이 구성되어 있으면 최소 5마리의 배추흰지렁이가 필요하다. 0은 배추가 심어져 있지 않은 땅이고, 1은 배추가 심어져 있는 땅을 나타낸다.

2. 아이디어(문제접근법)

- 배추가 심어져 있는 땅 근처에 있는 배추가 심어져 있는 땅을 파악하는 문제이다
- 만일 선택한 위치(x,y)가 배추가 심어져 있는 땅이면 주변에 배추가 있는 땅 모두를 식별해야 한다
- 배추를 식별하는 과정은 더 이상 쪼개질 수 없는 과정이기에 이를 반복적으로 실행하여 배추가 심어져 있는 땅을 찾아내는 DFS 방식을 사용한다

3. 어려움 및 해결 방식
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    static int N, M;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            graph = new int[N][M];

            int K = Integer.parseInt(st.nextToken());
            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                graph[Y][X] = 1;
            }

            int result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (dfs(i, j)) {
                        result++;
                    }
                }
            }

            sb.append(result).append("\n");
        }
        System.out.println(sb.toString());

    }

    public static boolean dfs(int x, int y) {
        if (x <= -1 || x >= N || y <= -1 || y >= M) {
            return false;
        }

        if (graph[x][y] == 1) {
            graph[x][y] = 0;

            dfs(x - 1, y);
            dfs(x + 1, y);
            dfs(x, y - 1);
            dfs(x, y + 1);
            return true;
        }

        return false;
    }
}