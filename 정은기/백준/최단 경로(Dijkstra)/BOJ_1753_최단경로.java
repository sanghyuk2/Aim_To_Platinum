package aim_to_platinum.week08_review.b1753;


/*
1. 문제 요약
- 정점의 개수 node 와 간선의 개수 edge 를 입력받는다
- 시작 정점의 번호 start 를 입력받는다
- 이후 node 개의 줄에 걸쳐 start 부터 각 정점까지의 최단 경로값을 출력한다
    - start 노드는 0을 출력한다
    - 해당 노드까지의 경로가 존재하지 않으면 INF 를 출력한다


2. 아이디어 (문제 접근법)
[아이디어-1]
- 


3. 어려움 및 해결
- 
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node implements Comparable<Node>{
    int number;
    int weight;
    Node(int number, int weight){
        this.number = number;
        this.weight = weight;
    }
    public int compareTo(Node o){
        return this.weight - o.weight;
    }
}
public class BOJ_1753_최단경로 {
    static int node, edge, start;
    static ArrayList<ArrayList<Node>> list = new ArrayList<>();
    static int[] minDist;
    static boolean[] isVisited;

    static void Dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        minDist[start] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int nowNumber = now.number;

            if(isVisited[nowNumber]){
                continue;
            }
            isVisited[nowNumber] = true;

            for(Node n : list.get(nowNumber)){
                if(!isVisited[n.number] && minDist[n.number] > minDist[nowNumber] + n.weight){
                    minDist[n.number] = minDist[nowNumber] + n.weight;
                    pq.offer(new Node(n.number, minDist[n.number]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        node = Integer.parseInt(stk.nextToken());
        edge = Integer.parseInt(stk.nextToken());
        start = Integer.parseInt(br.readLine());

        minDist = new int[node + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        isVisited = new boolean[node + 1];

        for(int i=0; i<=node; i++){
            list.add(new ArrayList<>());
        }

        for(int i=0; i<edge; i++){
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            int weight = Integer.parseInt(stk.nextToken());
            list.get(from).add(new Node(to, weight));
        }

        Dijkstra();

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=node; i++){
            int temp = minDist[i];
            if(temp == Integer.MAX_VALUE){
                sb.append("INF").append("\n");
            }else{
                sb.append(temp).append("\n");

            }
        }

        sb.deleteCharAt(sb.lastIndexOf("\n"));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
