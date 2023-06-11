/*
1. 문제요약
왼쪽 위 칸에서 출발해서 오른쪽 아래칸에 도착할 때의 경로 수를 출력
이동시에는 현재 칸 보다 숫자가 작을때만 이동 가능
입력 : 칸의 크기 N(세로) M(가로)
출력 :  cnt(이동 가능한 경우의 수)
2. 아이디어
주어진 입력 값을 map에 저장하고 이동 배열(dx, dy)를 통해 이동할 수 있는 모든 경우의 수를 찾아서 출력한다.
3. 어려움 및 해결방식 X
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1520_내리막길 {
    static int[][] map;
    static int[][] dp;
    static int N, M;
    static int[] dy = {1,0,-1,0};
    static int[] dx = {0, 1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());

        map = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st2.nextToken());
                dp[i][j] = -1;
            }
        }

        int cnt = moveToMap(0 , 0);
        System.out.println(cnt);
    }

    public static int moveToMap(int y, int x) {
        if (y== N-1 & x== M-1){
            return 1;
        }
        if(dp[y][x] != -1){
            return dp[y][x];
        }

        dp[y][x] = 0;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= 0 && nx >= 0 && ny < N && nx < M){
                if (map[y][x] > map[ny][nx]){
                    dp[y][x] += moveToMap(ny, nx);
                }
            }
        }

        return dp[y][x];
    }
}