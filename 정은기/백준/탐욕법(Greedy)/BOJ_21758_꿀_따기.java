package aim_to_platinum.week05_greedy.b21758;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. 문제 요약
- 3 <= N <= 100,000 인 N을 입력받고 크기 N의 배열에 각각 꿀의 양을 입력받아 저장한다
- 다음 조건을 따라 얻을 수 있는 꿀의 최대값을 구하는 문제
    - 벌 두 마리의 자리, 벌통의 자리를 나타내는 각각 다른 세 인덱스를 정한다
    - 벌 각각의 위치에서 꿀의 위치까지의 인덱스들을 조회하며 해당 인덱스의 꿀의 양을 더한다
        - 이 때, 벌의 위치에 있는 꿀은 더할 수 없다 (자신의 위치 포함)

2. 아이디어 (문제 접근법)
[아이디어-1]
- 어떠한 경우든 두 번 더해지는 인덱스는 벌통의 위치이다
    - 벌통의 위치 인덱스가 B 라고 할 때,
        단순히 생각해서 BT를 기준으로 좌우 중 합이 더 큰 쪽의 끝에서
        두 마리가 벌통으로 향하는게 최대값이 될 것이라고 생각했지만
        다음과 같은 예외가 발생할 수 있다
            -> 1[벌1], 99[벌2], 1, 1, 1, 1, [벌통]
            (더 안쪽의 벌 때문에 최대값을 더하지 못하는 경우)
[아이디어-2]
- 우선 벌통과 벌1의 위치만을 고려해보자
    1. 벌통 위치가 [0]이라면 -> 벌1은 [N-1]에 있는게 벌1의 최대값
    2. 벌통 위치가 [N-1]이라면 -> 벌1은 [0]에 있는게 벌1의 최대값

        3.(수정 전) -> 벌통기준 좌우 끝 중에 합이 더 큰 쪽에 있는게 벌1의 최대값
            3-1. 오른쪽의 합이 더 크다면 벌1은 [N-1]에 있는게 벌1의 최대값
                -> 벌2의 경우 같은 오른쪽에 있다면 애초에 벌통을 [0]에 놓는것보다 작다
            3-2. 왼쪽의 합이 더 크다면 벌1은 [0]에 있는게 벌1의 최대값
                -> 벌2의 경우 같은 왼쪽에 있다면 애초에 벌통을 [N-1]에 놓는 것보다 작다
            => 결론 : 1, 2는 벌통기준 벌1, 벌2가 같은 방향에 있을 때의 최대값으로 보면 된다
            => 따라서 3. 을 벌통 기준 벌1, 벌2가 양 옆의 끝에 있는 경우로 봐야한다
                벌1 위치는 [0], 벌2 위치는 [N-1]로 놓고 벌통을 움직이는 것과 같다
    3.(수정) -> 벌1, 벌2를 각각 [0]와 [N-1]에 놓고 벌통을 움직인다

    - 1. / 2. : 다음으로 벌2의 위치를 고려
        1. 의 경우 -> [벌통]------[벌1]
            벌이 위치한 곳의 꿀은 더할 수 없다는 조건 때문에
            [아이디어-1] 에서의 예외 케이스는 어느 인덱스에서든 발생 가능하다
            따라서 벌2의 위치를 [N-2]~[1]까지 순회하며 최대값을 갖는 경우의 인덱스에 위치
        2. 의 경우 -> [벌1]------[벌통]
            같은 이유로 벌 2의 위치를 [1]~[N-2]부터 순회하며 최대값을 갖는 경우의 인덱스에 위치
    - 3. : [0] < [벌통] < [N-1] 위 범위에서 최대값을 구한다
        -> 즉, [1]~[N-2] 의 합에서 그 중 가장 큰 값을 더하면 된다 (벌통은 두 번 더해진다)
            순회를 하나 줄이기 위해 입력받을 때 최대값을 static int maxValue 에 넣자


        위 1, 2, 3의 경우들 중 가장 큰 값을 갖는 경우가 최대값이 된다


3. 어려움 및 해결
- 아이디어 (문제 접근법 참조)
- 의미없는 경우를 제외하는 과정이 오래걸렸다
    ex) [아이디어-2] 의 3.
 */
public class BOJ_21758_꿀_따기 {
    static int N;
    static int[] arr;
    static long answer;
    static int maxValue = Integer.MIN_VALUE;
    static long total = 0;
    static long[] toRight;
    static long[] toLeft;

    static long left(){ // BT --- --- B1
        int BT = 0;
        int B1 = N-1;

        long B1H, B2H;
        long max = 0;

        for(int i=B1-1; i>BT; i--){ // 벌2 위치
            B1H = total - arr[B1] - arr[i];
            B2H = total - toLeft[i];
            max = Math.max(max, B1H+B2H);
        }

        return max;
    }
    static long right(){ // B1 --- --- BT
        int BT = N-1;
        int B1 = 0;

        long B1H, B2H;
        long max = 0;

        for(int i=B1+1; i<BT; i++){ // 벌2 위치
            B1H = total - arr[B1] - arr[i];
            B2H = total - toRight[i];
            max = Math.max(max, B1H+B2H);
        }

        return max;
    }
    static long etc(){ // B1 --- BT --- B2
        int B1 = 0;
        int B2 = N-1;
        long max = total + maxValue - arr[B1] - arr[B2];
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        toLeft = new long[N];
        toRight = new long[N];

        stk = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(stk.nextToken());
            maxValue = Math.max(maxValue, arr[i]);
            total += arr[i];
        }

        toRight[0] = arr[0];
        for(int i=1; i<=N-1; i++){
            toRight[i] = toRight[i-1] + arr[i];
        }
        toLeft[N-1] = arr[N-1];
        for(int i=N-2; i>=0; i--){
            toLeft[i] = toLeft[i+1] + arr[i];
        }

        answer = Math.max(left(), right());
        answer = Math.max(answer, etc());
        System.out.println(answer);
    }
}
