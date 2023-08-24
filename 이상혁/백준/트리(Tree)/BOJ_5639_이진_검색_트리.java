/*
### 1. 문제 요약

- 이진 검색 트리를 전위 순회한 결과가 주어졌을 때, 이 트리를 후위 순회한 결과를 구하는 프로그램을 작성하시오

### 2. 아이디어(문제 접근법)

- 받아들인 입력값으로 트리를 구성해보자
- 구성 트리를 후위 순회한 값으로 출력을 해보자
    - 출력을 하기 위한 `postorderTraversal` 메소드를 생성
        - 후위 순회는 `left Node → right Node → root Node` 순으로 순회하기에 재귀함수를 사용하여 탐색한다

### 3. 어려움 및 해결방식

- X

### 4. 참고자료

- https://johoonday.tistory.com/233
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        // 루트 노드 생성
        line = br.readLine();
        int rootValue = Integer.parseInt(line);
        Node root = new Node(rootValue);

        // 이진 검색 트리 구성
        while ((line = br.readLine()) != null && !line.equals("")) {
            int value = Integer.parseInt(line);
            constructTree(root, value);
        }

        // 후위 순회로 노드 값 출력
        postorderTraversal(root);
    }

    private static void constructTree(Node root, int value) {
        Node currentNode = root;
        Node newNode = new Node(value);

        while (true) {
            if (value < currentNode.value) {
                if (currentNode.left == null) {
                    currentNode.left = newNode;
                    break;
                }
                currentNode = currentNode.left;
            } else {
                if (currentNode.right == null) {
                    currentNode.right = newNode;
                    break;
                }
                currentNode = currentNode.right;
            }
        }
    }

    private static void postorderTraversal(Node root) {
        if (root == null) return;

        postorderTraversal(root.left);
        postorderTraversal(root.right);
        System.out.println(root.value);
    }
}