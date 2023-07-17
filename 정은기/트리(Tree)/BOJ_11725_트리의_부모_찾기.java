package aim_to_platinum.week07_tree.b11725;

/*
1. 문제 요약
- 트리의 루트는 1로 정해져있다
- 노드의 개수 N(2<=N<=100,000)을 입력받는다
- N-1번 만큼 트리 상에서의 연결 관계가 주어진다
- 2번 노드부터 N번 노드까지 각각의 부모 노드를 출력하면 된다

2. 아이디어 (문제 접근법)
[아이디어-1]
- 루트노드부터 시작한다면 각 노드의 부모 노드들을 전부 확인할 수 있다
- BFS 방식을 사용해서 int[N+1] parentArr 배열을 만들어
    각 index 의 부모 노드를 기록한다

3. 어려움 및 해결
-
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11725_트리의_부모_찾기 {

    static int N;
    static ArrayList<ArrayList<Integer>> tree;
    static boolean[] isVisited;
    static int[] parentArr;

    public static void BFS(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        isVisited[start] = true;
        while(!queue.isEmpty()) {
            int temp = queue.poll();
            for(Integer x : tree.get(temp)) {
                if(!isVisited[x]) {
                    parentArr[x] = temp;
                    isVisited[x] = true;
                    queue.add(x);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        isVisited = new boolean[N+1];
        parentArr = new int[N+1];
        tree = new ArrayList<>();

        for(int i=0; i<N+1; i++) tree.add(new ArrayList<>());

        for(int i=0; i<N-1; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int vertex1 = Integer.parseInt(stk.nextToken());
            int vertex2 = Integer.parseInt(stk.nextToken());
            tree.get(vertex1).add(vertex2);
            tree.get(vertex2).add(vertex1);
        }

        BFS(1);

        for(int i=2; i<=N; i++) {
            System.out.println(parentArr[i]);
        }


    }

}
