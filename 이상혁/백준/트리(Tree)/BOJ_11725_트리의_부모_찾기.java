/*
### 1. 문제 요약

- 루트 없는 트리가 주어진다. 이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성하시오

### 2. 아이디어(문제 접근법)

- 트리를 구성한 뒤 부모를 저장하는 parents 배열을 사용하여 값을 출력한다
- 간선을 정확히 주어주기에 ArrayList 자료구조를 사용하여 부모가 가지는 자식 노드의 값들을 저장하였다

### 3. 어려움 및 해결방식

- X
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static int N;
    static int[] parents;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        int children = N - 1;

        while (children-- > 0) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            tree.get(A).add(B);
            tree.get(B).add(A);
        }

        parents = new int[N + 1];
        visited = new boolean[N + 1];

        dfs(1, 0);

        for (int i = 2; i <= N; i++) {
            System.out.println(parents[i]);
        }
    }

    private static void dfs(int node, int parent) {
        visited[node] = true;
        parents[node] = parent;

        for (int child : tree.get(node)) {
            if (!visited[child]) {
                dfs(child, node);
            }
        }
    }
}