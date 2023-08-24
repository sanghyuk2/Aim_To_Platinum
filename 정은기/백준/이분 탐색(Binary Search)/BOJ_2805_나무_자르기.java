package aim_to_platinum.week09_binary_tree.b2805;
/*
1. 문제 요약
- 나무의 수 N을 입력받는다 (1 ≤ N ≤ 1,000,000)
- 필요한 나무의 길이 M을 입력받는다 (1 ≤ M ≤ 2,000,000,000)
- N개 나무의 높이들을 입력받는다
- 절단기의 높이를 X 라고 할 때, 상근이가 얻게 될 나무의 길이의 합이 M 이상이 되도록 한다
    이 때 절단기의 높이를 구하면 된다


2. 아이디어 (문제 접근법)
[아이디어-1]
- Up Down 게임을 하듯이 UpperBound 방식을 사용한다
    자른 높이의 합이 찾고자 하는 길이 이상일 때까지 반복한다

- low = 0, high = 제일 높은 나무의 높이 H 로 시작한다
    나무들을 H/2 로 잘랐을 때
   1. 잘린 길이의 합이 M 보다 크면
        - 절단기 높이를 올린다
        - 절단기 높이는 (X + high) / 2 이다
   2. 잘린 길이의 합이 M 보다 작으면
        - 절단기 높이를 내린다
        - 절단기 높이는 (X + low + 1) / 2 이다

- 위 단계를 반복하며 자른 나무의 합이 M 이상을 만족하게 될 때 다음을 반복한다
    자른 나무의 합이 M 이상을 만족하게 될 때
    - M 을 answer 에 저장한다


3. 어려움 및 해결
-
 */

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_2805_나무_자르기 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());

        int low = 0;
        int high = 0;

        int[] treeArr = new int[N + 1];
        stk = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            treeArr[i] = Integer.parseInt(stk.nextToken());
            high = Math.max(high, treeArr[i]);
        }

        while(low < high){
            int X = (low + high) / 2;

            long sum = 0;

            for(int height : treeArr){
                if(height > X){
                    sum += height - X;
                }
            }
            if(sum < M){
                high = X;
            }else{
                low = X + 1;
            }
        }

        int answer = low - 1;

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }
}
