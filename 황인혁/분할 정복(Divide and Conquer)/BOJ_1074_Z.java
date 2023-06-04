/*
1. 문제이해
N이 주어지고 2^N × 2^N 크기의 배열이 주어지고 주어진 r행 c열의 값을 Z모양으로 다니며
몇번째로 방문하는지 탐색하는 문제
입력 : N r c
출력 : arr[r][c]
조건 : 왼쪽 위→오른쪽 위→왼쪽 아래→오른쪽 아래 순으로 탐색
          0부터 시작
2. 아이디어
배열을 만들고 배열을 이동하며 값을 증가시키면서 저장후 배열[r][c]로 출력
→ 사분면으로 나눈뒤 r c의 위치를 찾으면서 cnt++
3. 사용변수
int N, r, c
int cnt
4. 자료구조
- 재귀
inputArr(int size, int r, int c)
5. 문제점/해결책
1. 처음에는 간단하게 배열을 dx, dy로 이동하면서 값을 저장한 후 찾으면 될 줄
알았는데 큰 사각형이 작은 사각형으로 계속해서 분할해서 사각형 간에 이동하는 방법을 생각하는것에 어려움을 겪었다.
→ 재귀 (사분면으로 나눈뒤 r c의 위치를 찾으면서 cnt++)
2. arr[0][0]에서 부터 cnt 값을 증가 시키면서 값을 찾으려다. 잘못된 방법이라는 것을 알고 재귀를 사용해서 풀었다.
→ N이 1인 경우부터 생각해서 계산했더니 풀렸다.
 */
import java.util.Scanner;

public class bk_1074 {
    static int cnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();

        int size = (int) Math.pow(2, N);

        findRC(size, r, c);

        System.out.println(cnt);
    }

    public static void findRC(int size, int r, int c) {
        if (size == 1) {
            return;
        }

        if (r < size / 2) {
            if (c < size / 2) {
                //1사분면
                findRC(size/2, r, c);
            } else {
                // 2사분면
                cnt += (size*size) / 4;
                findRC(size/2, r, c - size/2);
            }
        } else {
            if (c < size / 2) {
                //3사분면
                cnt += (size*size) / 4 * 2;
                findRC(size/2, r - size/2, c);
            } else {
                //4사분면
                cnt += (size*size) / 4 * 3;
                findRC(size/2, r - size/2, c - size/2);
            }
        }
    }
}