import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* 1. 문제 요약
* 주어진 N자리 수열에서 숫자 K개를 지워서 얻을 수 있는 가장 큰 수
*
* 2. 아이디어
* 데크를 통해 문자 비교열을 해나가며 k--를 통해 현재 지워진 숫자를 확인
* 이 과정 중 못 지워져서 K가 남은 경우
* 그 만큼 데크의 마지막 부분에서 요소를 삭제한다.
* */
public class B_20300{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        long[] array = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).sorted().toArray();

        System.out.println(array.length % 2 == 1 ? array[array.length-1] : array[array.length-1] + array[0]);
        List<Integer> list = new ArrayList<>(1);
    }
}