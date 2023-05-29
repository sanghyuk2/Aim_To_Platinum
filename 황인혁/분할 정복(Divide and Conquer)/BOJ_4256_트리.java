/*
1. 문제이해
첫번째 줄에 테스트 케이스 수 T가 주어지고
두번째 줄에 노드수가 주어지고
세번째 줄에 전위순회한 결과
네번째 줄에 중위순회한 결과
두번째 줄에서 네번째 줄까지 T만큼 반복
입력 : T(테스트 케이스), N(노드 수)
출력 : 트리별 후위순회한 결과
조건
모든 노드는 최대 2개의 자식노드만 가질수 있다.
전위순회시 맨 앞에 있는 노드가 루트노드 이다.
2. 아이디어
주어진 전위순회의 맨앞에 있는 노드가 루트노드 이고 중위순회 결과에서 루트노드를 기준으로 왼쪽, 오른쪽 서브트리를 알아낼수 있다. 이를 이용해서 후위 순회를 수행하여 테스트 케이스 수 만큼 실행한 후 출력한다.
* 전위 : 루트 → 왼쪽 → 오른쪽
  중위  : 왼쪽 → 루트 → 오른쪽
  후위 : 왼쪽 → 오른쪽 → 루트
3. 사용변수
int T : 테스트 케이스 수
int n : 각 테스트 케이스별 노드 수
int[] preorder : 전위 순회 결과를 저장하는 리스트
int[] inorder : 중위 순회 결과를 저장하는 리스트
4. 자료구조/메서드
postOrder(int start, int end, int idx)
5. 문제점/해결책
주어진 입력값을 토대로 트리를 구성한 후에 풀려고 했다. 하지만 방법이 복잡하다고 생각해서 구현하지 못했고 주어진 입력값으로 패턴을 알아냈다. 전위순회의 맨 처음 입력값은 루트노드이고
이를 기준으로 중위순회 입력값에서 노드를 왼쪽, 오른쪽으로 이등분 할수 있고 나눈 기준으로 전위순회의 입력값에서 맨 처음입력 값이 또 서브트리의 제일 위에 노드이다. 이렇게 해서 계속 이등분을 해서 노드의 위치를 구할수 있었다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bk_4256 {
    static int[] preorder;
    static int[] inorder;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());

            preorder = new int[n];
            inorder = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                preorder[j] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                inorder[j] = Integer.parseInt(st.nextToken());
            }

            postOrder(0, n, 0);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void postOrder(int start, int end, int idx){
        for (int now = start; now < end ; now++) {
            if(preorder[idx] == inorder[now]){
                postOrder(start, now, idx+1);
                postOrder(now+1, end, idx+1+now-start);
                sb.append(preorder[idx]).append(" ");
                break;
            }
        }
    }
}