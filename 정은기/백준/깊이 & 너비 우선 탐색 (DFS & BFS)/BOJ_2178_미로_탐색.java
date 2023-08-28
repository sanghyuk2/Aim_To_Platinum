package aim_to_platinum.week10_DFS_BFS.b2178;

/*
1. 문제 요약
- N(세로) x M(가로) 크기의 배열을 입력받는다. (2 ≤ N, M ≤ 100)
- (1, 1) 에서 출발하여 (N, M) 의 위치로 이동할 때 최소의 칸 수를 구하면 된다.
- 시작 위치와 도착 위치를 포함하여 센다.


2. 아이디어 (문제 접근법)
[아이디어-1]
- board 의 값들이 붙어서 주어지므로 StringTokenizer 대신 chatAt() 을 사용한다.
- 최소의 칸 수를 구해야 하므로 BFS 를 활용한다.
- BFS 과정 진행 중 먼저 (N, M) 에 도달한 레벨을 출력하면 된다.


3. 어려움 및 해결
-
*/

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178_미로_탐색 {

    static int[][] board;
    static boolean[][] isVisited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int N, M;

    static void BFS(int x, int y){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        isVisited[x][y] = true;

        while (!queue.isEmpty()){
            int[] now = queue.poll();

            for(int i = 0; i < 4; i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx < 0 || nx > N || ny < 0 || ny > M){
                    continue;
                }

                if((board[nx][ny] != 1) || isVisited[nx][ny]){
                    continue;
                }

                queue.offer(new int[]{nx, ny});
                board[nx][ny] = board[now[0]][now[1]] + 1;
            }
        }
    }

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        board = new int[N + 1][M + 1];
        isVisited = new boolean[N + 1][M + 1];

        for(int i = 1; i <= N; i++){
            String s = br.readLine();
            for(int j = 1; j <= M; j++){
                board[i][j] = s.charAt(j-1) - '0';
            }
        }

        BFS(1, 1);

        sb.append(board[N][M]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
