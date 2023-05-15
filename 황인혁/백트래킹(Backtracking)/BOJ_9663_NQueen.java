/*
1. 문제이해
NXN의 크기를 가진 체스판에 N개의 퀸을 놓을 수 있는 경우의 수 구하기
(서로를 공격할수 없는 경우의 수를 출력)
입력 : N(체스판의 크기, 퀸의 개수)
출력 : cnt(가능한 경우의 수)
조건 : 퀸은 서로 같은 행, 같은 열, 대각선에 위치하면 안됨
2. 아이디어
- 첫 번째 행부터 시작해서 각 열마다 퀸을 놓을수 있는지 확인
- 현재 위치에 퀸을 놓을 수 있다면 다음 행으로 이동
- 현재 위치에 퀸을 놓을 수 없다면 다음 열로 이동
- 마지막 행까지 퀸을 놓았다면, cnt값 증가 다음 경우 탐색
- 더 이상 탐색할 경우가 없다면 이전 상태에서 다른 경우 탐색
3. 사용변수
int N : 입력값(체스판 크기, 퀸의 개수)
int[] col : 각 행에 퀸이 위치한 열의 번호를 저장하는 배열
int[] cnt : 결과값(경우의 수)
4. 자료구조
dfs(int row)
5. 문제점/해결책 X
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.util.Scanner;

public class BOJ_16987_계란으로계란치기{
    static int N; //입력값
    static int[] col; //행에 있는 열번호를 저장하는 배열
    static int cnt; //결과값

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        col = new int[N];

        dfs(0);
        System.out.println(cnt);
    }

    public static void dfs(int row) {
        if (row == N) { // 마지막 행까지 퀸을 놓은 경우(완료)
            cnt++;
            return;
        }
        for (int i = 0; i < N; i++) {
            col[row] = i;
            if (isPossible(row)) { // 현재 위치에 퀸을 놓을 수 있는 경우
                dfs(row + 1); // 다음 행으로 이동
            }
        }
    }

    public static boolean isPossible(int row) {
        for (int i = 0; i < row; i++) {
            // 같은 열에 퀸이 있는 경우
            if (col[i] == col[row]) {
                return false;
            }
            // 대각선 방향에 퀸이 있는 경우(대각선에 있는 번호는 절대값(열-열)=(행-행)이 같다)
            if (Math.abs(col[i] - col[row]) == Math.abs(row - i)) {
                return false;
            }
        }
        return true;
    }
}