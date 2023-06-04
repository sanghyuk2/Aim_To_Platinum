/*
1. 문제이해
N이 주어진다. N은 3*2^(k)이다. 세개의 삼각형으로 이루어져 있다.
입력 : N(삼각형의 높이)
출력 : 삼각형을 기본으로 세개씩 붙이면서 더 큰 삼각형을 만듬
조건 : 삼각형의 높이가 3이면 삼각형을 만든다.
가장 높이있는 별을 기준으로 (y, x) 한다
2. 아이디어
N을 3으로 나누고 k값을 알아낸다.
k 값 만큼 별 찍기를 반복하고 char[][] stars 배열에 저장후 n/2 하면서 drawStar를 호출하면서 스트링빌더로 출력
3. 사용변수
int N
char[N][2*N-1] stars
4. 자료구조
makeStars(int y, int x, int n)
5. 문제점/해결책
String으로 위의 삼각형 모양을 만들고 재귀적으로 풀어보여고 했으나 방법을 찾지 못했다
⇒ char[][] 배열을 사용해서 *이 들어가는 부분에 저장하는 방식으로 변경했다.
처음에 문제를 분석하지 않고 풀어보려고 해서 오래 걸렸다.
⇒ 규칙을 찾아냈다. k가 1일때 부터 삼각형의 높이와 밑변의 길이의 규칙을 찾아내서 문제를 풀었다.
 */


import java.util.Arrays;
import java.util.Scanner;

public class bk_2448 {
    static char[][] star;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        star = new char[N][2*N-1];

        for(int i = 0; i < N; i++){
            Arrays.fill(star[i], ' ');
        }

        makeStars(0,N-1, N); // 공백이 있기 때문에 x의 값은 N - 1에서 시작한다.

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2*N-1; j++) {
                sb.append(star[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void makeStars(int y , int x, int n){
        if(n == 3){
            star[y][x] = '*';
            star[y+1][x-1] = star[y+1][x+1] = '*';
            star[y+2][x-2] = star[y+2][x-1] = star[y+2][x] = star[y+2][x+1]= star[y+2][x+2] = '*';
            return;
        }

        makeStars(y, x, n/2 );
        makeStars(y + n/2, x - n/2, n/2 );
        makeStars(y + n/2, x + n/2, n/2 );
    }
}