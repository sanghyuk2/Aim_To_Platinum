/*
1. 문제 요약
- 합쳐진 에너지 드링크의 양을 최대로 하려고 한다
- 다음 규칙을 따르면서 최댓값을 구하여라
    1. 임의의 서로 다른 두 에너지 드링크를 고른다.
    2. 한쪽 에너지 드링크를 다른 쪽 에너지 드링크에 모두 붓는다. 단, 페인은 야근 후유증으로 인해 손이 떨려, 붓는 과정에서 원래 양의 절반을 바닥에 흘리게 된다.
    3. 다 붓고 남은 빈 에너지 드링크는 버린다.
    4. 1~3 과정을 에너지 드링크가 하나만 남을 때까지 반복한다.

2. 아이디어(문제 접근법)
- 가장 큰 값을 반으로 나누면 손해를 너무 크게 보니, 그 보다 작은 값들을 반으로 나눠서 총합을 구하자

3. 어려움 및 해결방식
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] amounts = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            amounts[i] = Integer.parseInt(st.nextToken());
        }

        int max = Arrays.stream(amounts).max().getAsInt();
        double sum = 0;

        for (int amount : amounts) {
            sum += amount;
        }

        sum = (sum - max)/2 + max;

        System.out.println(sum);

    }
}