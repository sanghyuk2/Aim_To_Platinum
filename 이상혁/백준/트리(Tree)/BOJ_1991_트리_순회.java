/*
### 1. 문제 요약

- 이진 트리를 입력받아 전위 순회(preorder traversal), 중위 순회(inorder traversal), 후위 순회(postorder traversal)한 결과를 출력하는 프로그램을 작성하시오

### 2. 아이디어(문제 접근법)

- 전위 순회(preorder traversal), 중위 순회(inorder traversal), 후위 순회(postorder traversal) 알고리즘을 다 구현한 뒤 돌려버리자
- HashMap을 자료구조로 선택하여 트리를 구성하였다
    - 이는 구성하는 과정에서 효율적으로 관리하기 위해 사용하였다
    - HashMap은 key-value 쌍으로 데이터를 저장하며, 키를 기반으로 빠른 검색과 접근이 가능하기 때문이다

### 3. 어려움 및 해결방식

- X
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Node {
    char value;
    Node left;
    Node right;

    public Node(char value) {
        this.value = value;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 노드 개수

        // 트리 구성
        Map<Character, Node> nodeMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char parentValue = st.nextToken().charAt(0);
            char leftValue = st.nextToken().charAt(0);
            char rightValue = st.nextToken().charAt(0);

            Node parent;
            if (nodeMap.containsKey(parentValue)) {
                parent = nodeMap.get(parentValue);
            } else {
                parent = new Node(parentValue);
                nodeMap.put(parentValue, parent);
            }

            if (leftValue != '.') {
                Node leftChild;
                if (nodeMap.containsKey(leftValue)) {
                    leftChild = nodeMap.get(leftValue);
                } else {
                    leftChild = new Node(leftValue);
                    nodeMap.put(leftValue, leftChild);
                }
                parent.left = leftChild;
            }

            if (rightValue != '.') {
                Node rightChild;
                if (nodeMap.containsKey(rightValue)) {
                    rightChild = nodeMap.get(rightValue);
                } else {
                    rightChild = new Node(rightValue);
                    nodeMap.put(rightValue, rightChild);
                }
                parent.right = rightChild;
            }
        }

        Node root = nodeMap.get('A');

        // 전위 순회 결과 출력
        preorderTraversal(root);
        System.out.println();

        // 중위 순회 결과 출력
        inorderTraversal(root);
        System.out.println();

        // 후위 순회 결과 출력
        postorderTraversal(root);
        System.out.println();
    }

    // 전위 순회 구현
    private static void preorderTraversal(Node root) {
        if (root == null) return;

        System.out.print(root.value);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
    }

    // 중위 순회 구현
    private static void inorderTraversal(Node root) {
        if (root == null) return;

        inorderTraversal(root.left);
        System.out.print(root.value);
        inorderTraversal(root.right);
    }

    // 후위 순회 구현
    private static void postorderTraversal(Node root) {
        if (root == null) return;

        postorderTraversal(root.left);
        postorderTraversal(root.right);
        System.out.print(root.value);
    }
}