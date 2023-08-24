package aim_to_platinum.week07_tree.b3584;
/*
1. 문제 요약
- 공통 조상에 대한 정의는 다음과 같다
    두 노드를 모두 자손으로 가지면서 깊이가 가장 깊은 노드
- 테스트 케이스의 수 T를 입력받는다
    --- 각 테스트 케이스 ---
    노드의 수 N(2<=N<=10000)을 입력받는다
    N-1 번에 걸쳐 간선 정보가 주어진다
        A B 입력을 받을 경우 A는 B의 부모노드임을 뜻한다
    이후 가장 가까운 공통조상을 구할 두 노드를 입력받는다

2. 아이디어 (문제 접근법)
[아이디어-1]
- 각 index 부모 노드를 기록해놓은 int[N+1] 배열 parent 를 생성한다
- A의 부모 노드를 기록해 놓을 boolean[N+1] 배열 isParent 를 생성한다
    parent[A] 를 시작으로
    root 노드에 도달할 때까지 isParent 를 true 로 업데이트 한다
- parent[B]를 시작으로
    root 노드에 도달 할 때까지 isParent 를 순회한다
        그 과정에서 isParent 값을 true 로 갖는 index를 만나면
        해당 index가 가장 가까운 공통 조상이 된다
=> 시간초과

3. 어려움 및 해결
- 답은 맞게 나오는데 시간 초과가 발생한다

 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3584_가장_가까운_공통_조상_2 {

    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int T = Integer.parseInt(br.readLine());

        // T 케이스
        for(int t=0; t<T; t++){
            int N = Integer.parseInt(br.readLine());
            int[] parent = new int[N+1];
            boolean[] isParent = new boolean[N+1];
            // 노드 개수
            for(int j=0; j<N-1; j++){
                // B 의 부모 노드는 A 이다 <= 저장
                stk = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(stk.nextToken());
                int B = Integer.parseInt(stk.nextToken());
                parent[B] = A;
            }

            stk = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(stk.nextToken());
            int B = Integer.parseInt(stk.nextToken());

            while(true){
                if(A == 0) break;
                isParent[A] = true;
                A = parent[A];
            }

            while(true){
                if(isParent[B]){
                    sb.append(B).append("\n");
                    break;
                }
                B = parent[B];
            }
        }

        System.out.print(sb.toString());
    }
}
