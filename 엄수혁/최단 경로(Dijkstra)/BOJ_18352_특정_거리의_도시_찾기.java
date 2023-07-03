import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 문제 요약
 * 출발 도시로부터 떨어진 거리가 K인 노드들을 찾아라.
 * 즉, 단방향 간선들로 연결 여부를 파악하고 몇번 내로 이동이 가능한지 확인의 필요
 *
 * 아이디어
 * 모든 간선의 가중치는 동일,
 * 즉, 해당 노드로 오는데 필요로하는 depth를 찾으면 된다.
 * 큐를 통해 전체 bfs 탐색을 진행한다.
 */

public class BOJ_18352_특정_거리의_도시_찾기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int INF = -1;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<Integer>[] node = new List[N + 1];

        for (int i = 1; i <= N; i++) node[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());

            node[start].add(dest);
        }

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        Queue<Integer> q = new ArrayDeque<>();
        q.add(X);
        dist[X] = 0;

        List<Integer> answer = new ArrayList<>();

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (dist[cur] > K) break;
            if (dist[cur] == K) answer.add(cur);

            for (int next : node[cur]) {
                if (dist[next] != INF) continue;
                dist[next] = dist[cur] + 1;
                q.add(next);
            }
        }

        Collections.sort(answer);
        StringBuilder sb = new StringBuilder();
        for (int cur : answer) {
            sb.append(cur).append('\n');
        }
        System.out.print(answer.isEmpty() ? -1 : sb);
    }
}
