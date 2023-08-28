package aim_to_platinum.week10_DFS_BFS.b1260;

/*
1. 문제 요약
-


2. 아이디어 (문제 접근법)
[아이디어-1]
-


3. 어려움 및 해결
-
*/

import java.io.*;
import java.util.*;

public class BOJ_1260_DFS와_BFS {

    static int node, edge, start;
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static boolean[] isVisited;
    static Queue<Integer> queue = new LinkedList<>();

    static void DFS(int start){
        isVisited[start] = true;
        sb.append(start).append(" ");

        ArrayList<Integer>destList = map.get(start);
        for(int dest : destList){
            if(isVisited[dest]) continue;
            DFS(dest);
        }
    }

    static void BFS(int start){
        queue.offer(start);
        isVisited[start] = true;
        sb.append(start).append(" ");

        while(!queue.isEmpty()){
            int now = queue.poll();

            for(int dest : map.get(now)){
                if(isVisited[dest]) continue;
                isVisited[dest] = true;
                sb.append(dest).append(" ");
                queue.offer(dest);
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
        start = Integer.parseInt(stk.nextToken());

        for(int i = 0; i <= node; i++){
            map.add(new ArrayList<>());
        }

        for(int i = 0; i < edge; i++){
            stk = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(stk.nextToken());
            int n2 = Integer.parseInt(stk.nextToken());
            map.get(n1).add(n2);
            map.get(n2).add(n1);
        }
        for(ArrayList<Integer> list : map){
            Collections.sort(list);
        }

        isVisited = new boolean[node + 1];
        DFS(start);

        sb.append("\n");

        isVisited = new boolean[node + 1];
        BFS(start);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
