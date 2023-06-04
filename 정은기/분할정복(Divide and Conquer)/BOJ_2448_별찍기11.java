package aim_to_platinum.week02_divide_and_conquer.b2448;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String board[] = new String[n];
        board[0] = "  *  ";
        board[1] = " * * ";
        board[2] = "*****";

        for (int k = 1; 3 * (int)Math.pow(2, k) <= n; ++k) {
            DC(k, board);
        }
        for (int i = 0; i < n; ++i) {
            System.out.println(board[i]);
        }
    }

    private static void DC(int k, String board[]) {
        int bottom = 3 * (int)Math.pow(2, k);
        int middle = bottom / 2;
        for (int i = middle; i < bottom; ++i) {
            board[i] = board[i - middle] + " " + board[i -middle];
        }
        String temp = "";
        while (temp.length() < middle) {
            temp += " ";
        }
        for (int i = 0; i < middle; ++i) {
            board[i] = temp + board[i] + temp;
        }
    }
}
