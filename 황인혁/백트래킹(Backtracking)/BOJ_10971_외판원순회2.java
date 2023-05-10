/*
1. 문제이해
    주어진 배열(int[N][N]) 도시들 간에 이동비용을 계산하여 외판원이 모든 도시를 거친후 다시 돌아오는 최소비용을 출력하는 문제
    입력 : N(도시의 수, 배열크기), 배열 값 i 에서 j로 j 에서 i 로 이동 할 때의 이동비용
    출력 : 모든 순환후 최소 비용 출력
    조건 :
    한번 갔던 도시로는 갈수 없다(int[i][i] 또는 int[j][j]는 0)
    2. 사용변수
    int N : 도시수(배열 크기)
    int[][] route : 도시이동 경로
    boolean[] visited : 방문한 도시 저장
    int routeMin : 결과값
    3. 자료구조
    dfs(int start, int end, int sum, int cnt)
    4. 문제점/해결책
    - 다음 도시로 이동하는 루트를 탐색할때 cnt를 해주는 부분에서 후위연산자를 사용해서 값이 제대로 출력되지않았다. ⇒ cnt + 1을 해서 다음 도시로 이동 할때 cnt 값을 추가해주었다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10971_외판원순회2 {
    static int N;
    static int[][] route;
    static boolean[] visited;
    static int routeMin = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        route = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                route[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            visited[i] = true;
            dfs(i, i, 0, 0);
        }

        System.out.println(routeMin);
    }

    public static void dfs(int start, int now, int sum, int cnt) {

        if (cnt == N - 1) {
            if (route[now][start] != 0) {
                sum += route[now][start];
                routeMin = Math.min(sum, routeMin);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i] && route[now][i] > 0) {
                visited[i] = true;
                dfs(start, i, sum + route[now][i], cnt+1);//이부분 후위연산자는 왜 안되지?
                visited[i] = false;
            }
        }
    }
}