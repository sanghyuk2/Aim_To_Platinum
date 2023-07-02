/*
1. 문제이해
도시의 개수 N, 도로의 개수 M, 거리정보 K, 출발도시의 번호 X가 주어지고 두번째 줄부터  a, b두개의 숫자가 주어지는 데이는 a번 도시에서 b번 도시로 이동할 수 있는 단방향 도로가 존재한다는 의미이다. 이 정보들을 토대로 출발도시에서 출발하여 각 도시들의 최단 거리가 거리정보k인 도시를 출력한다.
조건 : 모든 도로의 거리는 1이다.
2. 아이디어
map[N][N] 크기의 2차원 배열에 도로의 개수 M만큼  map[a][b]를 이동 가능한 정보를 1로 저장한 후 경로를 찾는 과정을 통해 최단 거리를 찾는다.
3. 어려움 및 해결방식
배열을 통해 문제를 풀었더니 메모리 초과가 나왔다. 정점의 개수가 많을수록 메모리가 초과된다. ⇒ arraylist를 통해 문제를 해결하는 방식으로 수정했다.
 */



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bk_18352 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();
        int X = sc.nextInt();

        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            map.get(a).add(b);
        }

        int[] dist = new int[N + 1];
        boolean[] check = new boolean[N + 1];
        Queue<Integer> que = new LinkedList<>();
        que.add(X);
        check[X] = true;

        while (!que.isEmpty()) {
            int now = que.poll();

            for (int next : map.get(now)) {
                if(!check[next]){
                    dist[next] = dist[now] + 1;
                    check[next] = true;
                    que.add(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                flag = true;
                sb.append(i).append("\n");
            }
        }
        if (flag) {
            System.out.println(sb);
        } else {
            System.out.println(-1);
        }
    }
}