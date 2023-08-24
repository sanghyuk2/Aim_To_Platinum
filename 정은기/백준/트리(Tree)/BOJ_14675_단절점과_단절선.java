package aim_to_platinum.week07_tree.b14675;

/*
1. 문제 요약
- 정점 개수 N 을 입력받는다
    - 이후 N-1 번에 걸쳐 간선의 정보가 주어진다
    - 이 때 a b 값이 주어지면 a 정점과 b 정점이 연결되어있다는 뜻이다
- 다음 질문의 개수 q를 입력받고 q 만큼 질문을 입력받는다
    - 1 k(1~N-1) 일 때는 k번 정점이 단절점인지에 대한 질의이다
    - 1 k(1~N-1) 일 때는 k번째 입력받은 간선이 단저런인지에 대한 질의이다

2. 아이디어 (문제 접근법)
[아이디어-1]
- 우선 모든 간선은 단절선이다
    A -- B -- C
     \    \
      E    D
    위와 같은 트리가 있을 때
    리프 노드와 연결된 어떤 간선을 제거하여도 두 개의 트리로 나눠지게 된다
    루트 노드와 연결된 간선 혹은 그 외의 간선도 마찬가지다
- 단절점이 되는 노드의 조건은 간단하다
    연결된 노드의 개수가 1개가 아니면 전부 단절점이 된다
- 따라서 노드 개수 N+1 만큼의 크기를 갖는 int 배열 count 를 만들어
    간선 정보를 입력받을 때 마다 1씩 증가시켜준 후에
    이후 count[a] 를 확인하여 단절점 여부를 확인할 수 있다


3. 어려움 및 해결
- 처음 '모든 간선은 단절선'이라는 아이디어에 확신이 없어서
    틀린 답이라는 결과가 나오면 단절점을 구하는 과정과 마찬가지로 연결된 간선의 개수를 구할 생각이었다
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14675_단절점과_단절선 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int N = Integer.parseInt(br.readLine());
        int[] count = new int[N+1];
        while(N --> 1){
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            count[a]++;
            count[b]++;
        }

        int Q = Integer.parseInt(br.readLine());
        while(Q --> 0){
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            if(a == 1){
                if(count[b] == 1) System.out.println("no");
                else System.out.println("yes");
            }else if(a == 2){
                System.out.println("yes");
            }
        }
    }
}
