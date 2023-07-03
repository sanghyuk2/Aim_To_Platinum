/*
1. 문제이해
     음의 사이클의 여부를 확인하는 문제
      (경로를 지날 때 마다 최단 거리가 계속 감소하는 것)
입력 : TC개수
           //TC1
           N(지점의 수) M(도로의 개수) W(웜홀의 개수)
           S(시작지점) E(도착지점) T(걸리는 시간)  //M개
           S(시작지점) E(도착지점) T(걸리는 시간) //W개
           //..TCN개
           N(지점의 수) M(도로의 개수) W(웜홀의 개수)
           S(시작지점) E(도착지점) T(줄어드는 시간)  //M개
           S(시작지점) E(도착지점) T(줄어드는 시간) //W개
출력 : TC개의 줄에 걸쳐서 만약에 시간이 줄어들면서 출발 위치로 돌아오는 것이 가능하면 YES, 불가능하면 NO를 출력한다.
조건 : 도로는 양방향 웜홀은 단방향이다.(웜홀은 지나면 시간이 줄어든다.)
          두 지점을 연결하는 도로가 한 개보다 더 많을 수도 있다.
2. 아이디어
    벨만-포드 알고리즘을 사용해서 도로와 웜홀인 경우를 나눠서 입력받고 알고리즘을 구현해서 음의 사이클이 있는지 확인한다.
3. 어려움 및 해결방식
 처음에 문제를 읽었을 때 문제가 자체가 이해되지 않아서 벨만포드 알고리즘 부터 다른 분들의 풀이 방식을 참고하여 문제를 이해한 후에 문제를 풀었다.
n번의 경로를 찾아서 저장하고 이후 1번 더 시행했을 때에도 값이 변한다면 음의 사이클이 존재하는 것이다.
 */



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1865_웜홀 {
    static int n;
    static int m;
    static int w;
    static int[] dist;
    static ArrayList<Node>[] road;

    static class Node{
        int node;
        int time;

        public Node(int node,int time){
            this.node = node;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            dist = new int[n+1];
            Arrays.fill(dist,1000000);
            road = new ArrayList[n+1];

            for(int j = 0; j < n+1; j++) {
                road[j] = new ArrayList<>();
            }

            for(int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                road[s].add(new Node(e,t));
                road[e].add(new Node(s,t));
            }

            for(int k = 0; k < w; k++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                road[s].add(new Node(e,-t));
            }


            if(bellmanFord()) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }

        System.out.println(sb);

        br.close();
    }

    static boolean bellmanFord() {
        dist[1] = 0;

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j < road.length; j++) {
                for(Node next : road[j]) {
                    if(next.time + dist[j] < dist[next.node]) {
                        dist[next.node] = next.time + dist[j];
                    }
                }
            }
        }


        for(int j = 1; j < road.length; j++) {
            for(Node next : road[j]) {
                if(next.time + dist[j] < dist[next.node]) {
                    return true;
                }
            }
        }
        return false;
    }
}