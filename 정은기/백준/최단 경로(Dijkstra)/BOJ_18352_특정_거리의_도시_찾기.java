package aim_to_platinum.week08_review.b18352;
/*
1. 문제 요약
- 도시의 개수 N (2 ≤ N ≤ 300,000)
- 도로의 개수 M (1 ≤ M ≤ 1,000,000)
- 거리 정보 K (1 ≤ K ≤ 300,000)
- 출발 도시 X (1 ≤ X ≤ N)
- 이후 M번 만큼 A B를 입력받는다
    이는 A->B 이동이 가능한 단방향 도로가 존재한다는 뜻이다
    A와 B는 서로 다른 자연수이다
- 최종적으로 X에서 출발하여 도착 가능한 도시들 중
    최단 거리가 K인 모든 도시의 번호를 오름차순으로 출력하면 된다
    만약 해당하는 도시가 없다면 -1을 출력한다


2. 아이디어 (문제 접근법)
[아이디어-1]
- BFS 를 이용한 풀이도 가능하지만
    여기에서는 Dijkstra 알고리즘을 활용해보기로 한다



3. 어려움 및 해결
-
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class City implements Comparable<City>{
    int number;
    int weight;
    public City(int number, int weight){
        this.number = number;
        this.weight = weight;
    }
    @Override
    public int compareTo(City city){
        return this.weight - city.weight;
    }
}
public class BOJ_18352_특정_거리의_도시_찾기 {
    static int node;
    static int edge;
    static int range;
    static int startNode;

    static ArrayList<ArrayList<City>> list = new ArrayList<>();
    static boolean[] isVisited;
    static int[] minDist;

    static void dijkstra(int startNum){
        PriorityQueue<City> pq = new PriorityQueue<>();
        minDist[startNum] = 0;
        pq.offer(new City(startNum, 0));

        while(!pq.isEmpty()){
            City newCity = pq.poll();
            int newNumber = newCity.number;
            if(!isVisited[newNumber]){
                isVisited[newNumber] = true;

                for(City c : list.get(newNumber)){
                    if(!isVisited[c.number] && minDist[c.number] > minDist[newNumber] + 1){
                        minDist[c.number] = minDist[newNumber] + 1;
                        pq.offer(new City(c.number, minDist[c.number]));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        node = Integer.parseInt(stk.nextToken());
        isVisited = new boolean[node+1];
        minDist = new int[node+1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        for(int i=0; i<=node; i++){
            list.add(new ArrayList<>());
        }
        edge = Integer.parseInt(stk.nextToken());
        range = Integer.parseInt(stk.nextToken());
        startNode = Integer.parseInt(stk.nextToken());

        for(int i=0; i<edge; i++){
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            list.get(from).add(new City(to, 1));
        }

        dijkstra(startNode);

        StringBuffer sb = new StringBuffer();
        for(int i=1; i<minDist.length; i++){
            if(minDist[i] == range){
                sb.append(i).append("\n");
            }
        }

        if(sb.length() == 0){
            System.out.println(-1);
        }else{
            System.out.println(sb);
        }
    }
}
