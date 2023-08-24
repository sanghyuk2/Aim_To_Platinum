/*
1. 문제요약

그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.

입력으로 주어지는 간선은 양방향이다.

2. 아이디어(문제접근법)

DFS 방식은 한 노드를 선택하여 끝까지 탐색하는 방식이기에 재귀 함수를 이용하였다.

BFS 방식은 한 노드에서 여러 방향으로 뻗어 나가며 방문 하지 않은 노드에 대한 정보를 구하기에 Queue 자료구조를 이용하였다.

3. 어려움 및 해결 방식
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

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

        for (ArrayList<Integer> integers : graph) {
            Collections.sort(integers);
        }

        dfs(V);
        Arrays.fill(visited, false);
        sb.append("\n");
        bfs(V);

        System.out.println(sb.toString());
    }

    private static void dfs(int start) {
        visited[start] = true;

        sb.append(start).append(" ");

        for (int next : graph.get(start)) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            sb.append(now).append(" ");

            for (int next : graph.get(now)) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
    }


}