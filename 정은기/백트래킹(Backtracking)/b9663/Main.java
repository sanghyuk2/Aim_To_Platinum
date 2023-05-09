package aim_to_platinum.backtracking.b9663;

/*
제한시간 10초
- N X N 크기의 이차원 배열 체스판 board 사용
- N 개의 퀸이 주어졌을 때, 서로 공격할 수 없게 놓는 경우의 수 구하기
- 서로 공격할 수 있는지 없는지 확인하는 check 메서드
- 현재까지 놓은 퀸의 개수를 저장하는 queen
- 퀸을 놓는 방법의 수 count
- (i = 0 ~ N-1)(j = 0 ~ N-1) 상의 좌표 돌아다니며 DFS
    - 도중 queen == N 이 되면 count++, 백트래킹

=> 시간초과로 다시 고민
- NxN 체스판에 N개의 퀸
    -> 모든 행을 한 번씩 사용하게 됨
    -> boolean[] board = new boolean[N]; 을 활용
        -> board[x] = y; 가 의미하는 것
            = x행 y열에 퀸을 배치한다
    각 인덱스마다 0~N-1 의 값을 넣되,
    열/행/대각선 위치가 아니면 count++

여기서 queen 은 배치한 퀸의 개수이자
queen 번째 퀸이 위치하는 행을 의미한다
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int count = 0;
    static int[] board;

    static boolean check(int queen){
        for(int i=0; i<queen; i++){
            if(board[i] == board[queen]){
                return false;
            }
            if(Math.abs(i - queen) == Math.abs(board[i] - board[queen])){
                return false;
            }
        }
        return true;
    }

    static void DFS(int queen){
        if(queen == N){
            count++;
            return;
        }
        for(int i=0 ; i<N; i++){
            board[queen] = i;
            if(check(queen)){
                DFS(queen + 1);
            }
            else{
                board[queen] = -1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N];
        Arrays.fill(board, -1);

        DFS(0);

        System.out.println(count);
    }
}

