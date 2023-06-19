package aim_to_platinum.week05_greedy.b20300;
/*
1. 문제 요약
- 운동기구 N 개에 대하여 각 기구의 근손실 정도가 주어진다
- 운동기구 N 개일 때 PT 날은
    - 짝수일 때 N/2 일
        매일 기구 두 개씩 사용
    - 홀수일 때 N/2+1 일
        마지막날만 한 개 사용, 그 외 날짜는 두 개씩 사용
- 이런 조건에서 PT 날 근손실 정도가 M을 넘지 않게 할 때 M의 최소값 구하는 문제

2. 아이디어 (문제 접근법)
[아이디어-1]
- 근손실 정도를 배열 loss 에 담고, 이후 Array.sort(loss) 로 오름차순 정렬
- N이 홀수일때와 짝수일 때 두 가지 경우로 나눠서 생각해 보자
    - N이 홀수일 때
        M은 loss[N-1] 보다 높을수도, 적을수도 있지만
        loss[N-1]에 다른 요소를 더하는 경우 어떤 경우보다 큰 값이 나오므로 마지막 날에 배치
        - M을 loss[N-1] 로 두고 시작
        - 이후 loss[0] 과 loss[N-2], loss[1] 과 loss[N-3] ...
            을 더하며 M 보다 큰 수 나올 경우 업데이트
    - N이 짝수일 때
        - M을 loss[0]+loss[N-1] 로 두고 시작
        - 이후 loss[0] 과 loss[N-1], loss[1] 과 loss[N-2] ...
            을 더하며 M 보다 큰 수 나올 경우 업데이트
- 최종적으로 나오는 M을 출력

3. 어려움 및 해결
[아이디어-1] ->
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20300_서강근육맨 {
    static int N;
    static long[] loss;

    static long oddSolution(){
        long M = loss[N-1];
        for(int i=0; i<N/2; i++){
            M = Math.max(M, loss[i]+loss[N-2-i]);
        }
        return M;
    }
    static long evenSolution(){
        long M = 0;
        for(int i=0; i<N/2; i++){
            M = Math.max(M, loss[i]+loss[N-1-i]);
        }
        return M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        N = Integer.parseInt(br.readLine());
        loss = new long[N];
        stk = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            loss[i] = Long.parseLong(stk.nextToken());
        }
        Arrays.sort(loss);

        if(N%2 == 1){
            System.out.println(oddSolution());
        }else if(N%2 == 0){
            System.out.println(evenSolution());
        }
    }
}
