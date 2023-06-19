/*
1. 문제 요약
- 사람이 있고, 각 사람마다 돈을 인출하는 시간이 존재한다
- 줄을 서 있는 사람의 수 N과 각 사람이 돈을 인출하는데 걸리는 시간 Pi가 주어졌을 때, 각 사람이 돈을 인출하는데 필요한 시간의 합의 최솟값을 구하는 프로그램을 작성하세요

2. 아이디어(문제 접근법)
- 각 index의 사람이 앞에 있는 사람들을 기다려야 하므로, 정렬을 통해 값을 구한다

3. 어려움 및 해결방식
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] times = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(times);

        int answer = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j <= i; j++) {
                answer += times[j];
            }
        }

        System.out.println(answer);

    }
}