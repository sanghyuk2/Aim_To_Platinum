package aim_to_platinum.week06_shortest_path.b11404;
/*
1. 문제 요약
- n(2 ≤ n ≤ 100)개의 도시, m(1 ≤ m ≤ 100,000)개의 버스가 있다.
- 각 버스별로 사용할 때마다 필요한 비용이 있다. (가중치)
- 도시 A->B 의 최솟값을 기록한 인접행렬을 출력하면 된다.
- 제한시간 1초..

2. 아이디어 (문제 접근법)
[아이디어-1]
- 플로이드 알고리즘 : 모든 정점에서 모든 정점으로의 최단 경로를 구하는 알고리즘
- 경로찾기처럼 거쳐가는 경로를 활용하되, 크기 비교부분 추가

3. 어려움 및 해결
- "시작 도시와 도착 도시가 같은 경우는 없다" 라는 조건을 고려하지 않았다.
    플로이드 알고리즈 부분에
    if(i == j) continue; 를 추가해주었다.
- "시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다." 부분도 고려해야 한다.
    예제 입력1 입력 값중에
    3 4 1
    3 4 2 나
    1 4 1
    1 4 2 등등이 그렇다
    고로 입력을 받는 부분에
    if(weight < board[from][to]) board[from][to] = weight; 를 추가했다
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11404_플로이드 {

    static int city;
    static int bus;
    static long board[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        city = Integer.parseInt(br.readLine());
        bus = Integer.parseInt(br.readLine());
        board = new long[city][city];

        for(int i=0; i<city; i++){
            Arrays.fill(board[i], Integer.MAX_VALUE);
        }

        for(long i=0; i<bus; i++){
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken())-1;
            int to = Integer.parseInt(stk.nextToken())-1;
            long weight = Integer.parseInt(stk.nextToken());
            if(weight < board[from][to]) board[from][to] = weight;
        }

        for(int k=0; k<city; k++){
            for(int i=0; i<city; i++){
                for(int j=0; j<city; j++){
                    if(i == j) continue;
                    if(board[i][k]+board[k][j] < board[i][j]){
                        board[i][j] = board[i][k]+board[k][j];
                    }
                }
            }
        }

        for(int i=0; i<city; i++){
            for(int j=0; j<city; j++){
                if(board[i][j] == Integer.MAX_VALUE) board[i][j] = 0;
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
