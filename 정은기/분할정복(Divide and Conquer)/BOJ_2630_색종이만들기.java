package aim_to_platinum.week02_divide_and_conquer.b2630;

/*
두 개의 2차원 배열 board 와 check 를 만들어 보자,

if 종이 전체가 같은 색깔로 칠해져 있지 않다면
    - 가로와 세로 중간을 잘라서 4등분 한다.
        - 이를 반복
단일 색상의 색종이 수를 색상별로 출력하면 되는 문제이다

즉, 다음 과정을 반복한다

1. 영역의 전체 색상이 같은지 확인한다
    if 같으면 해당 색상 count++
    else 4등분하여 각 부분별로 문제 해결



 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int blueCount = 0;
    static int whiteCount = 0;

    static int[][] board;

    static boolean checkColor(int xFrom, int xTo, int yFrom, int yTo){
        int first = board[xFrom][yFrom];
        for(int i=xFrom; i<=xTo; i++){
            for(int j=yFrom; j<=yTo; j++){
                if(board[i][j] != first) return false;
            }
        }
        return true;
    }
//
//    static void test1(int xFrom, int xTo, int yFrom, int yTo){
//        for(int i=xFrom; i<=xTo; i++){
//            for(int j=yFrom; j<=yTo; j++){
//                board[i][j] = 9;
//            }
//        }
//    }
//
//    static void test2(){
//        for(int i=0; i<N; i++){
//            for(int j=0; j<N; j++){
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }

    static void DC(int xFrom, int xTo, int yFrom, int yTo){
        if(checkColor(xFrom, xTo, yFrom, yTo)){
            if(board[xFrom][yFrom] == 0){
                whiteCount++;
            }else{
                blueCount++;
            }

//            test1(xFrom, xTo, yFrom, yTo);
//            test2();

        }else{
            DC(xFrom, (xTo+xFrom)/2, yFrom, (yTo+yFrom)/2);
            DC(xFrom, (xTo+xFrom)/2, (yTo+yFrom)/2+1, yTo);
            DC((xTo+xFrom)/2+1, xTo, yFrom, (yTo+yFrom)/2);
            DC((xTo+xFrom)/2+1, xTo, (yTo+yFrom)/2+1, yTo);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for(int i=0; i<N; i++){
            str = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        DC(0, N-1, 0, N-1);

        System.out.println(whiteCount);
        System.out.println(blueCount);
    }
}
