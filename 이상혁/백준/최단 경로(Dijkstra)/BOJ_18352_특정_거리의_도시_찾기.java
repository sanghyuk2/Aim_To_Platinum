/*
1. 문제 요약

- 어떤 나라에는 1번부터 N번까지의 도시와 M개의 단방향 도로가 존재한다. 모든 도로의 거리는 1이다.
- 이 때 특정한 도시 X로부터 출발하여 도달할 수 있는 모든 도시 중에서, 최단 거리가 정확히 K인 모든 도시들의 번호를 출력하는 프로그램을 작성하시오.
- 또한 출발 도시 X에서 출발 도시 X로 가는 최단 거리는 항상 0이라고 가정한다.

2. 아이디어(문제 접근법)

- 각 도시에서 다음 도시로 갈 수 있는 방향을 모두 저장한다
- 자료구조 PriorityQueue를 사용하여 최소 비용을 가진 도시 노드를 계속 찾아간다

3. 어려움 및 해결방식

4. 참고자료
- 이것이 코딩 테스트다
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int INF = (int) 1e9;
    private static List<List<City>> list = new ArrayList<>();
    static int dist[];
    private static StringBuilder sb = new StringBuilder();
    private static int N, M, K, X;

    static class City implements Comparable<City> {
        int index;
        int distance;

        public City(int index, int list) {
            this.index = index;
            this.distance = list;
        }

        @Override
        public int compareTo(City c) {
            return this.distance - c.distance;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        dist = new int[N+1];
        Arrays.fill(dist, INF);

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.get(start).add(new City(end, 1));
        }

        dijkstra(X);

        for (int i = 1; i < dist.length; i++) {
            if(dist[i] == K) {
                sb.append(i).append('\n');
            }
        }

        if (sb.length() == 0) {
            System.out.println(-1);
        }else {
            System.out.println(sb.toString());
        }

    }

    private static void dijkstra(int startCity) {
        PriorityQueue<City> pq = new PriorityQueue<>();
        boolean visit[] = new boolean[N + 1];
        dist[startCity] = 0;

        pq.offer(new City(startCity, 0));

        while (!pq.isEmpty()) {
            City city = pq.poll();
            int num = city.index;

            if (visit[num]) {
                continue;
            }

            for (City c : list.get(num)) {
                if( !visit[c.index] && dist[c.index] > (c.distance + dist[num]) ) {
                    dist[c.index] = c.distance + dist[num];
                    pq.offer(new City(c.index, dist[c.index]));
                }
            }

        }

    }
}