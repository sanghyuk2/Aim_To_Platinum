import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<List<Integer>> map = new ArrayList<>();
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        int subjects = Integer.parseInt(st.nextToken());
        int prerequisite = Integer.parseInt(st.nextToken());
        dp = new int[subjects + 1];

        for (int i = 0; i <= subjects; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisite; i++) {
            st = new StringTokenizer(in.readLine());
            int prev = Integer.parseInt(st.nextToken());
            int cur = Integer.parseInt(st.nextToken());
            map.get(cur).add(prev);
        }

        int idx = 0;
        for (List<Integer> list : map) {
            if(list.size() == 0){
                dp[idx++] = 1;
                continue;
            }

            for (int prevS : list) {
                dp[idx] = Math.max(dp[prevS] + 1, dp[idx]);
            }
            idx++;
        }

        for(int i = 1; i < dp.length; i++){
            sb.append(dp[i]).append(" ");
        }

        System.out.println(sb.substring(0, sb.length()-1));
    }
}