package aim_to_platinum.week11_implementation.b20291;
/*
1. 문제 요약
- 바탕화면에 있는 파일명을 입력받아 확장자를 기준으로 정렬한다
    - 이 때 확장자명의 사전 순으로 정렬한다


2. 아이디어 (문제 접근법)
[아이디어-1]
- 파일명과 확장자를 필드로 갖는 Files 클래스를 만든다
    - 내부에 Comparable 을 통한 compareTo 메서드를 구현해 확장자명으로 정렬한다
- 이름을 입력받아 Files 인스턴스를 생성하고 PriorityQueue 를 통해 정렬한다


3. 어려움 및 해결
- TreeMap 사용으로 더 간단하게 풀이
*/

import java.io.*;
import java.util.*;

class Files implements Comparable<Files>{
    String name;
    String exe;

    public Files(String name, String exe){
        this.name = name;
        this.exe =exe;
    }
    @Override
    public int compareTo(Files file) {
        if(this.exe.compareTo(file.exe) == 0){
            return this.name.compareTo(file.name);
        }else{
            return this.exe.compareTo(file.exe);
        }
    }
}
public class BOJ_20291_파일_정리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;

        List<Files> fileList = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        while(N-- > 0){
            stk = new StringTokenizer(br.readLine(), ".");
            fileList.add(new Files(stk.nextToken(), stk.nextToken()));
        }

        TreeMap<String, Integer> tm = new TreeMap<>();
        for(Files file : fileList){
            int before = tm.getOrDefault(file.exe, 0);
            tm.put(file.exe, before + 1);
        }

        for(Map.Entry<String, Integer> element : tm.entrySet()) {
            sb.append(element.getKey()).append(" ").append(element.getValue()).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    /* 이전 코드 
    class Files implements Comparable<Files>{
    String name;
    String exe;

    public Files(String name, String exe){
        this.name = name;
        this.exe =exe;
    }
    @Override
    public int compareTo(Files file) {
        return this.exe.compareTo(file.exe);
    }
    }
    public class BOJ_20291_파일_정리 {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringBuilder sb = new StringBuilder();
            StringTokenizer stk;
    
            PriorityQueue<Files> pq = new PriorityQueue<>();
            int N = Integer.parseInt(br.readLine());
            while(N-- > 0){
                stk = new StringTokenizer(br.readLine(), ".");
                pq.offer(new Files(stk.nextToken(), stk.nextToken()));
            }
    
            HashMap<String, Integer> hm = new HashMap<>();
            for(int i = 0; i < pq.size(); i++){
                Files file = pq.poll();
                int before = hm.getOrDefault(file.exe, 0);
                hm.put(file.exe, before + 1);
            }
    
            Iterator<Map.Entry<String, Integer>> entry = hm.entrySet().iterator();
            while (entry.hasNext()){
                Map.Entry<String, Integer> element = entry.next();
                sb.append(element.getKey()).append(" ").append(element.getValue()).append("\n");
            }
    
            bw.write(sb.toString());
            bw.flush();
            bw.close();
            br.close();
        }
    }
    
    */
}
