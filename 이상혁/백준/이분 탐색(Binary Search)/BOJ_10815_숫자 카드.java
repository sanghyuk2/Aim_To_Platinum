/*
1. 문제요약

- 숫자 카드는 정수 하나가 적혀져 있는 카드이다. 상근이는 숫자 카드 N개를 가지고 있다. 정수 M개가 주어졌을 때, 이 수가 적혀있는 숫자 카드를 상근이가 가지고 있는지 아닌지를 구하는 프로그램을 작성하시오.

2. 아이디어(문제접근법)

- 주어진 값의 범위가 굉장히 크므로 이진 탐색을 생각하였다
- 보유한 값과 찾는 값을 각각 배열 형태로 나타내어 보유한 값의 배열 내에 찾고자 하는 값이 존재하는지 이진 탐색을 통해 찾도록 하였다

3. 어려움 및 해결 방식

- 어려움) 값에서 한 번 0이 나오니 있는 값이더라도 계속하여 1이 아닌 0이 호출되었다
- 해결방법) binarySearch(0, N - 1, clientArr[i]) 메소드를 호출 할 때, 시작 지점을 0이 아닌 i로 지정하였었다. 이를 0으로 바꿔주니 문제가 해결되었다
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] ownerArr;
    static int[] clientArr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ownerArr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ownerArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(ownerArr);

        int M = Integer.parseInt(br.readLine());
        clientArr = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            clientArr[i] = Integer.parseInt(st.nextToken());
            int result = binarySearch(0, N - 1, clientArr[i]);

            if (result != -1) {
                sb.append(1).append(" ");
            } else {
                sb.append(0).append(" ");
            }
        }

        System.out.println(sb.toString());
    }

    private static int binarySearch(int start, int end, int target) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (ownerArr[mid] == target) {
                return mid;
            } else if (ownerArr[mid] < target) {
                start = mid + 1;
            } else if (ownerArr[mid] > target) {
                end = mid - 1;
            }
        }
        return -1;
    }
}