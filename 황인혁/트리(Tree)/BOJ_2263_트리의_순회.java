/*
1. 문제이해
n개의 정점을 갖는 이진트리가 주어지고 중위순회(왼쪽→루트→오른쪽)와 후위순회(왼쪽→오른쪽→루트)가 주어졌을 때, 전위순회를(루트→왼쪽→오른쪽) 출력하는 문제
입력 : N(노드의 개수)
          중위순회
          후위순회
2. 아이디어
루트를 찾아서 중위순회루트를 기준으로 왼쪽 오른쪽 노드를 나눌수 있다.
주어진 후위순회의 맨 마지막 노드가 루트노드이고 중위순회에서 그것을 토대로 트리를 구성할 수 있다.
3. 어려움 및 해결방식 X
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2263_트리의_순회 {
    static int[] in, post;
    static int[] preOrder;
    static int idx ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        in = new int[n+1];
        post = new int[n+1];
        preOrder = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++){
            in[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++){
            post[i] = Integer.parseInt(st.nextToken());
        }

        idx = 1;
        makePreOrder(1, n, 1, n);

        for (int i = 1; i <= n ; i++) {
            System.out.print(preOrder[i] + " ");
        }
    }

    public static void makePreOrder(int inSt, int inEd, int poSt, int poEd){

        if(inSt <= inEd && poSt <= poEd){
            preOrder[idx++] = post[poEd];

            int root = inSt;
            for(int i = inSt; i <= inEd; i++){
                if (in[i] == post[poEd]){
                    root = i;
                    break;
                }
            }

            makePreOrder(inSt, root-1, poSt, poSt + root - inSt - 1);
            makePreOrder(root + 1, inEd, poSt + root  - inSt, poEd -1);
        }
    }
}