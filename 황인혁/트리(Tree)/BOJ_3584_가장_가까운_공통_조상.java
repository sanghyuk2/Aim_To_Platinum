/*
1. 문제이해
테스트 케이스 T가 주어지고 N-1개의 간선 정보가 주어진다.(왼쪽이 부모노드 오른쪽이 자식노드) 테스트케이스마다 마지막 줄에 주어진 노드의 가장 가까운 공통 조상을 출력한다.
2. 아이디어
간선정보를 입력 받아서 트리를 구성하고 공통 조상을 구해야 할 두 노드중에서 한 노드 부터 루트로 탐색을 하면 방문기록을 남기고 다른 노드도 탐색하며 제일 처음으로 겹친 노드를 출력한다.
3. 어려움 및 해결방식 X
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3584_가장_가까운_공통_조상 {
    static int[] tree;
    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            tree = new int[n + 1];
            checked = new boolean[n + 1];

            StringTokenizer st;
            int a, b;
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                tree[b] = a;
            }
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            findParent(a, b);
        }

    }

    public static void findParent(int a, int b) {
        while (a > 0) {
            checked[a] = true;
            a = tree[a];
        }

        while (b > 0){
            if (checked[b]){
                System.out.println(b);
                break;
            }
            b = tree[b];
        }

    }
}