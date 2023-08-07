package aim_to_platinum.week08_review.b1238;

/*
1. 문제 요약
- 각 마을당 한 명의 학생들이 있다
    마을의 수 = 학생의 수 N(1 ≤ N ≤ 1,000) 을 입력받는다
- 단방향 도로의 개수 M(1 ≤ M ≤ 10,000) 을 입력받는다
- 파티장소 X(1 ≤ X ≤ N) 를 입력받는다
- 이후 M번 만큼 from, to, weight(1 ≤ weight ≤ 100) 를 입력받는다
    - 이 때 시작점과 끝 점이 같은 도로는 없다
    - from 에서 to 로 가는 도로의 수는 1개 이하이다
- 최종적으로 마을->X->마을 의 비용이 가장 많이 드는 학생의 소요시간을 출력하면 된다


2. 아이디어 (문제 접근법)
[아이디어-1]
- 단방향 도로이기 때문에 마을->X 와 X->마을의 최소 소요 시간이 다를 수 있다
- Floyd 알고리즘으로 모든 최단 경로를 파악한 뒤 총 시간을 구할 수 있지만
    Floyd 알고리즘의 시간복잡도는 N^3 이므로 (1 ≤ N ≤ 1,000) 인 상황에서
    사용했다가는 시간초과가 발생한다.
    - 따라서 Dijkstra 알고리즘을 사용해
        1. X->다른 모든 마을 까지의 최단 경로
        2. 다른 모든 마을->X 까지의 최단 경로
      를 알아내 더하면 각 학생의 최소 소요 시간을 구할 수 있다.
    1 -> X를 시작점으로 Dijkstra 알고리즘 수행
    2 -> (힌트 참조) 도로를 입력받는 과정에서
        reverseList 에 출발지점과 도착지점을 반대로 입력받는다
        이후 해당 리스트를 기반으로 Dijkstra 알고리즘을 적용하면
        다른 마을들->X 까지의 최단경로 배열이 완성된다


3. 어려움 및 해결
- 2. 다른 모든 마을->X 까지의 최단 경로
    를 구하는 방법을 생각해내지 못해서 다른 블로그의 답을 참조했다
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Town implements Comparable<Town>{
    int number;
    int weight;
    public Town(int number, int weight){
        this.number = number;
        this.weight = weight;
    }
    @Override
    public int compareTo(Town o){
        return this.weight - o.weight;
    }
}

public class BOJ_1238_파티 {

    static int student, load, party;

    static int[] Dijkstra(ArrayList<ArrayList<Town>> temp){
        PriorityQueue<Town> pq = new PriorityQueue<>();
        pq.offer(new Town(party, 0));

        boolean[] isVisited = new boolean[student + 1];
        Arrays.fill(isVisited, false);

        int[] dist = new int[student + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[party] = 0;

        while(!pq.isEmpty()){
            Town now = pq.poll();
            int nowNumber = now.number;

            if(isVisited[nowNumber]){
                continue;
            }
            isVisited[nowNumber] = true;

            for(Town t : temp.get(nowNumber)){
                if(!isVisited[t.number] && dist[t.number] > dist[nowNumber] + t.weight){
                    dist[t.number] = dist[nowNumber] + t.weight;
                    pq.offer(new Town(t.number, dist[t.number]));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        student = Integer.parseInt(stk.nextToken());
        load = Integer.parseInt(stk.nextToken());
        party = Integer.parseInt(stk.nextToken());

        ArrayList<ArrayList<Town>> list = new ArrayList<>();
        ArrayList<ArrayList<Town>> reverseList = new ArrayList<>();
        for(int i=0; i<=student; i++){
            list.add(new ArrayList<>());
            reverseList.add(new ArrayList<>());
        }

        for(int i=0; i<load; i++){
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            int time = Integer.parseInt(stk.nextToken());

            list.get(from).add(new Town(to, time));
            reverseList.get(to).add(new Town(from, time));
        }

        int[] minDist = Dijkstra(list);
        int[] reverseMinDist = Dijkstra(reverseList);

        int answer = Integer.MIN_VALUE;
        for(int i=0; i<=student; i++){
            answer = Math.max(answer, minDist[i] + reverseMinDist[i]);
        }

        int ans = (answer == Integer.MIN_VALUE ? -1 : answer);
        System.out.println(ans);
    }
}
