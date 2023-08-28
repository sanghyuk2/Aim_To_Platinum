package aim_to_platinum.week10_DFS_BFS.b11724;

/*
1. 문제 요약
- 방향이 없는 그래프가 주어졌을 때 연결 요소의 개수를 구한다.
- 첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000) 과
    간선의 양 끝점 M(0 ≤ M ≤ N×(N-1)/2) 을 입력 받는다.
- 그리고 M 번만큼 간선의 양 끝점 u, v를 입력받는다.
    이 때 (1 ≤ u, v ≤ N, u ≠ v) 이고
    같은 간선은 한 번만 입력받는다.


2. 아이디어 (문제 접근법)
[아이디어-1]
- 제한시간 3초, N 의 최대값이 1000 이므로 인접 그래프 활용 가능하다.
- 방향이 없는 간선이므로 간선의 양 점 u, v를 입력받는 과정에서
     u -> v, v-> u 둘 다 입력받는다
- 이후 그래프를 순회하면서 방문한 적이 없는 곳이면 결과값을 +1 해주고
    DFS 혹은 BFS 를 실행하며 방문처리하면 된다


3. 어려움 및 해결
-
*/

import java.io.*;
import java.util.StringTokenizer;


public class BOJ_11724_연결_요소의_개수 {

    static boolean[][] board;
    static boolean[] isVisited;

    static int N, M, answer = 0;

    static void DFS(int now){
        isVisited[now] = true;
        for(int i = 1; i <= N; i++){
            if(board[now][i] && !isVisited[i]){
                DFS(i);
            }
        }
    }

    static void BFS(){

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        board = new boolean[N + 1][N + 1];
        isVisited = new boolean[N + 1];
        while(M-- > 0){
            stk = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(stk.nextToken());
            int v = Integer.parseInt(stk.nextToken());
            board[u][v] = true;
            board[v][u] = true;
        }

        for(int i = 1; i <= N; i++){
            if(!isVisited[i]){
                answer++;
                DFS(i);
            }
        }

        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
