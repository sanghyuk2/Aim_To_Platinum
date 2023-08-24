package aim_to_platinum.week09_binary_tree.b1654;
/*
1. 문제 요약
- 첫째 줄에 이미 갖고 있는 랜선의 개수 K(1 <= K <= 10,000)와
    필요한 랜선의 개수 N(1 <= N <= 1,000,000)을 입력받는다
        - 이 때 항상 (K <= N) 이다
- 그리고 다음 K줄에 걸쳐 갖고있는 K개의 랜선의 길이를 각각 입력받는다
- 이 때 최종적으로 만들어 낸 N개의 랜선의 길이가 될 수 있는 값들 중 최대값을 구하면 된다
    - 랜선의 길이 <= 2^31-1 이다


2. 아이디어 (문제 접근법)
[아이디어-1]
- Upper Bound 를 사용해서 풀이가 가능할 것 같다
    - min, mid, max 를 사용한다
    - 1을 min 에, 가장 긴 길이를 max 에 할당한다
- 그리고 다음을 반복한다
    while(min < max)
    - mid = (min + max) / 2
    - K개의 랜선을 mid 로 나눠서 몇 개의 랜선이 나오는지 구한다
        이 때 개수를 LAN 이라고 하면
        1. LAN < N 일 경우
            랜선의 길이를 줄여야한다
            max = mid
        2. LAN >= N 일 경우
            랜선의 길이를 늘려야 한다
            min = mid + 1


3. 어려움 및 해결
- 첫 번째 풀이였던
    long min = 1;
    long mid;
    long max = 0;
    for(int i = 0; i < K; i++){
        lanArr[i] = Integer.parseInt(br.readLine());
        max = Math.max(lanArr[i], max);
    }
    에서는 중간 케이스에서 틀렸습니다가 떴다

    하지만 아래처럼 수정하니 답이 맞았다
    long min = 0; //수정
    long mid;
    long max = 0;
    for(int i = 0; i < K; i++){
        lanArr[i] = Integer.parseInt(br.readLine());
        max = Math.max(lanArr[i], max);
    }
    max++; //수정

    만약 갖고 있는 랜선의 길이가 모두 1 이라면
    첫 번째 풀이의 경우 min, mid, max 각각은
    min = 1, mid = 1, max = 1

    이 때 while(min < max) 이라는 조건 때문에 이분 탐색이 진행되지 않아
    1 - 1 = 0 을 답으로 가져가게 된다는 케이스가 있었다.
    입력받는 K개의 랜선의 길이는 자연수이니, max 를 구한 후 +1 해주어
    위와같은 케이스에서도 무조건 이분탐색을 진행 하도록 한다

    Upper Bound 를 사용하는 경우 구하고자 하는 답 +1 을 반환하므로
    -1 을 해주어야 한다는 부분에서 발생한 문제였다
 */

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_1654_랜선_자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(stk.nextToken());
        int N = Integer.parseInt(stk.nextToken());

        int[] lanArr = new int[K];


        long min = 1; // 0도 상관 없음
        long mid;
        long max = 0;
        for(int i = 0; i < K; i++){
            lanArr[i] = Integer.parseInt(br.readLine());
            max = Math.max(lanArr[i], max);
        }
        max++;

        while(min < max){
            mid = (min + max) / 2;
            long count = 0;

            for(int i = 0; i < K; i++){
                count += lanArr[i] / mid;
            }

            if(count < N){
                max = mid;
            }else{
                min = mid + 1;
            }
        }

        sb.append(min - 1);
        bw.write(sb + "\n");
        bw.flush();

        bw.close();
        br.close();
    }
}
