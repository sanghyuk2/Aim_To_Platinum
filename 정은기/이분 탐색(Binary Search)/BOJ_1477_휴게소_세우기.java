package aim_to_platinum.week09_binary_tree.b1477;
/*
1. 문제 요약
- 첫째줄에서 현재 휴게소의 개수 N(0 ≤ N ≤ 50)
    더 지으려고 하는 휴게소의 개수 M(1 ≤ M ≤ 100)
    고속도로의 길이 L(100 ≤ L ≤ 1,000)을 입력받는다
- 이어서 N개의 휴게소의 위치를 입력받는다
    고속도로의 끝과 이미 휴게소가 있는 곳에 휴게소를 설치할 수 없고
    입력받는 휴게소의 위치는 모두 정수일 때
- 휴게소가 없는 구간의 최댓값의 최솟값을 출력한다


2. 아이디어 (문제 접근법)
[아이디어-1]
- 휴게소를 짓고자 하는 구간을 찾는 문제이다
    입력받은 휴게소 위치를 정렬한 후
    아래와 같이 휴게소가 없는 구간의 거리에 초점을 맞춰 이분탐색을 시도하면 된다
    이 때 새로 짓고자 하는 휴게소가 M개 인데,
    각 휴게소 간 거리를 휴게소가 없는 구간의 거리로 나누고
    이들의 총 합을 구하여 M과 비교한다
    1. M보다 더 많을 경우
        - 휴게소가 없는 구간의 거리를 늘인다
            min = mid + 1;
    2. M보다 적을 경우
        - 휴게소가 없는 구간의 거리를 줄인다
            max = mid;


3. 어려움 및 해결
- int min = 0; -> 이 부분에서 런타임 에러 (/ by zero) 가 발생했다
    어차피 도로의 시작으로부터 거리가 0인 경우는 고려할 필요 없으니
    min = 1 로 시작하니 해결되었다
 */


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1477_휴게소_세우기 {

    static boolean solution(int mid, int M, ArrayList<Integer> list){
        int count = 0;

        for(int i = 1; i < list.size(); i++) {
            count += (list.get(i) - list.get(i - 1) - 1) / mid;
        }

    //        if(count >= M){
        if(count > M){
            return true;
        }else{
            return false;
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
        int L = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(L);
        for(int i = 0; i < N; i++){
            list.add(Integer.parseInt(stk.nextToken()));
        }
        Collections.sort(list);

//        int min = 0; ->
        int min = 1;
        int mid;
        int max = L - 1;
        while(min < max){
            mid = (min + max) / 2;
            if(solution(mid, M, list)){
                min = mid + 1;
            }else{
                max = mid;
            }
        }

        sb.append(min);
        bw.write(sb + "\n");
        bw.flush();

        bw.close();
        br.close();
    }
}
