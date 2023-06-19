/*
1. 문제 요약
- Si에 시작해서 Ti에 끝나는 N개의 수업이 주어지는데, 최소의 강의실을 사용해서 모든 수업을 가능하게 해야 한다
- 참고로, 수업이 끝난 직후에 다음 수업을 시작할 수 있다. (즉, Ti ≤ Sj 일 경우 i 수업과 j 수업은 같이 들을 수 있다.)
- 최소한의 강의실 개수를 구하시오

2. 아이디어(문제 접근법)
    1. 주어진 강의를 시작 시간을 기준으로 오름차순으로 정렬한다
        - 시작 시간을 기준으로 정렬하지 않고 종료 시간을 기준으로 정렬하게 되면 다음과 같은 문제가 발생한다
            - 최소 강의실의 개수를 구하기 어려워진다
                - 시작 시간을 기준으로 정렬하면 가장 빨리 시작하는 강의를 먼저 처리하므로, 강의실의 배정이 단순해진다
            - 더 많은 강의실이 필요할 수 있다
                - 종료 시간을 기준으로 정렬할 경우, 현재 강의의 종료 시간과 이전에 배정된 강의실의 종료 시간을 비교하면서 강의실을 선택해야 한다
                - 만약 이전 강의의 종료 시간보다 현재 강의의 시작 시간이 빠르다면 새로운 강의실을 추가로 사용해야 한
                - 이는 종료 시간을 기준으로 정렬할 경우 더 많은 강의실이 필요하게 되는 상황을 초래할 수 있다
    2. 우선순위 큐를 생성한다
        - 우선순위 큐를 선택한 이유는 다음과 같다
            - 각 강의의 시작 시간과 종료 시간을 주어지며, 이러한 시간 정보를 기반으로 강의실을 배정해야 한다
                - 시작 시간과 종료 시간은 각각 강의의 우선순위와 관련이 있을 수 있으며, 우선순위 큐는 우선순위를 기준으로 요소를 관리하므로 이를 활용할 수 있다
            - 각 시간대마다 강의실 개수를 확인
                - 각 시간대마다 어떤 강의가 종료되고 어떤 강의가 시작되는지를 추적해야 함을 의미한다
                - 우선순위 큐를 사용하면 현재 시간에 진행 중인 강의들 중 가장 빨리 종료되는 강의를 선택할 수 있다
    3. 각 강의를 순회하면서 다음을 수행한다
        - 우선순위 큐가 비어있지 않고, 가장 빨리 끝나는 강의의 종료 시간이 현재 강의의 시작 시간보다 작거나 같다면, 해당 강의실을 반환하고 우선순위 큐에서 제거한다
        - 현재 강의의 종료 시간을 우선순위 큐에 추가한다
    4. 우선순위 큐에 남아있는 강의실의 개수가 최소 필요한 강의실의 개수이다

3. 어려움 및 해결방식
- 어려움
    - 처음에는 완전탐색으로 구현을 하였으나 `시간 초과` 가 뜸
        - 모든 가능한 시간 조합을 확인하며, 겹치는 강의 시간을 찾아 최소 강의실 개수를 구하기 때문이
    - PriorityQueue라는 자료구조를 생각하지 못함
- 아래 4번 항목의 링크를 참고하여 문제해결의 주요 아이디어인 PriorityQueue에 대한 인사이트를 얻음

4. 참고자료
[[BOJ] 백준 11000번 : 강의실 배정 (JAVA)](https://steady-coding.tistory.com/253)

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] startTimes = new int[N];
        int[] endTimes = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            startTimes[i] = Integer.parseInt(st.nextToken());
            endTimes[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(allocateClassrooms(startTimes, endTimes));
    }

    public static int allocateClassrooms(int[] startTimes, int[] endTimes) {
        int n = startTimes.length;
        Lecture[] lectures = new Lecture[n];
        for (int i = 0; i < n; i++) {
            lectures[i] = new Lecture(startTimes[i], endTimes[i]);
        }
        Arrays.sort(lectures);

        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        for (Lecture lecture : lectures) {
            if (!rooms.isEmpty() && rooms.peek() <= lecture.startTime) {
                rooms.poll();
            }
            rooms.offer(lecture.endTime);
        }

        return rooms.size();
    }

    static class Lecture implements Comparable<Lecture> {
        int startTime;
        int endTime;

        public Lecture(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Lecture other) {
            if (this.startTime == other.startTime) {
                return Integer.compare(this.endTime, other.endTime);
            }
            return Integer.compare(this.startTime, other.startTime);
        }
    }
}