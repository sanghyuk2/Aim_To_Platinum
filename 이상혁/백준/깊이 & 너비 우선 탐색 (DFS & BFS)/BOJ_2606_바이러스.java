/*
1. 문제요약

7대의 컴퓨터가 <그림 1>과 같이 네트워크 상에서 연결되어 있다고 하자. 1번 컴퓨터가 웜 바이러스에 걸리면 웜 바이러스는 2번과 5번 컴퓨터를 거쳐 3번과 6번 컴퓨터까지 전파되어 2, 3, 5, 6 네 대의 컴퓨터는 웜 바이러스에 걸리게 된다. 하지만 4번과 7번 컴퓨터는 1번 컴퓨터와 네트워크상에서 연결되어 있지 않기 때문에 영향을 받지 않는다.
어느 날 1번 컴퓨터가 웜 바이러스에 걸렸다. 컴퓨터의 수와 네트워크 상에서 서로 연결되어 있는 정보가 주어질 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 출력하는 프로그램을 작성하시오.

2. 아이디어(문제접근법)

정점(V)의 개수보다 간선(E)의 개수가 적은 경우 **희소 그래프(Sparse Graph)** 라고 부른다.
    - 정점의 개수와 간선의 개수가 비슷한 경우 **밀집 그래프(Dense Graph)** 라고 부른다.
    - 일반적인 경향으로는 간선의 개수가 V의 제곱에 가깝게 증가하는 경우를 밀집 그래프라고 부를 수 있다.
        - 예를 들어, V가 100이라고 가정하면:
        - 간선의 개수가 100보다 작으면 희소 그래프로 간주될 수 있습니다.
        - 간선의 개수가 100보다 크고 10000에 가깝다면 중간 정도의 밀집 그래프로 볼 수 있습니다.
        - 간선의 개수가 10000을 넘어간다면 상당히 밀집 그래프로 간주될 수 있습니다.
주어진 문제는 희소 그래프 문제이며, 이에 희소 그래프에 효율적인 **BFS**를 선택하여 문제를 해결하려 하였다.
BFS를 사용하면 감염 컴퓨터들을 너비 우선으로 탐색하며, 시작점에서 가까운 컴퓨터부터 차례대로 확인할 수 있고, 최소한의 이동으로 탐색할 수 있다.

3. 어려움 및 해결 방식
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());

        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
            graph.get(B).add(A);
        }

        bfs(1);
        System.out.println(answer);

    }

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : graph.get(now)) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                    answer++;
                }
            }
        }
    }
}