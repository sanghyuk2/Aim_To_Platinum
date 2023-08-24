/*
1. 문제요약

- 상근이는 나무 M미터가 필요하다
- 절단기에 높이 H를 설정한다
- 상근이가 가져갈 나무는 한 줄에 연속된 각각의 나무의 높이 - H 만큼만 가져간다
- 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 구하는 프로그램을 작성하시오

2. 아이디어(문제접근법)

- 주어진 범위가 굉장히 크기에 이진 탐색을 통해 문제를 해결하려고 함
- 끝점과 시작점을 두어 결과값이 중간보다 작은 경우 끝점을 중간으로 당기고 결과값이 중간보다 큰 경우 시작점을 중간보다 1 큰 값으로 옮겨 반복적으로 계산한다

3. 어려움 및 해결 방식
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {

    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        arr = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        long start = 0;
        long end = Arrays.stream(arr).max().getAsLong();
        long res = 0;

        while (start <= end) {
            long total = 0;
            long mid = (start + end) / 2;

            for (int i = 0; i < N; i++) {
                if (arr[i] > mid) total += arr[i] - mid;
            }

            if (total < M) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
                res = mid;
            }
        }

        System.out.println(res);
    }
}
