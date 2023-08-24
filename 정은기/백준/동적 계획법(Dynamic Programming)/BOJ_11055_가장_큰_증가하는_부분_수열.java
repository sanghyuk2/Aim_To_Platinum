package aim_to_platinum.week03_DP.b11055;

/*
1. 문제 요약
부분 수열을 활용한 문제
- 증가하는 부분수열이므로 추가되는 요소는 이전 요소보다 큰 값을 갖는다
- 부분 수열이므로 연속되어야 할 필요가 없다
- 생성 가능한 부분수열들 중 합이 가장 큰 경우의 합을 출력

2. 아이디어
- 생성하고자 하는 수열과 같은 크기의 배열을 만들어 각 index 에
    해당 위치까지 가질 수 있는 가장 큰 수열의 합을 기록하는 방식으로 풀어보려 한다

3. 어려움 및 해결방식
- 입력값을 기록하는 배열 arr 과 부분수열의 합을 기록하는 배열 check 를 만든다
- check 를 순회한다
    - 이전의 arr 값들 중 arr[i] 보다 작은 값을 갖는 index = j1, j2, ... 라고 할때
    가장 큰 check[j] 의 값에 arr[i] 를 더한다

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11055_가장_큰_증가하는_부분_수열 {

    static int[] arr;
    static int[] check;
    static int N;

//    static void showArr(){
//        for(int x : arr) System.out.print(x + " ");
//        System.out.println();
//    }
//    static void showCheck(){
//        for(int x : check) System.out.print(x + " ");
//        System.out.println();
//    }

    static void DP(int i){
        int max = 0;
        for(int j = 0; j<i; j++){
            if(arr[j]<arr[i] && max<check[j]){
                max = check[j];
            }
        }
        check[i] = arr[i] + max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        check = new int[N];

        StringTokenizer stk = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        check[0] = arr[0];
//        showArr();
//        showCheck();
        for(int i=1; i<N; i++){
            DP(i);
        }

        int answer = Integer.MIN_VALUE;
        for(int x : check){
            answer = Math.max(x, answer);
        }
        System.out.println(answer);
    }
}
