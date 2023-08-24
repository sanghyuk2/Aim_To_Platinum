package beakjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
    char val;
    Node left;
    Node right;

    public Node(char val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BOJ_1991_트리_순회 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        Node head = new Node('A', null, null);

        for (int i = 0; i < N; i++) {
            char[] val = in.readLine().replaceAll(" ", "").toCharArray();
            insertNode(head, val);
        }
        pre(head);
        System.out.println();
        in(head);
        System.out.println();
        post(head);
    }

    public static void insertNode(Node node, char[] val) {
        if (node.val == val[0]) {
            node.left = createNode(node.left, val[1]);
            node.right = createNode(node.right, val[2]);
            return;
        }

        if (node.left != null) {
            insertNode(node.left, val);
        }

        if (node.right != null) {
            insertNode(node.right, val);
        }
    }

    public static Node createNode(Node node, char val) {
        return val != '.' ? new Node(val, null, null) : null;
    }

    public static void pre(Node head) {
        System.out.print(head.val);

        if (head.left != null) {
            pre(head.left);
        }

        if (head.right != null) {
            pre(head.right);
        }
    }

    public static void in(Node head) {
        if (head.left != null) {
            in(head.left);
        }

        System.out.print(head.val);

        if (head.right != null) {
            in(head.right);
        }
    }

    public static void post(Node head){
        if(head.left != null){
            post(head.left);
        }

        if(head.right != null){
            post(head.right);
        }

        System.out.print(head.val);
    }
}