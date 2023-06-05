package aim_to_platinum.week03_DP.b1106;

/*
1. 문제 요약
- C 이상의 고객을 늘려야 하고 N 개의 도시가 있을 때
- N 개의 도시들 각각에 대한 홍보 비용 cost 와
- 얻을 수 있는 고객의 수 customer 를 입력받는다
    - 각각의 도시는 cost 의 정수배 만큼만 홍보 가능하다
    - customer 은 최대 100이다

2. 아이디어
- 처음 아이디어
    - 비용당 얻을 수 있는 고객의 수 (얻는 고객의 수/비용) 가 가장 많은 도시를 고르고
    - 그 도시에 C 만큼의/C를 넘지 않는 만큼의 홍보를 한 후
    - 도시들 중 가장 적은 비용으로 C 이상이 되는 도시에 홍보하기

- 힌트 참고 후
    - 적어도 C명의 고객을 얻는 것이 목표이기에
        C명 얻는 비용 > C+n명 얻는 비용
        이런 상황이 발생 가능하다
        이런 경우 C+n 명을 얻는 것이 답에 가까우므로 다음과 같이 해결해야 한다

    - customer 는 최대 100이라는 조건 때문에 홍보를 통해 얻을 수 있는 최대 고객의 수는 C+100 이다
        int maxCustomer = C+100
        - 이를 위한 int[] costForIdx = new int[maxCustomer+1] 를 만든다
            -> 각 인덱스만큼의 고객을 얻기 위한 최소 비용
            - 최소값을 구하는 과정이므로 초기값을 MAX_VALUE 로 세팅 한다
            - costForIdx[0] = 0 (나중 정수배 투자 부분을 위해 세팅)

    - 이후 각 도시별 홍보비용 cost 와 이 때 얻을 수 있는 고객의 수 customer 를 입력받는다
        - customer 부터 maxCustomer 까지 순회하며 아래를 반복한다
        [각 index 의 최소비용] 과 [각 index-customer 의 비용 + cost] 를 비교해 더 작은값으로 업데이트 한다
            - 만약 index-customer 의 비용이 MAX_VALUE 그대로라면
                - 업데이트되지 않아 최소값을 구할수 없으므로 패스한다
            -> 정수배 홍보

    - 위 과정을 마친 후 costForIdx 의 index C 부터 maxCustomer 범위의 최소값을 구한다

3. 어려움 및 해결방식
- 최소 C 명을 늘린다는 의미를 처음에 제대로 이해하지 못했었다
- cost 를 통해 한 번에 늘릴 수 있는 고객의 최대 수가 100 이라는 조건을 활용할 방법을 떠올리지 못했다

각각에 대한 해결방식은 2.아이디어 - 힌트 참고 후 참조
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1106_호텔 {

    static int[] costForIdx; // index 명을 늘릴때의 cost
    static int maxCustomer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(stk.nextToken()); // C명 이상의 고객
        int N = Integer.parseInt(stk.nextToken()); // 도시의 개수 N
        maxCustomer = C+100;

        costForIdx = new int[maxCustomer+1]; // 한 번에 얻을 수 있는 고객의 수 <= 100
        // 각 Index 만큼의 고객을 얻기 위해 필요한 최소 비용을 MAX_VALUE 로 세팅
        Arrays.fill(costForIdx, Integer.MAX_VALUE);

        // 나중 정수배만큼 홍보하는 부분을 위해 세팅해야 한다
        costForIdx[0] = 0;

        for(int i=0; i<N; i++){
            // 각 도시별로 비용별 얻을 수 있는 사람의 수를 입력받고
            stk = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(stk.nextToken());
            int customer = Integer.parseInt(stk.nextToken());

            // cost 로 얻을 수 있는 사람의 수 부터 최대 얻을 수 있는 사람의 수 까지 순회
            // 순회하며 현재 최소 비용과 (현재 - cost 로 얻을 수 있는 사람의 수)의 비용중 더 작은 값으로 업데이트 한다
                // 정수배만큼 추가로 홍보하는 부분
            // 만약 업데이트 되어있지 않다면 값을 구할 수 없으므로 패스한다
            for(int j=customer; j<=maxCustomer; j++){
                if(costForIdx[j-customer] == Integer.MAX_VALUE) continue;
                costForIdx[j] = Math.min(costForIdx[j], costForIdx[j-customer]+cost);
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i=C; i<=maxCustomer; i++){
            answer = Math.min(answer, costForIdx[i]);
        }
        System.out.println(answer);
    }
}
