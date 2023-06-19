import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 1. 문제 요약
 * 가장 근손실이 큰 날을 찾기
 *
 * 2. 아이디어
 * 홀수인 경우만 맥스값을 지정해주고, 나머지는 짝수와 동일 로직
 *
 * */
public class B_20300 {
    public static void main(String[] args) throws IOException {
       import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

        public class Main {
            public static void main(String[] args) throws IOException {
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                int N = Integer.parseInt(in.readLine());
                long[] intArr = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).sorted().toArray();
                long max = Long.MIN_VALUE;

                if (N % 2 != 0) {
                    max = intArr[N - 1];
                    for (int i = 0; i < N / 2; i++) {
                        max = Math.max(intArr[i] + intArr[N - 2 - i], max);
                    }
                } else {
                    for (int i = 0; i < N / 2; i++) {
                        max = Math.max((intArr[i] + intArr[N - 1 - i]), max);
                    }
                }
                System.out.println(max);
            }
        }
    }
}