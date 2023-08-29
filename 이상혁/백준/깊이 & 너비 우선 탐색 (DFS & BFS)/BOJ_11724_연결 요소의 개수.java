/*
1. 문제요약

방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.

2. 아이디어(문제접근법)

문제를 읽고 처음 든 생각은 연결 요소의 개수를 구하는 것이기에 모든 노드를 방문해야 한다는 점이었다.

연결 리스트로 문제를 해결하려고 할 때, 연결 요소의 마지막 노드를 방문하게 되면 연결이 끊긴다.

이에 visited 배열의 모든 요소가 true가 될 때까지 while문을 돌려 모든 노드를 방문할 수 있게끔 하였다. 또한 BFS 방식을 통해 연결부분이 끊길 때 마다 answer 값을 증가시켜 답을 도출하였다

3. 어려움 및 해결 방식
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
모든 노드를 방문할 때까지 반복문 실행
 */
public class Main {
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        visited[0] = true;

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int index = 1;
        int answer = 0;
        while (!isAllTrue()) {
            if (visited[index]) {
                index++;
                continue;
            }
            bfs(index);
            answer++;
        }

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
                }
            }
        }
    }

    private static boolean isAllTrue() {
        for (boolean bool : visited) {
            if (!bool) {
                return false;
            }
        }

        return true;
    }
}