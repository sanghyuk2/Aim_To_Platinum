package aim_to_platinum.week09_binary_tree.b13397;
/*
1. 문제 요약
- 배열의 크기 N(1 ≤ N ≤ 5,000) 과
    나누고자 하는 구간의 개수 M(1 ≤ M ≤ N)
    을 입력받는다
- 구간의 점수를 구간 내 최댓값과 최솟값의 차라고 할 때
    구간의 점수의 최댓값을 최소로 해야 한다
    - 이 때 그 값을 출력한다


2. 아이디어 (문제 접근법)
[아이디어-1]
- 랜선 자르기와 나무자르기 문제에서 이분탐색으로 찾고자 하는 값이 길이였다면
    이 문제에서 찾고자 하는 값은 구간을 나누는 기준이다
    구간의 점수 = 구간 내 최댓값과 최솟값의 차이이므로
    구간을 나누는 기준은 숫자 간 차이가 된다

- 입력받은 숫자들 중 최댓값과 최솟값을 찾아 각각 max, min 에 할당한다
- 그리고 다음을 반복한다
- while(min < max)
    - mid = (min + max) / 2
    - 만약 나눠진 구간의 개수가 M 초과라면
        - min = mid + 1
    - 만약 나눠진 구간의 개수가 N 이하라면
        - max = mid

- 나눠진 구간의 개수를 구하는 방법은 다음과 같다
    - 구간 시작 부분
        minValue = Integer.MAX_VALUE
        maxValue = Integer.MIN_VALUE
    - 구간 나누기 부분
        이후 배열을 순회하면서 minValue 와 maxValue 를 업데이트 한다
            (구간의 점수가 바뀐다)
        이후 midValue 와 비교해서 구간의 점수가 midValue 를 넘게 되면
            현재 구간의 전까지를 구간으로 나눈다
    - return 부분
        나눠진 구간의 개수가 M 이하라면 true
        나눠진 구간의 개수가 M 초과라면 false



3. 어려움 및 해결
-
 */

import java.io.*;
import java.util.StringTokenizer;


public class BOJ_구간_나누기 {

    static boolean solution(int mid, int[] arr, int M){
        int count = 1;
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;

        for(int i = 0; i < arr.length; i++){
            minValue = Math.min(minValue, arr[i]);
            maxValue = Math.max(maxValue, arr[i]);
            if(maxValue - minValue > mid){
                maxValue = Integer.MIN_VALUE;
                minValue = Integer.MAX_VALUE;
                count++;
                i--;
            }
        }

        if(count > M){
            return false;
        }else {
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());

        int[] arr = new int[N];
        stk = new StringTokenizer(br.readLine());
        int min = 0;
        int mid;
        int max = 0;
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(stk.nextToken());
            max = Math.max(max, arr[i]);
        }

        while(min < max){
            mid = (min + max) / 2;

            if(solution(mid, arr, M)){
                max = mid;
            }else{
                min = mid + 1;
            }
        }

        sb.append(max);
        bw.write(sb + "\n");
        bw.flush();

        bw.close();
        br.close();
    }
}
