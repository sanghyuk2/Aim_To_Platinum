package aim_to_platinum.week04_DP2.b12865;


/*
1. 문제 요약
- 물품의 수 N 만큼 각각 무게 W와 가치 V를 가진 물품을 입력받는다
- 준서가 견딜 수 있는 무게를 K라고 할 때 가능한 가장 높은 가치의 물건을 담는 경우의 가치를 출력

2. 아이디어 (문제 접근법)
[아이디어-1]
- T 인스턴스를 생성해 저장할 배열 tArr[N] 를 만든다
- 각 무게에서의 최고 가치를 저장할 배열 maxValue[K+1] 를 만들어둔다
- tList 순회, 그 안에서 maxValue 순회하며 다음과 같이 처리
    - 새로운 물건 tList[i]를 maxValue[j] 에 넣을 수 있는가?
        -> 있다
            - (지금의 최고 가치) vs (지금의 무게 - 새로운 물건의 무게) 때의 최고 가치 + 새로운 물건의 가치
        -> 없다
            - 직전의 값(현재까진 가장 큰 V 가짐) 그대로 저장
[아이디어-2]
- "(지금의 무게 - 새로운 물건의 무게) 때의 최고 가치 + 새로운 물건의 가치" 부분에서 (이전 값을 불러오는 과정에서) 이전값을 누적해서 저장하는 문제점 발견
    이전의 최고가치를 계속 누적하는 문제의 해결 방법을 찾던 도중
    https://st-lab.tistory.com/141 에서 maxValue 에 해당하는 dp 배열을 끝에서 처음으로 탐색하는 것을 발견
    순회 순서를 바꾸니 이전값이 누적되는 문제가 해결되었다..

3. 어려움 및 해결
[아이디어-1] -> 이미 담았던 값을 또 담는 문제 발생
            -> 이를 해결하기 위해 담은 T를 체크하는 배열 생성하고 하다보니 코드가 너무 비효율적으로 길어져 원점으로 복귀
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class T{
    int W, V;
    public T(int W, int V){
        this.W = W;
        this.V = V;
    }
}
public class BOJ_12865_평범한_배낭 {
    static T[] tArr;
    static int[] maxValue;
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        tArr = new T[N];
        K = Integer.parseInt(stk.nextToken());
        maxValue = new int[K+1];

        // tArr 에 T 저장
        for(int i=0; i<N; i++){
            stk = new StringTokenizer(br.readLine());
            tArr[i] = new T(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
        }

        maxValue[0] = 0;
        for(int i=0; i<N; i++){
            T t = tArr[i];
            int w = t.W;
            int v = t.V;
            for (int j = K; j-w >= 0; j--) {
                maxValue[j] = Math.max(maxValue[j], maxValue[j-w] + v);
            }
//            for(int j=1; j<=K; j++){
//                if(j-w<0) continue;
//                maxValue[j] = Math.max(maxValue[j], maxValue[j-w]+v);
//            }
        }

        System.out.println(maxValue[K]);
    }
}
