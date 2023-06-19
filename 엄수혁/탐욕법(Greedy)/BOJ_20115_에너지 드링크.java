import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 1. 문제 요약
 * 캔 두개의 총 용량을 비교해서 더 적은 쪽의 용량을 큰쪽으로 이동시키는 문제
 *
 * 2. 아이디어
 * 가장 큰 수에 나머지 캔들의 값들을 넣어준다.
 * */
public class B_20115 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        /*int[] array = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int max = Arrays.stream(array).max().orElse(Integer.MIN_VALUE);
        System.out.println((max + Arrays.stream(array).filter(i -> i != max).mapToDouble(i -> i / 2.0).sum()));*/

        double[] array = Arrays.stream(in.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
        double max = Arrays.stream(array).max().orElse(-1);
        System.out.println((max + Arrays.stream(array).filter(i -> i != max).map(i -> i / 2.0).sum()));
    }
}


