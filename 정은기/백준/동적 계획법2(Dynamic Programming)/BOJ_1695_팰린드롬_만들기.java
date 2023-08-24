package aim_to_platinum.week04_DP2.b1695;
/*
1. 문제 요약
- 제한시간 2초

2. 아이디어 (문제 접근법)
[아이디어-1]
- 참조 링크의 풀이를 태블릿 그림으로 이해하며 풀어보았다
- 입력받은 수열을 기록하기 위해 int[] arr = new int[N] 생성
- 다른 DP 문제들과 마찬가지로 연산 결과를 배열에 담기 위해 int[][] DPArr = new int[N][N] 를 생성
    - 이후 DPArr 의 값들을 -1로 초기화한다
- arr 의 왼쪽과 오른쪽 투 포인터 역할을 할 int left 와 int right 를 파라미터로 갖는
    DP(int left, int right) -> DP(0, N-1) 실행
    다음 포인터란 left+1 과 right-1을 의미한다 (가운데로 범위를 좁혀감)
    - if (left >= right) return 0;
        투 포인터가 교차하면
        - 0 반환
    - if (DPArr[left][right] != -1) return DPArr[left][right];
        이미 해당 위치까지의 최소값을 구했으면
        - 해당 값 반환
    - if (arr[left] == arr[right])
        각 포인터가 가리키는 값이 같으면
        - DPArr[left][right] = DP(left+1, right-1);
        - return DP(left+1, right-1);
            다음 포인터가 가리키는 값을 기록하고 반환 (추가한 게 없기 때문)
    - else
        각 포인터가 가리키는 값이 다르면
        - DPArr[left][right] = Math.min(DP(left+1 ,right)+1, DP(left ,right-1)+1);
        - return Math.min(DP(left+1 ,right)+1, DP(left ,right-1)+1);
            왼쪽이나 오른쪽 포인터 중 하나만 다음 위치로 이동
            이후 해당 위치의 DP 값중 더 작은 값 + 1 을 기록하고 반환 (숫자 하나를 추가하기 때문)

3. 어려움 및 해결
- 수열이 주어졌을 때 왼쪽, 오른쪽 끝 각각에 left, right 포인터를 만든다
- 이후 가운데로 하나씩 좁혀오며 포인터가 가리키는 값이 다른 경우
    - 왼쪽에 오른쪽 값 생성
    - 오른쪽에 왼쪽 값 생성
        의 두 가지 경우의 수 발생
- 이것만으로는 메모이제이션은 가능하지만 최소의 개수가 나오는 경우의 수를 구하지 못한다
- 아이디어가 나오지 않아 다른 사람의 풀이를 참조 (https://velog.io/@ynoolee/%EB%B0%B1%EC%A4%801695%EB%B2%88-%ED%8C%B0%EB%A6%B0%EB%93%9C%EB%A1%AC-%EB%A7%8C%EB%93%A4%EA%B8%B0)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1695_팰린드롬_만들기 {

    static int N;
    static int[] arr;
    static int[][] DPArr;

    static int DP(int left, int right){
        if(left >= right) return 0;
        if(DPArr[left][right] != -1) return DPArr[left][right];

        if(arr[left]==arr[right]){
            int min = DP(left+1, right-1);
            DPArr[left][right] = min;
            return min;
        }else{
            int min = Math.min(DP(left+1 ,right)+1, DP(left ,right-1)+1);
            DPArr[left][right] = min;
            return min;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        DPArr = new int[N][N];
        stk = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
            Arrays.fill(DPArr[i], -1);
        }

        System.out.println(DP(0, N-1));
    }
}
