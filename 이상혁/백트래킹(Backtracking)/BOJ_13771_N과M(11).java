import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] nums;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        pick(new ArrayList<>(), 0);

        System.out.println(sb.toString());
    }

    private static void pick(ArrayList<Integer> picked, int toPick) {
        if (toPick == M) {
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < toPick; i++) {
                temp.append(picked.get(i)).append(' ');
            }

            /*
            if (!sb.toString().contains(temp)) {
                sb.append(temp).append('\n');
            }
            문제점1 : .contains() 함수를 사용하면, 중복 수열을 제거하기 위해 매번 .contains() 함수를 호출한다.
            즉, 수열의 길이가 커질수록 많은 시간이 소요된다.

            문제점1은 해결책1로 연결됩니다.
             */
            sb.append(temp).append('\n');

            return;
        }

        int prev = -1;
        for (int next = 0; next < N; next++) {
            /*
            해결책1 : prev이라는 값을 기억하는 변수를 생성하여 다음에 들어올 값과 비교하는 과정을 거칩니다.
            이미 Arrays.sort()라는 메서드를 사용하여 배열을 정리하여 오름차순으로 정렬되어있으니, 작은 값이 들어올리는 만무합니다.
            이에, 단순히 값이 동일한지만 확인 후 동일하다면 값이 결과값을 가지는 List에 들어오지 못하도록 막습니다.

            예)
            3 2
            4 4 2
            의 경우 picked가 2와 4를 이미 보유하고 있는 상태에서 4를 받아들이는 상황을 가정해보자.
            4를 받아들이는 전 단계에서 이미 prev는 4로 값이 초기화되어있다.
            이에, 새로운 4는 if문에서 걸러지며 picked에 저장이 되지 않는다.
             */
            if (prev != nums[next]) {
                prev = nums[next];
                picked.add(prev);
                pick(picked, toPick + 1);
                picked.remove(picked.size() - 1);

            }
        }
    }
}