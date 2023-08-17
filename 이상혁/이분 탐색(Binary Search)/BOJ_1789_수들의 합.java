/*
1. 문제 요약

- 서로 다른 N개의 자연수의 합이 S라고 한다. S를 알 때, 자연수 N의 최댓값은 얼마일까?

2. 아이디어(문제 접근법)

- 가장 작은 수부터 하나씩 더하면서 S까지 접근하다 i번째 순서의 숫자 i가 i보다 작은 경우 남은 숫자를 더한다

3. 어려움 및 해결방식
어려움) NumberFormatException이 자꾸 떴다
해결방법) 범위가 굉장히 큼에도 int를 고집하였다. => long 타입으로 변환하여 해결

 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//public class Main {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        long S = Long.parseLong(br.readLine());
//        long total = 0;
//        long cnt = 0;
//
//        for (long i = 1; i <= S; i++) {
//            if (S - total < i) {
//                break;
//            }
//            total += i;
//            cnt++;
//
//        }
//
//        System.out.println(cnt);
//    }
//}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long S = Long.parseLong(br.readLine());

        long start = 1;
        long end = S;
        long result = 0;

        while (start <= end) {
            long mid = (start + end) / 2;

            long total = (mid*(mid+1))/2;

            if (total > S) {
                end = mid - 1;
            }else {
                start=mid+1;
                result=mid;
            }
        }

        System.out.println(result);
        
    }
}