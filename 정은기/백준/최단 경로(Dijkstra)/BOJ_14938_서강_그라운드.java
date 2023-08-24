package aim_to_platinum.week08_review.b14938;


/*
1. 문제 요약
- 지역의 수 node 를 입력받는다
- 예은이가 탐색 가능한 거리 range 를 입력받는다
- 길의 수 edge 를 입력받는다
- 1~node 의 번호를 갖는 Area 각각이 갖는 아이텝의 수를 저장하는
    int[node+1] items 를 입력받는다
- 이후 edge 번 만큼 노드 n1, n2와 거리 weight 를 입력받아 저장한다
    이 때 인접리스트는 양방향이다
- 최종적으로 예은이가 얻을 수 있는 아이템의 최대값을 구하면 된다


2. 아이디어 (문제 접근법)
[아이디어-1]
- 1~node 번 Area 각 지점에서 다른 노드들까지의 최단 거리를 구하고 그 중 최대값을 구하는 것이므로
    Dijkstra 알고리즘을 사용한다
- 각 지역 Area 는 int number 와 int weight 를 갖는다
    + items 가 아닌 weight 를 갖는 이유
    인접리스트에 저장해 Dijkstra 활용해 최단거리 구할 때 사용하기 위함
        - Priority Queue 사용시 Area 를 weight 기준으로 정렬할 수 있도록
            implements Comparable 활용


3. 어려움 및 해결
-
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Area implements Comparable<Area>{
    int number;
    int weight;

    Area(int number, int weight){
        this.number = number;
        this.weight = weight;
    }

    @Override
    public int compareTo(Area area){
        return this.weight - area.weight;
    }
}

public class BOJ_14938_서강_그라운드 {

    static int node;
    static int range;
    static int edge;
    static int[] items;
    static List<ArrayList<Area>> list = new ArrayList<>();
    static int[] minDist;
    static boolean[] isVisited;

    static int dijkstra(int from){
        Arrays.fill(minDist, Integer.MAX_VALUE);
        Arrays.fill(isVisited, false);
        PriorityQueue<Area> pq = new PriorityQueue<>();
        pq.offer(new Area(from, 0));
        minDist[from] = 0;

        while(!pq.isEmpty()){
            Area now = pq.poll();
            int nowNumber =now.number;

            if(!isVisited[nowNumber]){
                isVisited[nowNumber] = true;
                for(Area area : list.get(nowNumber)){
                    if(!isVisited[area.number] && minDist[area.number] > minDist[nowNumber] + area.weight){
                        minDist[area.number] = minDist[nowNumber] + area.weight;
                        pq.add(new Area(area.number, minDist[area.number]));
                    }
                }
            }
        }
        int result = 0;
        for(int i=1; i<=node; i++){
            if(minDist[i] <= range){
                result += items[i];
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        node = Integer.parseInt(stk.nextToken());
        minDist = new int[node + 1];
        isVisited = new boolean[node + 1];
        range = Integer.parseInt(stk.nextToken());
        edge = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(br.readLine());
        items = new int[node+1];
        for(int i=1; i<=node; i++){
            items[i] = Integer.parseInt(stk.nextToken());
        }

        for(int i=0; i<=node; i++){
            list.add(new ArrayList<>());
        }

        for(int i=0; i<edge; i++){
            stk = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(stk.nextToken());
            int n2 = Integer.parseInt(stk.nextToken());
            int weight = Integer.parseInt(stk.nextToken());
            list.get(n1).add(new Area(n2, weight));
            list.get(n2).add(new Area(n1, weight));
        }

        int answer = 0;
        for(int i=1; i<=node; i++){
            answer = Math.max(answer, dijkstra(i));
        }

        System.out.println(answer);
    }
}
