/*
1. 문제이해
2, 4, 8, 16, 32, 64, 128중 하나의 크기를 가진 정사각형이 주어지고 그 안에 정사각형 모양의 0으로 된 하얀 색종이와 1로 된 파란 색종이가 각각 몇개 인지 출력 하는 문제
입력 : N(최초 색종이의 크기), 0과 1
출력 : white_cnt, blue_cnt
조건 : 크기가 1이면 종료
2. 아이디어
0, 0에서 시작해서 사분면을 돌아다니면서 정사각형 내의 요소가 동일한지 check
N/2를 하면서 계속 작아지면서 하얀 색종이 인지 파란 색종이 인지 확인

int isPaper(int x, int y, int size){
    if(check(x, y)){
          if(paper[x][y] == 0) white_cnt++
          else blue_cnt++
    } else{
          isPaper(x, y, size/2)
          isPaper(x, y + size/2, size/2)
          isPaper(x + size/2, y, size/2)
          isPaper(x + size/2, y + size/2, size/2)
      }
}
3. 사용변수
int N
int[][] paper
int white_cnt, blue_cnt
4. 자료구조
int isPaper(int x, int y, int size)
int check(int x, int y, int size)
5. 문제점/해결책
    X
*/



import java.util.Scanner;

public class bk_2630 {
    static int[][] paper;
    static int white_cnt = 0;
    static int blue_cnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        paper = new int[N][N];

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                paper[i][j] = sc.nextInt();
            }
        }

        isPaper(0 , 0, N);
        System.out.println(white_cnt);
        System.out.println(blue_cnt);
    }

    public static void isPaper(int x, int y, int size){
        if(check(x, y, size) || size == 1){
            if (paper[x][y] ==  0){
                white_cnt += 1;
            } else {
                blue_cnt += 1;
            }
        } else{
            isPaper(x, y, size/2);
            isPaper(x, y + size/2, size/2);
            isPaper(x + size/2, y, size/2);
            isPaper(x + size/2, y + size/2, size/2);
        }
    }

    public static boolean check(int x, int y, int size){
        for (int i = x; i < x + size; i++){
            for (int j = y; j < y + size; j++){
                  if(paper[x][y] != paper[i][j]) return false;
            }
        }
        return true;
    }
}