import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 문제 요약
 * A를 B로 바꾸는데 필요한 연산의 최솟값을 구해보자.
 *
 * 조건
 * 1. 2를 곱한다.
 * 2. 1을 수의 가장 오른쪽에 추가한다.
 *
 * 2. 아이디어
 * 나올 수 있는 경우의 수를 bfs를 통해 찾아낸다.
 * */
public class B_16395 {
    static Queue<Long> queue = new LinkedList<>();
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        queue.add(Long.parseLong(st.nextToken()));
        int target = Integer.parseInt(st.nextToken());
        boolean isFind = false;

        while (!queue.isEmpty() && !isFind) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                long val = queue.poll();

                if (val == target) {
                    isFind = true;
                    break;
                }

                if (val * 2 <= target) queue.add(val * 2);
                if ((val * 10) + 1 <= target) queue.add(val * 10 + 1);
            }
            cnt++;
        }
        System.out.println(isFind ? cnt : -1);
    }
}
