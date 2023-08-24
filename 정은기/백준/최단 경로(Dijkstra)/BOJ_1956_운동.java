package aim_to_platinum.week06_shortest_path.b1956;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
1. 문제 요약
- V개의 마을과 E개의 도로 (2 ≤ V ≤ 400), (0 ≤ E ≤ V(V-1))
- 사이클을 찾기를 원한다 == 출발지점과 도착지점이 같은 곳
    그 중에 최소 거리를 찾는 문제
- 세 개의 정수 a, b, c가 주어진다.
    a -> b 거리가 c인 도로 (c <= 10000)
    (a, b) 쌍이 같은 도로가 여러 번 주어지지 않는다 (입력받을 때 비교할 필요가 없다)
- 제한시간 2초 (왜 길지..?)

2. 아이디어 (문제 접근법)
[아이디어-1]
- 전체 인접행렬을 INF로 초기화 하고
- 플로이드 알고리즘으로 전체 도시에 대한 각각의 최소값을 구한 다음
    board[i][i] 들을 순회하며 최소값 찾음
- 만약 값이 INF 이라면 사이클이 없는 것 이므로 -1 출력

[아이디어-2]
- 두 마을을 왕복하는 경우까지만 사이클에 포함시키므로,
    모든 도시 간 최소 경로를 구하는 플로이드-와셜 알고리즘 진행하는 부분에서
    i == j 인 부분은 무시
        (board[i][j] 와 board[i][k] + board[k][j] 를 비교할 건데,
        애초에 처음 입력받은 board[i][i] 는 고려 대상이 아니기 때문)
- 모든 도시 간 최소 경로를 구한 뒤,
    i->j 와 j->i 경로가 있으면 두 마을을 왕복하는 경우이므로 사이클로 인정
    i == j의 경우는 여전히 무시한다
    -> 또 틀렸다


3. 어려움 및 해결
- [아이디어-1] 의 경우 채점 중 60% 부근에서 틀림
    계속 생각해봤는데 "도로의 길이의 합이 가장 작은 사이클" 을 구하는 문제이기에
    a->a 의 경우 사이클이 아니므로 제외하고 생각해야 하는건가? 하고 힌트를 보니 맞았음..
    => [아이디어-2]
- 10001 을 100,000,000 으로 해주니 해결되었다..

 */
public class BOJ_1956_운동 {

    static final long INF = 10000000;

    static int V, E;
    static long[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        V = Integer.parseInt(stk.nextToken());
        E = Integer.parseInt(stk.nextToken());
        board = new long[V][V];

        //인접행렬 초기화
        for(int i=0; i<V; i++) {
            Arrays.fill(board[i], INF);
        }

        //경로 입력
        for(int i=0; i<E; i++){
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken()) - 1;
            int to = Integer.parseInt(stk.nextToken()) - 1;
            long weight = Long.parseLong(stk.nextToken());

            if(from == to) continue;
            board[from][to] = weight;
        }

        //플로이드
        for(int k=0; k<V; k++){
            for(int i=0; i<V; i++){
                for(int j=0; j<V; j++){
                    if(board[i][j] > board[i][k] + board[k][j]){
                        board[i][j] = board[i][k] + board[k][j];
                    }
                }
            }
        }

//        for(int i=0; i<V; i++){
//            for(int j=0; j<V; j++){
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }

        long min = Long.MAX_VALUE;
        for(int i=0; i<V; i++){
            min = Math.min(min, board[i][i]);
        }

        if(min == INF) System.out.println(-1);
        else System.out.println(min);
    }
}
