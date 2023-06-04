package aim_to_platinum.week03_DP.b11727;

/*
1. 문제 요약

2. 아이디어
- 처음 아이디어
    - 2Xn 직사각형을 1X2, 2X1, 2X2 타일로 채우는 방법의 수
        - 1X2나, 2X2나 둘 다 가로 2칸을 차지한다
        [1] - 가로의 길이 n을 길이 1과 2로 채울 수 있는 모든 경우를 구한다
        [2] - 길이 2로 채우는 경우의 수는 1X2타일 두개로 채우는 경우와 2X2타일 하나로 채우는 경우 두 가지만 있다
            - 길이 2로 채운 부분의 수 T 에 대하여 1X2와 2X2 두 가지 경우를 적용시키면 된다. (2^T)
-> 답은 맞지만, 시간초과 발생

- 그림을 그려보니 이해가 쉬웠다
    2Xn 타일 -> 2X(n+1) 로 변할 때
    이는 곧
    2Xn 타일 모든 경우에 2X1 타일 하나를 더한 경우 혹은
    2X(n-1) 타일 모든 경우에 2X2 타일 혹은 1x2(2) 를 더한 경우이다

    따라서
    arr[n] = arr[n-1] + 2*arr[n-2] 이다

3. 어려움 및 해결방식
- 처음 재귀를 활용한 DFS 방식으로 해결하려 했을 때 시간복잡도를 고려하지 않았던 점이 아쉽다
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11727_2xn_타일링_2 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];

        arr[1] = 1;
        if(N>1) arr[2] = 3;

        for(int i=3; i<=N; i++){
            arr[i] = ( arr[i-1] + 2*arr[i-2] ) % 10007;
        }

        System.out.println(arr[N]);
    }
}