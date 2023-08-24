import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10971_외판원순회 {
    static int N;
    static int[][] W;
    static boolean[] isVisited;
    static int minCost = Integer.MAX_VALUE;

    static boolean isFinished(){
        for(boolean x : isVisited){
            if(!x) return false;
        }
        return true;
    }

    static void solution(int started, int now, int cost){
        if(isFinished()){
            if(W[now][started] != 0){
                cost += W[now][started];
                minCost = Math.min(minCost, cost);
                return;
            }
        }

        for(int i=0; i<N; i++){
            if(!isVisited[i] && W[now][i]!=0){
                isVisited[i] = true;
                solution(started, i, cost+W[now][i]);
                isVisited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;

        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        isVisited = new boolean[N];

        for(int i=0; i<N; i++){
            stk = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                W[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        for(int i=0; i<N; i++){
            isVisited = new boolean[N];
            Arrays.fill(isVisited, false);
            isVisited[i] = true;
            solution(i, i, 0);
        }

        System.out.println(minCost);
    }

}
