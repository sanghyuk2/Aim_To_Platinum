import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1991_트리_순회 {
    static Map<Character, char[]> tree;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        tree = new HashMap<>();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char node = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            tree.put(node, new char[] {left, right});
        }

        preorder('A');
        sb.append('\n');
        inorder('A');
        sb.append('\n');
        postorder('A');

        System.out.println(sb.toString());
    }

    public static void preorder(char node) {
        if (node == '.') {
            return;
        }
        sb.append(node);
        preorder(tree.get(node)[0]);
        preorder(tree.get(node)[1]);
    }

    public static void inorder(char node) {
        if (node == '.') {
            return;
        }
        inorder(tree.get(node)[0]);
        sb.append(node);
        inorder(tree.get(node)[1]);
    }

    public static void postorder(char node) {
        if (node == '.') {
            return;
        }
        postorder(tree.get(node)[0]);
        postorder(tree.get(node)[1]);
        sb.append(node);
    }
}