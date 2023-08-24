/*
1. 문제 요약
    - N×N 크기의 종이를 4등분을 반복적으로 자르는 과정을 거침
    - 위의 과정을 거쳐진 종이가 모두 하얀색 혹은 파란색으로 칠해져있거나, 하나의 정사각형 칸이 되어 더 이상 자를 수 없을 때까지 반복
    - 하얀색 종이와 파란색 종이의 개수를 구하시오

2. 아이디어(문제접근법)
    - 4등분이기에 변을 2로 나누는걸로 접근
    - 나누어진 정사각형 범위 내 존재하는 최소단위(1x1)의 정사각형이 모두 같은 색인지 판별
        - 만일 같다면 과정을 멈추고 blue 혹은 white 값을 증가
        - 만일 다르다면 최소단위 혹은 범위 내 최소단위의 정사각형의 색이 모두 같아질 때까지 나눔

3. 어려움 및 해결방식
    - 없음
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] blueAndWhite = new int[2];
    static int[][] origami;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        origami = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                System.out.println(Integer.parseInt(st.nextToken()));
            }
        }

        makeOrigami(N, 0, 0);
        for (int n : blueAndWhite)
            System.out.println(n);
    }

    static void makeOrigami(int N, int y, int x) {
        for (int i = y; i < y + N; i++) {
            for (int j = x; j < x + N; j++)
                if (origami[i][j] != origami[y][x]) {
                    makeOrigami(N / 2, y, x);
                    makeOrigami(N / 2, y + N / 2, x);
                    makeOrigami(N / 2, y, x + N / 2);
                    makeOrigami(N / 2, y + N / 2, x + N / 2);

                    return;
                }
        }

        blueAndWhite[origami[y][x]]++;
    }
}