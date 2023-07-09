/*
1. 문제이해
노드가 주어지고 전위순회, 중위순회, 후위순회를 한 순서대로 출력하는 프로그램
입력 : 루트노드 왼쪽자식노드 오른쪽자식노드
조건 : 항상 A가 루트 노드가 된다.
2. 아이디어
노드 클래스를 만들고 tree 인접배열리스트를 만들어서 저장후 메서드 전위순회(루트→왼쪽→오른쪽), 중위순회(왼쪽→루트→오른쪽), 후윈순회(왼쪽→오른쪽→루트) 메서드를 만들어서 순서대로 출력
3. 어려움 및 해결방식 X
 */
import java.util.ArrayList;
import java.util.Scanner;

class Node{
    char left;
    char right;

    public Node(char left, char right) {
        this.left = left;
        this.right = right;
    }
}

public class BOJ_1991_트리_순회{
    static ArrayList<Node>[] tree;



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        tree = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i < n; i++) {
            char node = sc.next().charAt(0);
            char left = sc.next().charAt(0);
            char right = sc.next().charAt(0);

            tree[node - 'A'].add(new Node(left, right));
        }

        PreOrder('A');
        System.out.println();
        InOrder('A');
        System.out.println();
        PostOrder('A');
        System.out.println();
    }

    public static void PreOrder(char node) {
        if(node == '.') {
            return;
        }

        System.out.print(node);
        PreOrder(tree[node - 'A'].get(0).left);
        PreOrder(tree[node - 'A'].get(0).right);
    }

    public static void InOrder(char node) {
        if(node == '.') {
            return;
        }

        InOrder(tree[node - 'A'].get(0).left);
        System.out.print(node);
        InOrder(tree[node - 'A'].get(0).right);
    }

    public static void PostOrder(char node) {
        if(node == '.') {
            return;
        }

        PostOrder(tree[node - 'A'].get(0).left);
        PostOrder(tree[node - 'A'].get(0).right);
        System.out.print(node);
    }
}