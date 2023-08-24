package aim_to_platinum.week07_tree.b1991;
/*
1. 문제 요약
- 이진 트리를 입력받아 전위순회, 중위순회, 후위순회 순으로 출력하면 된다
- 한 번 더 정리
    1. 전위순회 : [루트노드] -> (왼쪽->오른쪽) 서브트리 전위순회
    2. 중위순회 : 왼쪽 서브트리 중위순회 -> [루트노드] -> 오른쪽 서브트리 중위순회
    3. 후위순회 : (왼쪽->오른쪽) 서브트리 후위순회 -> [루트노드]
- 항상 A가 루트 노드가 된다
- 자식 노드가 없으면 . 으로 입력받는다

2. 아이디어 (문제 접근법)
[아이디어-1]
- 노드 클래스를 만들어서 각 방식에 맞는 순서대로 재귀호출을 진행하려 한다
    - preorder

    - inorder

    - postorder


3. 어려움 및 해결
- 출력보다는 입력 부분에서 생각을 더 해봐야 했던 문제였다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
    char name;
    Node left;
    Node right;

    Node(){}
    Node(char name, Node left, Node right){
        this.name = name;
        this.left = left;
        this.right = right;
    }
}

public class BOJ_1991_트리_순회 {

    static Node rootNode = new Node('A', null, null);

    static class Node{
        char name;
        Node left;
        Node right;

        public Node(char name, Node left, Node right) {
            this.name = name;
            this.left = left;
            this.right = right;
        }
    }

    static void addNode(Node node, char head, char left, char right) {
        if(node.name==head) {
            node.left = (left=='.' ? null : new Node(left, null, null));
            node.right = (right=='.' ? null : new Node(right, null, null));
        }
        else {
            if(node.left!=null) addNode(node.left, head, left, right);
            if(node.right!=null) addNode(node.right, head, left, right);
        }
    }


    static void preOrder(Node node) {
        if(node ==null) return;
        System.out.print(node.name);
        preOrder(node.left);
        preOrder(node.right);
    }
    static void inOrder(Node node) {
        if(node ==null) return;
        inOrder(node.left);
        System.out.print(node.name);
        inOrder(node.right);
    }
    static void postOrder(Node node) {
        if(node ==null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.name);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        while(n --> 0) {
            StringTokenizer stk = new StringTokenizer(br.readLine());

            char head = stk.nextToken().charAt(0);
            char left = stk.nextToken().charAt(0);
            char right = stk.nextToken().charAt(0);

            addNode(rootNode, head, left, right);
        }

        preOrder(rootNode);
        System.out.println();
        inOrder(rootNode);
        System.out.println();
        postOrder(rootNode);
    }
}
