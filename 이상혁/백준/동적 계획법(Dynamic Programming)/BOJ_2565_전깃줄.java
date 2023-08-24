/*
1. 문제 요약

전깃줄이 전봇대에 연결되는 위치는 전봇대 위에서부터 차례대로 번호가 매겨진다.

전깃줄의 개수와 전깃줄들이 두 전봇대에 연결되는 위치의 번호가 주어질 때, 남아있는 모든 전깃줄이 서로 교차하지 않게 하기 위해 없애야 하는 전깃줄의 최소 개수를 구하는 프로그램을 작성하시오.

2. 아이디어(문제 접근법)

예제의 입력은 다음과 같다

```
8
1 8
3 9
2 2
4 1
6 4
10 10
9 7
7 6
```

전봇대 숫자는 위에서부터 아래로 증가하기에 순서대로 정렬하면 다음과 같은 형태가 된다

| 1 | 2 | 3 | 4 | 6 | 7 | 9 | 10 |
| 8 | 2 | 9 | 1 | 4 | 6 | 7 | 10 |

`교차된다` 라는 말을 다르게 표현하면 위에서 이미 연결된 숫자보다 작다라는 뜻이다.

없애야 하는 전깃줄의 최소 개수를 구하려면 남아있는 전깃줄의 최대 개수를 구하면 된다.

즉, 최장 증가 부분 수열(LIS) 알고리즘을 적용할 수 있다.

3. 어려움 및 해결방식

어려움) 최장 증가 부분 수열 알고리즘을 왜 적용해야하는지 아이디어를 얻기까지 어려움이 있었다.

해결) 정렬을 하고 난 뒤, 교차라는 단어가 왜 사용되었는지 생각하다보니 아이디어를 얻게 되었다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int[] array;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr = new int[501];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        array = Arrays.stream(arr).filter(value -> value != 0).toArray();

        Arrays.fill(dp, 1);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = Arrays.stream(dp).max().getAsInt();
        System.out.println(N - max);

    }
}

