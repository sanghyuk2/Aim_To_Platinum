package aim_to_platinum.week08_review.b1916;

/*
1. 문제 요약
- 도시의 개수 Cities(1 ≤ N ≤ 1,000) 를 입력받는다
- 버스의 개수 M(1 ≤ M ≤ 100,000) 를 입력받는다
    - 이후 M번에 걸쳐 다음과 같이 버스 정보를 입력받는다
        출발도시 도착도시 버스비용(0 <= 버스비용 < 100,000)
- 마지막에는 출발 도시와 도착 도시만 입력 받고 이 둘 사이의 최소 비용을 구하면 된다


2. 아이디어 (문제 접근법)
[아이디어-1]
- Dijkstra 알고리즘을 활용하여 출발도시에서 다른 도시들로의 최단 거리를 구한다
- 이후 도착도시 까지의 최단 거리를 출력하면 된다


3. 어려움 및 해결
-

*/


import java.io.*;
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
    public int compareTo(City o){
        return weight - o.weight;
    }
}
public class BOJ_1916_최소비용_구하기 {
    static int Cities;
    static int Buses;
    static int startCity;
    static int endCity;
    static ArrayList<ArrayList<City>> list = new ArrayList<>();

    static int Dijkstra(){
        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.offer(new City(startCity, 0));

        boolean[] isVisited = new boolean[Cities + 1];

        int[] minDist = new int[Cities + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[startCity] = 0;

        while(!pq.isEmpty()){
            City now = pq.poll();

            if(isVisited[now.number]) continue;
            isVisited[now.number] = true;

            for(City c : list.get(now.number)){
                if(!isVisited[c.number] && minDist[c.number] > minDist[now.number] + c.weight){
                    minDist[c.number] = minDist[now.number] + c.weight;
                    pq.add(new City(c.number, minDist[c.number]));
                }
            }
        }
        return minDist[endCity];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        Cities = Integer.parseInt(br.readLine());
        Buses = Integer.parseInt(br.readLine());

        for(int i=0; i<=Cities; i++){
            list.add(new ArrayList<>());
        }

        for(int i=0; i<Buses; i++){
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            int weight = Integer.parseInt(stk.nextToken());
            list.get(from).add(new City(to, weight));
        }

        stk = new StringTokenizer(br.readLine());
        startCity = Integer.parseInt(stk.nextToken());
        endCity = Integer.parseInt(stk.nextToken());

        bw.write( Dijkstra() + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

}