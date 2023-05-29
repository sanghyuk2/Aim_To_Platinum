package aim_to_platinum.week02_divide_and_conquer.b14600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
https://rebro.kr/64
2^n X 2^n 크기의 정사각형은 1X1 한 칸을 제외하고 ㄴ 모양의 타일로 전부 채울 수 있다.

1. x, y축에 주의하여 하수구 위치 -1로 표시
    static number = 1
아래 내용을 재귀로 해결
recursion(startX, startY, size)
2. 현재 범위의 한 변의 길이를 size라고 하자
    - 현재 범위를 4등분하여 (size=2^n 이라서 1만 아니라면 무조건 가능하다)
        check 메서드 안 파라미터는 다음과 같이
        그 아래는 check 결과 true면 취할 행동
            - startX, startY, size/2
                - (startX+size/2-1, startY+size/2-1) number++로
            - startX, startY+size/2, size/2
                - (startX+size/2-1, startY+size/2) number++로
            - startX+size/2, startY, size/2
                - (startX+size/2, startY+size/2-1) number++로
            - startX+size/2, startY+size/2, size/2
                - (startX+size/2, startY+size/2) number++로
    - 이어서 아래로 recursion 진행
    recursion(startX, startY, size/2)
    recursion(startX, startY+size/2, size/2)
    recursion(startX+size/2, startY, size/2)
    recursion(startX+size/2, startY+size/2, size/2)

3. check(시작X, 시작Y, 체크길이)
    체크하는 범위가 전부 0인지 확인 (아무 이벤트 없는지)

 */
public class Main {
    static int[][] board;
    static int N;
    static int number = 1; //타일 번호

    static boolean check(int startX, int startY, int size){
        for(int i=startX; i<startX+size; i++){
            for(int j=startY; j<startY+size; j++){
                if(board[i][j]!=0) return false;
            }
        }
        return true;
    }

    static void recursion(int startX, int startY, int size){

        if(size==1){
            return;
        }

        int newSize = size/2;
        if(check(startX, startY, newSize)){
            board[startX+newSize-1][startY+newSize-1] = number;
        }
        if(check(startX, startY+newSize, newSize)){
            board[startX+newSize-1][startY+newSize] = number;
        }
        if(check(startX+newSize, startY, newSize)){
            board[startX+newSize][startY+newSize-1] = number;
        }
        if(check(startX+newSize, startY+newSize, newSize)){
            board[startX+newSize][startY+newSize] = number;
        }
        number++;

//        showBoard();
        System.out.println();

        recursion(startX, startY, newSize);
        recursion(startX, startY+newSize, newSize);
        recursion(startX+newSize, startY, newSize);
        recursion(startX+newSize, startY+newSize, newSize);

    }

    static void showBoard(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        int n = Integer.parseInt(br.readLine());
        N = (int)Math.pow(2, n);
        board = new int[N][N];

        stk = new StringTokenizer(br.readLine());
        int holeX = Integer.parseInt(stk.nextToken());
        int holeY = Integer.parseInt(stk.nextToken());
        board[N-holeY][holeX-1] = -1;

        recursion(0, 0, N);

        showBoard();
    }
}
