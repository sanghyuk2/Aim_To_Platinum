package beakjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node2 {
    int val;
    Node2 left;
    Node2 right;

    public Node2(int val, Node2 left, Node2 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BOJ_5639_이진_검색_트리 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        Node2 head = new Node2(Integer.parseInt(in.readLine()), null, null);
        String val = "";
        while ((val = in.readLine()) != null) {
            if (val.isBlank()) {
                break;
            }
            insertNode(head, Integer.parseInt(val));
        }
        post(head);
        System.out.println(sb.toString());
    }

    public static void insertNode(Node2 head, int val) {
        if (head.val > val) {
            if (head.left == null) {
                head.left = new Node2(val, null, null);
            } else {
                insertNode(head.left, val);
            }
        } else {
            if (head.right == null) {
                head.right = new Node2(val, null, null);
            } else {
                insertNode(head.right, val);
            }
        }
    }

    public static void post(Node2 head) {
        if (head.left != null) {
            post(head.left);
        }

        if (head.right != null) {
            post(head.right);
        }

        sb.append(head.val).append("\n");
    }
}