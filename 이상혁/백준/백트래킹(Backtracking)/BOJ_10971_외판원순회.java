import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
문제점 1.
1 -> 2 -> 3 -> 4 -> 1 과정에서 4번도시에서 1번 도시로 돌아가는 길이 없는 예외 경우를 처리를 안해줌

해결책 1.
if (W[path.get(path.size() - 1)][path.get(0)] != 0) 문 안에 돌아가는 경우가 없는 경우 return을 하지 않게끔 만듦

문제점 2.
현재 도시에서 다음 도시로 넘어갈 때 길이 없는 경우를 처리하지 않음

해결책 2.
 if(W[here][next] == 0) {
   continue;
 }
 위 코드를 통해 해결
 */
public class Main {
    private static int N;
    private static int[][] W;
    private static boolean[] visited;
    private static List<Integer> path = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        visited = new boolean[N];
        //첫번째 도시는 시작점이기에 항상 true 값을 지닌다
        visited[0] = true;
        //방문한 도시
        path.add(0);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(shortestPath(path, visited, 0));
    }

    private static long shortestPath(List<Integer> path, boolean[] visited, int currentLength) {
        if (path.size() == N) {
            //만일 돌아가는 길이 없다면 실행시키지 않는다
            if (W[path.get(path.size() - 1)][path.get(0)] != 0) {
                //지금까지 움직인 거리 + 현재 지점으로부터 첫 도시까지 돌아가는 거리
                return currentLength + W[path.get(path.size()-1)][path.get(0)];
            }
        }

        //비교하는 수
        //최소값을 비교하기에, 큰 값을 지정하고 작은수를 골라낸다.
        long ret = Long.MAX_VALUE;

        for (int next = 0; next < N; next++) {
            //이미 방문했던 도시면, 넘긴다.
            if (visited[next]) {
                continue;
            }

            //현재 도시
            int here = path.get(path.size() - 1);

            //만약 다음 도시로 넘어가는 길이 없는 경우에 다른 도시를 선택한다.
            if(W[here][next] == 0) {
                continue;
            }

            path.add(next);
            visited[next] = true;

            long cand = shortestPath(path, visited, currentLength + W[here][next]);

            ret = Math.min(ret, cand);
            path.remove(path.size()-1);
            visited[next] = false;
        }

        return ret;
    }
}