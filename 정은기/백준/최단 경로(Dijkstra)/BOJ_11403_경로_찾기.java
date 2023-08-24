package aim_to_platinum.week06_shortest_path.b11403;

/*
1. 문제 요약
- 주어진 인접행렬 board 를 기반으로
    정점 i에서 j로 향하는 경로가 있으면 1로 표시, 없으면 0으로 표시한다.
    이후 경로 여부를 나타낸 행렬을 나타내면 된다.

2. 아이디어 (문제 접근법)
[아이디어-1]
- 이동할 수 있는지 여부를 파악하기만 하므로
    board i->j 가 0이었어도 i->k->j가 된다면
    i->j = 1 로 업데이트 시켜준다.

3. 어려움 및 해결
-
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11403_경로_찾기 {

    static int N;
    static int[][] board;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for(int i=0; i<N; i++){
            stk = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(stk.nextToken());
            }
        }


        for(int k=0; k<N; k++){
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(board[i][k]==1 && board[k][j]==1){
                        board[i][j] = 1;
                    }
                }
            }
        }


        for(int i=0; i<N; i++){
            for(int j = 0; j<N; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
