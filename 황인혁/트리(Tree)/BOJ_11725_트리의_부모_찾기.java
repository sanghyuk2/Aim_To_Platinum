/*
1. 문제이해
루트 없는 트리가 주어지고 트리의 루트를 1이라고 했을 때 각 노드의 부모노드를 세로로 한줄로 출력하는 문제
입력 : N(노드의 개수)
          간선으로 이어진 두 개의 노드
조건 : 노드번호 2번 부터 출력
2. 아이디어
dfs 알고리즘을 사용해서 부모노드를 저장할 배열을 따로 생성하고 트리를 탐색하며 각 노드의 부모노드를 찾아낸다.
3. 어려움 및 해결방식
실수 : 입력값을 받을 때 간선의 수를  노드의 개수만큼 받는 것으로 해서 값이 제대로 출력되지 않았었다. 실수를 줄이자.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_11725_트리의_부모_찾기 {
    static ArrayList<Integer>[] tree;
    static int[] parent;
    static boolean[] check;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        tree = new ArrayList[n + 1];
        parent = new int[n + 1];
        check = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n-1; i++) {
            int pa = sc.nextInt();
            int ch = sc.nextInt();

            tree[pa].add(ch);
            tree[ch].add(pa);
        }

        dfs(1);

        for (int i = 2; i <= n; i++) {
            System.out.println(parent[i]);
        }
    }

    public static void dfs(int node) {
        check[node] = true;

        for (int i : tree[node]) {
            if (!check[i]) {
                parent[i] = node;
                dfs(i);
            }
        }
    }
}