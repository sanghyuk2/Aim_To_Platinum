package aim_to_platinum.week08_review.b11000;
/*
1. 문제 요약
- 먼저 수업의 수 N을 입력받고
    N 만큼 수업의 시작과 종료 시간을 입력받는다 (0 ≤ S < T ≤ 109)
    최소 몇 개의 강의실만으로 강의를 소화할 수 있는지 알아내면 된다

2. 아이디어 (문제 접근법)
[아이디어-1]
- 먼저 시작시간 S와 종료시간 T를 갖는 수업 Class 클래스를 생성한다
- 이후 순서대로 강의를 받는다
- 진행중인 강의 리스트를 순회한다
    + 순회할 때마다 리스트의 크기를 체크해 최대값 classroom 을 알아낸다
    1. 다음 순서 강의 시작시간이
    진행중인 어떤 강의의 죵료시간보다 빠르다면
        - 그냥 리스트에 추가한다
    2. 다음 순서 강의 시작시간이
    진행중인 어떤 강의의 종료시간보다 같거나 뒤라면
        - 진행중인 강의를 리스트에서 제거하고 리스트에 새 강의를 넣는다

    - 이후 수업을 리스트에 담아 정렬한다
    이 때 정렬 기준은 다음과 같다
    1. 시작 시간
    2. 종료 시간

- 최종적으로 classroom 을 출력하면 된다

[아이디어-2]
- 입력받아 정렬하는 부분은 동일하게 진행 -> 시작시간 순으로 정렬된 리스트
- 기존 수업들의 종료 시간과 새로 비교할 수업의 시작 시간만을 비교
    - 종료 시간만 받은 PriorityQueue<Integer> pq 생성
    - 리스트에서 자례대로 값을 가져온다 (새 수업)
        - 새 수업 시작시간이 기존 가장 빨리 끝나는 시간보다 갖거나 뒤라면
            pq.poll(), pq.offer(list[i].endTime)
        - 새 수업 시작시간이 기존 가장 빨리 끝나는 시간보다 앞이라면
            pq.offer(list[i].endTime)



3. 어려움 및 해결
- [아이디어-1] 시간초과 발생
- N번 입력받음과 동시에 N번 만큼의 순회를 하니 시간복잡도가 너무 커졌다
- N번 입력 받는 부분과 N번 만큼 순회하며 교실의 수를 구하는 부분을 분리시켜도
    후자의 경우 같은 시간복잡도를 가져서 의미가 없었다
    때문에 교실의 최소값을 구하는 부분에 PriorityQueue 를 사용해서
    시간복잡도를 낮추고자 하였다

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Class implements Comparable<Class>{
    public int startTime;
    public int endTime;

    Class(int startTime, int endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public int compareTo(Class c){
        if(this.startTime == c.startTime){
            if(this.endTime == c.endTime){
                return 0;
            }else{
                return this.endTime - c.endTime;
            }
        }else{
            return this.startTime - c.startTime;
        }
//        return this.startTime == c.startTime ? this.endTime - c.endTime : this.startTime - c.startTime;
    }
}

public class BOJ_11000_강의실_배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int N = Integer.parseInt(br.readLine());

        List<Class> list = new ArrayList<>();

        for(int i=0; i<N ;i++){
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());
            Class newClass = new Class(start, end);
            list.add(newClass);
        }

        Collections.sort(list);


        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(list.get(0).endTime);
        for(int i=1; i<N; i++){
            int s = list.get(i).startTime;
            int e = list.get(i).endTime;
            if(pq.peek() <= s){
                pq.poll();
            }
            pq.offer(e);
        }

        System.out.println(pq.size());
    }
}
