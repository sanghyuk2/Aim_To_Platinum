package aim_to_platinum.week10_DFS_BFS.b1012;

/*
1. 문제 요약
- 첫 줄에서 테스트케이스의 수를 입력받는다.
- 가로길이 M과 세로길이 N 인 밭에 배추를 심을 때
    상하좌우 연결되어있는 배추는 한 마리의 지렁이로 보호가 가능하다.
    이 때 주어진 각 테스트케이스마다 필요한 지렁이의 수를 구하면 된다.
- 테스트 케이스마다 가로길이 M(1 ≤ M ≤ 50),
    세로길이 N(1 ≤ N ≤ 50), 배추의 개수 K(1 ≤ K ≤ 2500) 가 주어진다.
    배추의 위치 X(0 ≤ X ≤ M-1), Y(0 ≤ Y ≤ N-1) 를 입력받는다.


2. 아이디어 (문제 접근법)
[아이디어-1]
- M x N 크기의 인접 행렬을 만들어
    BFS/DFS 를 활용하여 연결되어있는 배추의 수를 세면 된다.


3. 어려움 및 해결
-
*/

import java.io.*;
import java.util.StringTokenizer;


public class BOJ_1012_유기농_배추 {

    static int testCase, N, M, K;
    static boolean[][] board;
    static boolean[][] isVisited;
    static int dx[] = {1, -1, 0, 0};
    static int dy[] = {0, 0, 1, -1};

    static int answer = 0;

    static void DFS(int x, int y){
        isVisited[x][y] = true;

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1) {
                continue;
            }

            if(!board[nx][ny] || isVisited[nx][ny]) {
                continue;
            }

            DFS(nx, ny);
        }
    }

    static void BFS(int x, int y){

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ab = new StringBuilder();
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0){
            stk = new StringTokenizer(br.readLine());
            M = Integer.parseInt(stk.nextToken());
            N = Integer.parseInt(stk.nextToken());
            K = Integer.parseInt(stk.nextToken());
            board = new boolean[N][M];
            isVisited = new boolean[N][M];
            answer = 0;

            while(K-- > 0){
                stk = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(stk.nextToken());
                int y = Integer.parseInt(stk.nextToken());
                board[y][x] = true;
            }

            for(int i = 0; i < N ;i++) {
                for (int j = 0; j < M; j++) {
                    if(!board[i][j] || isVisited[i][j]){
                        continue;
                    }
                    answer++;
                    DFS(i, j);
                }
            }

            sb.append(answer).append("\n");
        }
        sb.substring(0, sb.length());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
