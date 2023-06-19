/*
1. 문제 요약

- N개의 운동기구를 최대 두 개씩 선택할 수 있다
    - 모든 운동기구를 다 사용하고 싶다
    - 전에 사용했던 기구는 다시 사용하지 않는다
- 각 운동기구마다 근손실이 일어나는 정도가 정해져있다
- 근손실 정도가 M을 넘지 않도록 하며 운동기구를 모두 사용한다고 가정했을 때, M의 최솟값을 구하시오

2. 아이디어(문제 접근법)

- 근손실 정도가 기록된 배열을 정렬한다
- 만약 배열의 크기가 홀수 일 때
    1. 마지막 숫자는 제외
    2. 나머지 배열 요소들에서 index를 늘려가며 muscleLoses[i] + muscleLoses[N - 2 - i]의 최대값을 구한다
    3. 마지막 숫자와 구한 합 중 가장 큰 값을 비교하여 최대값을 구한다
- 짝수 일 때
    - muscleLoses[i] + muscleLoses[N - 1 - i]

3. 어려움 및 해결방식
- 어려움
    - 모든 반례를 생각해도 맞는데, 백준 제출 시 틀렸다고 뜸
- 해결방법
    - 마지막 System.out.println() 문에서 출력할 때 long 타입을 int로 명시적 형변환을 해 줘서 틀렸음
    - 이를 지워 제출하니 통과하였음
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] muscleLoses = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            muscleLoses[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(muscleLoses);

        long res = -1;
        int length = N / 2;

        if (N % 2 == 0) {
            for (int i = 0; i < length; i++) {
                res = Math.max(res, muscleLoses[i] + muscleLoses[N - 1 - i]);
            }
        }else {
            if (N == 1) {
                System.out.println(muscleLoses[0]);
                return;
            }

            for (int i = 0; i < length; i++) {
                res = Math.max(res, muscleLoses[i] + muscleLoses[N - 2 - i]);
            }
            res = Math.max(res, muscleLoses[N - 1]);
        }

        System.out.println(res);
    }
}