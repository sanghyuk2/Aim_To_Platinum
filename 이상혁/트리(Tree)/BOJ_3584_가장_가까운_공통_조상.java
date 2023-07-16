/*
### 1. 문제 요약

- 두 노드가 주어질 때 노드들이 공통으로 가지는 부모 중 두 노드에 가장 가까운 노드를 출력하시오
    - 예를 들어, 트리의 루트 노드가 8, 자식 노드가 4, 노드 4의 자식 노드가 15와 11을 가지고 있을 때, 8과 4 모두 조상 노드이지만, 가장 가까운 공통 조상 노드는 4이다

### 2. 아이디어(문제 접근법)

- LCA 알고리즘을 사용하여 depth를 맞춘 뒤 depth를 동시에 줄여나가며 값이 동일할 때 공통되는 조상의 값을 반환하도록 하였다

### 3. 어려움 및 해결방식

- 어려움) 처음에는 x의 부모를 저장하는 Parent[x]를 사용하여 계속하여 depth를 줄여나가려고 했으나, 왜인지 찾지 못하였음
- 해결방법) 검색을 통해 LCA(Lowest Common Ancestor)라는 알고리즘을 발견하였다
    - 4번 참고자료 항목에 링크를 걸어두었으니 읽어보자

### 4. 참고자료

- https://www.crocus.co.kr/660
- https://4legs-study.tistory.com/121
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
    int value;
    Node parent;
    List<Node> children;

    public Node(int value) {
        this.value = value;
        this.children = new ArrayList<>();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine()); // 노드 개수

            Node[] nodes = new Node[N + 1]; // 노드 배열
            for (int i = 1; i <= N; i++) {
                nodes[i] = new Node(i);
            }

            // 트리 구성
            for (int i = 0; i < N - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int parentValue = Integer.parseInt(st.nextToken());
                int childValue = Integer.parseInt(st.nextToken());
                nodes[childValue].parent = nodes[parentValue];
                nodes[parentValue].children.add(nodes[childValue]);
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int target1 = Integer.parseInt(st.nextToken());
            int target2 = Integer.parseInt(st.nextToken());

            // 가장 가까운 공통 조상 찾기
            Node lca = findLCA(nodes[target1], nodes[target2]);

            sb.append(lca.value).append('\n');
        }

        System.out.print(sb.toString());
    }

    // LCA 알고리즘을 사용하여 가장 가까운 공통 조상 찾기
    private static Node findLCA(Node node1, Node node2) {
        int depth1 = getDepth(node1);
        int depth2 = getDepth(node2);

        // 두 노드의 깊이를 맞춤
        while (depth1 > depth2) {
            node1 = node1.parent;
            depth1--;
        }
        while (depth2 > depth1) {
            node2 = node2.parent;
            depth2--;
        }

        // 공통 조상을 찾을 때까지 탐색
        while (node1 != node2) {
            node1 = node1.parent;
            node2 = node2.parent;
        }

        return node1;
    }

    // 노드의 깊이를 계산
    private static int getDepth(Node node) {
        int depth = 0;
        while (node.parent != null) {
            node = node.parent;
            depth++;
        }
        return depth;
    }
}