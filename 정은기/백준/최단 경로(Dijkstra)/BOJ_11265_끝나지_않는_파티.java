package aim_to_platinum.week06_shortest_path.b11265;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. 문제 요약
- 모든 파티장끼리의 최단 경로를 구해야 하므로 플로이드 알고리즘 사용
    - 파티장(이하 노드)간 인접행렬 입력받아 중간 노드 거치게 만들어 최단거리 업데이트
- 이후 서비스 요청 수만큼 출발->도착 최단경로 값과 시간 비교
- 노드 수를 N, 서비스 요청 손님 수를 M 이라고 할 때,
    N(5 ≤ N ≤ 500), M(1 ≤ M ≤ 10,000) 이고
    인접행렬을 board 라고 하면 board[i][j] 안에 들어갈 수 있는 수 T의 범위는
    T(1 ≤ T ≤ 1,000,000)

2. 아이디어 (문제 접근법)
[아이디어-1]
- 플로이드 알고리즘 사용하여 모든 정점간 최단 경로 확인
- 이후 board[from][to] <= weight 일 경우 "Enjoy other party",
    아닐경우 "Stay here" 출력

3. 어려움 및 해결
-
 */
public class BOJ_11265_끝나지_않는_파티 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        long[][] board = new long[N][N];

        for(int i=0; i<N; i++){
            stk = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Long.parseLong(stk.nextToken());
            }
        }

        for(int k=0; k<N; k++){
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(board[i][j] > board[i][k] + board[k][j]){
                        board[i][j] = board[i][k] + board[k][j];
                    }
                }
            }
        }

        for(int i=0; i<M; i++){
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken()) - 1;
            int to = Integer.parseInt(stk.nextToken()) - 1;
            long weight = Long.parseLong(stk.nextToken());

            if(board[from][to] <= weight){
                System.out.println("Enjoy other party");
            }else{
                System.out.println("Stay here");
            }
        }
    }

}
